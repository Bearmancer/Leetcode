#!/usr/bin/env python3
"""Validate that every ``src/Q_*/`` directory conforms to the canonical schema.

Canonical schema::

    Q_<4-digit-zero-padded-id>_<Title_Case_Words_Digit_Spelled_Out>

Rules checked for each ``src/Q_*/`` directory:

    1. Dir name matches ``^Q_\\d{4}_[A-Z][A-Za-z_]+$``
       (4-digit pad, Title_Case title, no digits in title portion)
    2. The Java file's first non-comment, non-blank line is
       ``package <dir_name>;``, matching the directory name exactly
    3. The ``/**`` JavaDoc block contains a ``https://leetcode.com/problems/``
       URL on at least one line
    4. No underscore-separated title segment starts with a digit character
    5. The numeric ID prefix is exactly 4 digits

Output: a formatted table — ``PATH | RULE | ISSUE | SUGGESTED_FIX`` —
followed by a summary line.

Exit codes:
    0 — no violations found
    1 — one or more violations found

Usage::

    py validate_schema.py
    py validate_schema.py --src-dir C:/Users/Lance/Desktop/Leetcode/src
    py validate_schema.py --verbose
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path
from typing import NamedTuple

SCRIPT_DIR = Path(__file__).resolve().parent
DEFAULT_SRC_DIR = (SCRIPT_DIR / ".." / "src").resolve()

_ROMAN_RE = re.compile(r"^[IVX]+$")
_RULE1_RE = re.compile(r"^Q_\d{4}_[A-Z][A-Za-z_]+$")

DIGIT_WORDS: dict[str, str] = {
    "0": "Zero",
    "1": "One",
    "2": "Two",
    "3": "Three",
    "4": "Four",
    "5": "Five",
    "6": "Six",
    "7": "Seven",
    "8": "Eight",
    "9": "Nine",
}

SPECIAL_CASE_TOKENS: dict[str, str] = {
    "api": "API",
    "bst": "BST",
    "cpu": "CPU",
    "dfs": "DFS",
    "dp": "DP",
    "lfu": "LFU",
    "lru": "LRU",
    "sql": "SQL",
    "url": "URL",
}

ROMAN_NUMERALS: dict[str, str] = {
    "ii": "II",
    "iii": "III",
    "iv": "IV",
    "vi": "VI",
    "vii": "VII",
    "viii": "VIII",
    "ix": "IX",
    "xi": "XI",
    "xii": "XII",
}


# ---------------------------------------------------------------------------
# Schema helpers (inlined — not imported from rip script)
# ---------------------------------------------------------------------------


def _is_roman_numeral(token: str) -> bool:
    """Return True if *token* consists only of Roman numeral characters I, V, X."""
    return bool(_ROMAN_RE.fullmatch(token.upper()))


def _title_segment_to_words(segment: str) -> str:
    """Convert a single whitespace-free segment, expanding digit sub-tokens.

    Args:
        segment: A whitespace-free token such as ``"3Sum"``, ``"1D"``,
            ``"II"``, or ``"Sum"``.

    Returns:
        Schema-safe string, e.g. ``"Three_Sum"``, ``"One_D"``, ``"II"``.
    """
    if not segment:
        return ""
    lowered = segment.lower()
    if lowered in SPECIAL_CASE_TOKENS:
        return SPECIAL_CASE_TOKENS[lowered]
    if lowered in ROMAN_NUMERALS:
        return ROMAN_NUMERALS[lowered]
    if _is_roman_numeral(segment):
        return segment.upper()
    parts = re.split(r"(?<=\d)(?=[A-Za-z])|(?<=[A-Za-z])(?=\d)", segment)
    result: list[str] = []
    for part in parts:
        if not part:
            continue
        lowered_part = part.lower()
        if lowered_part in SPECIAL_CASE_TOKENS:
            result.append(SPECIAL_CASE_TOKENS[lowered_part])
        elif lowered_part in ROMAN_NUMERALS:
            result.append(ROMAN_NUMERALS[lowered_part])
        elif part.isdigit():
            result.extend(DIGIT_WORDS[c] for c in part)
        else:
            result.append(part.capitalize())
    return "_".join(result) if result else segment.capitalize()


def _canonical_dir_name(problem_id: str, title: str) -> str:
    """Compute the canonical directory name for a given problem ID and title.

    This is the single source of truth for the schema within this script.
    The ``title`` parameter may be reconstructed from an existing directory
    name by replacing underscores with spaces.

    Args:
        problem_id: Numeric problem ID string, e.g. ``"1"`` or ``"15"``.
        title: Human-readable (or reconstructed) title,
            e.g. ``"Two Sum"`` or ``"Three Sum"``.

    Returns:
        Canonical directory name, e.g. ``"Q_0001_Two_Sum"``.
    """
    padded = problem_id.zfill(4)
    cleaned = re.sub(r"[^A-Za-z0-9 ]", " ", title)
    tokens = cleaned.split()
    schema_parts: list[str] = []
    for token in tokens:
        converted = _title_segment_to_words(token)
        schema_parts.extend(p for p in converted.split("_") if p)
    return f"Q_{padded}_{'_'.join(schema_parts)}"


# ---------------------------------------------------------------------------
# Violation record
# ---------------------------------------------------------------------------


class ViolationRecord(NamedTuple):
    """A single schema violation discovered in one ``src/Q_*/`` directory."""

    path: str
    rule: int
    issue: str
    suggested_fix: str


# ---------------------------------------------------------------------------
# Java file inspection helpers
# ---------------------------------------------------------------------------


def _first_non_comment_line(lines: list[str]) -> str | None:
    """Return the first non-blank, non-comment Java line from *lines*.

    Handles ``//`` single-line comments and ``/* ... */`` block comments
    (including ``/** ... */`` JavaDoc).  A line that both opens and closes a
    block comment on the same line is treated as a comment and skipped.

    Args:
        lines: Raw lines from a ``.java`` file (as returned by
            ``str.splitlines()``).

    Returns:
        The stripped content of the first code line, or ``None`` if none
        found.
    """
    in_block = False
    for line in lines:
        stripped = line.strip()
        if not stripped:
            continue
        if in_block:
            if "*/" in stripped:
                in_block = False
            continue
        if stripped.startswith("//"):
            continue
        if stripped.startswith("/*"):
            if "*/" not in stripped:
                in_block = True
            continue
        return stripped
    return None


def _has_leetcode_url_in_javadoc(lines: list[str]) -> bool:
    """Return True if any line inside a ``/** */`` block contains a LeetCode URL.

    Args:
        lines: Raw lines from a ``.java`` file.

    Returns:
        ``True`` if ``https://leetcode.com/problems/`` appears inside a
        JavaDoc comment; ``False`` otherwise.
    """
    in_javadoc = False
    for line in lines:
        stripped = line.strip()
        if "/**" in stripped:
            in_javadoc = True
        if in_javadoc and "https://leetcode.com/problems/" in stripped:
            return True
        if in_javadoc and stripped == "*/":
            in_javadoc = False
    return False


# ---------------------------------------------------------------------------
# Directory validation
# ---------------------------------------------------------------------------


def _extract_id_and_title(dir_name: str) -> tuple[str, str] | None:
    """Parse raw problem ID and title-portion string from a directory name.

    Args:
        dir_name: Directory name such as ``"Q_1_Two_Sum"`` or
            ``"Q_0001_Two_Sum"``.

    Returns:
        A ``(raw_id, title_portion)`` tuple, or ``None`` if the name does
        not begin with the expected ``Q_<digits>_`` prefix.
    """
    m = re.match(r"^Q_(\d+)_(.+)$", dir_name)
    if not m:
        return None
    return m.group(1), m.group(2)


def validate_directory(d: Path, verbose: bool) -> list[ViolationRecord]:
    """Run all five schema rules against a single ``Q_*`` directory.

    Args:
        d: ``Path`` to the problem directory (e.g. ``src/Q_1_Two_Sum``).
        verbose: When ``True``, emit additional diagnostic messages to
            stdout.

    Returns:
        A (possibly empty) list of :class:`ViolationRecord` items, one per
        rule that fails.
    """
    violations: list[ViolationRecord] = []
    dir_name = d.name

    parsed = _extract_id_and_title(dir_name)
    raw_id = parsed[0] if parsed else ""
    title_portion = parsed[1] if parsed else ""

    pseudo_title = title_portion.replace("_", " ") if title_portion else ""
    canonical = _canonical_dir_name(raw_id, pseudo_title) if raw_id else dir_name

    if not _RULE1_RE.fullmatch(dir_name):
        violations.append(
            ViolationRecord(
                path=dir_name,
                rule=1,
                issue=f"'{dir_name}' does not match Q_<4-digit>_<TitleCase> schema",
                suggested_fix=canonical,
            )
        )

    if canonical != dir_name:
        violations.append(
            ViolationRecord(
                path=dir_name,
                rule=1,
                issue=(
                    f"'{dir_name}' is schema-safe but not canonical; "
                    f"expected '{canonical}'"
                ),
                suggested_fix=canonical,
            )
        )

    if raw_id and len(raw_id) != 4:
        violations.append(
            ViolationRecord(
                path=dir_name,
                rule=5,
                issue=f"ID has {len(raw_id)} digit(s); expected exactly 4",
                suggested_fix=canonical,
            )
        )

    if title_portion:
        for segment in title_portion.split("_"):
            if segment and segment[0].isdigit():
                fixed_title = " ".join(
                    _title_segment_to_words(seg)
                    for seg in title_portion.split("_")
                    if seg
                )
                violations.append(
                    ViolationRecord(
                        path=dir_name,
                        rule=4,
                        issue=f"Title segment '{segment}' starts with a digit",
                        suggested_fix=_canonical_dir_name(
                            raw_id or "0000", fixed_title
                        ),
                    )
                )
                break

    java_files = sorted(d.glob("*.java"))
    if not java_files:
        if verbose:
            print(f"  [INFO] No .java file in {dir_name} — skipping Rules 2 & 3")
        return violations

    java_path = java_files[0]
    try:
        content = java_path.read_text(encoding="utf-8", errors="replace")
    except OSError as exc:
        if verbose:
            print(f"  [WARN] Cannot read {java_path.name}: {exc}")
        return violations

    lines = content.splitlines()

    first_line = _first_non_comment_line(lines)
    expected_package = f"package {dir_name};"
    if first_line is None:
        violations.append(
            ViolationRecord(
                path=dir_name,
                rule=2,
                issue="No non-comment, non-blank line found in Java file",
                suggested_fix=expected_package,
            )
        )
    elif first_line != expected_package:
        violations.append(
            ViolationRecord(
                path=dir_name,
                rule=2,
                issue=f"First code line is '{first_line}'; expected '{expected_package}'",
                suggested_fix=expected_package,
            )
        )

    if not _has_leetcode_url_in_javadoc(lines):
        violations.append(
            ViolationRecord(
                path=dir_name,
                rule=3,
                issue="No 'https://leetcode.com/problems/' URL found in /** */ block",
                suggested_fix='Add /** ... <a href="https://leetcode.com/problems/<slug>/"> ... */',
            )
        )

    return violations


# ---------------------------------------------------------------------------
# Table output
# ---------------------------------------------------------------------------


def _print_violations_table(violations: list[ViolationRecord]) -> None:
    """Print all violations as a formatted, right-padded table to stdout.

    Args:
        violations: All collected :class:`ViolationRecord` items.
    """
    if not violations:
        return

    headers = ("PATH", "RULE", "ISSUE", "SUGGESTED_FIX")
    rows: list[tuple[str, str, str, str]] = [
        (v.path, str(v.rule), v.issue, v.suggested_fix) for v in violations
    ]

    col_widths = [len(h) for h in headers]
    for row in rows:
        for i, cell in enumerate(row):
            col_widths[i] = max(col_widths[i], len(cell))

    def _fmt(row: tuple[str, ...]) -> str:
        return " | ".join(cell.ljust(col_widths[i]) for i, cell in enumerate(row))

    separator = "-+-".join("-" * w for w in col_widths)

    print(_fmt(headers))
    print(separator)
    for row in rows:
        print(_fmt(row))


# ---------------------------------------------------------------------------
# Run logic
# ---------------------------------------------------------------------------


def run(args: argparse.Namespace) -> int:
    """Scan *src_dir* and report schema violations.

    Args:
        args: Parsed namespace from :func:`main`.

    Returns:
        ``0`` if no violations were found, ``1`` otherwise.
    """
    src_dir = Path(args.src_dir)
    verbose: bool = args.verbose

    if not src_dir.is_dir():
        print(f"ERROR: src directory not found: {src_dir}", file=sys.stderr)
        return 1

    candidates = sorted(
        d for d in src_dir.iterdir() if d.is_dir() and d.name.startswith("Q_")
    )

    if not candidates:
        print(f"No Q_* directories found under {src_dir}")
        return 0

    all_violations: list[ViolationRecord] = []
    for d in candidates:
        dir_violations = validate_directory(d, verbose)
        all_violations.extend(dir_violations)
        if verbose and not dir_violations:
            print(f"  [OK] {d.name}")

    _print_violations_table(all_violations)

    total_dirs = len(candidates)
    total_violations = len(all_violations)

    if all_violations:
        print()

    print(f"{total_dirs} file(s) checked, {total_violations} violation(s) found.")

    return 0 if total_violations == 0 else 1


# ---------------------------------------------------------------------------
# Entry point
# ---------------------------------------------------------------------------


def main() -> None:
    """Parse CLI arguments and run the schema validator."""
    parser = argparse.ArgumentParser(
        description="Validate src/Q_*/ directories against the canonical schema.",
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog=(
            "Canonical schema:\n"
            "  Q_<4-digit-padded-id>_<Title_Case_Words_Digit_Spelled_Out>\n\n"
            "Rules:\n"
            "  1  Dir name matches ^Q_\\d{4}_[A-Z][A-Za-z_]+$\n"
            "  2  First Java code line is 'package <dir_name>;'\n"
            "  3  JavaDoc /** */ block contains a LeetCode problems URL\n"
            "  4  No underscore-separated title segment starts with a digit\n"
            "  5  Numeric ID is exactly 4 digits\n\n"
            "Examples:\n"
            "  py validate_schema.py\n"
            "  py validate_schema.py --src-dir C:/Users/Lance/Desktop/Leetcode/src\n"
            "  py validate_schema.py --verbose\n"
        ),
    )
    parser.add_argument(
        "--src-dir",
        default=str(DEFAULT_SRC_DIR),
        help=f"Path to the src/ directory to validate (default: {DEFAULT_SRC_DIR})",
    )
    parser.add_argument(
        "-v",
        "--verbose",
        action="store_true",
        help="Print a status line for every directory, including passing ones",
    )
    args = parser.parse_args()
    sys.exit(run(args))


if __name__ == "__main__":
    main()
