#!/usr/bin/env python3
"""Migrate existing ``src/Q_<n>_<Title>`` directories to the canonical
``Q_<4n>_<Title>`` schema.

⚠️  Safety notes:
- Run with ``--dry-run`` first to preview all renames before applying them.
- Only the directory name and the ``package ...;`` declaration are changed.
- No algorithm/implementation code is altered.
- Pass ``--execute`` to apply the renames.

Usage::

    py migrate_schema.py --dry-run
    py migrate_schema.py --execute
    py migrate_schema.py --src-dir C:/path/to/src --dry-run
    py migrate_schema.py --execute --verbose

Exit codes:
    0 — no renames needed (all dirs already canonical), or dry-run complete
    1 — one or more renames failed during execution
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

SCRIPT_DIR = Path(__file__).resolve().parent
DEFAULT_SRC_DIR = (SCRIPT_DIR / ".." / "src").resolve()

_ROMAN_RE = re.compile(r"^[IVX]+$")

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
    """Convert a single whitespace-free segment to schema-safe words."""
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
    """Compute the canonical ``Q_0001_Two_Sum`` directory name.

    Args:
        problem_id: Numeric ID string, e.g. ``"1"`` or ``"15"``.
        title: Human-readable (or reconstructed) title, e.g. ``"Two Sum"``.

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
# Migration logic
# ---------------------------------------------------------------------------


def _compute_renames(src_dir: Path) -> list[tuple[Path, Path]]:
    """Return a list of (old_path, new_path) pairs that need renaming.

    Args:
        src_dir: The ``src/`` directory to scan.

    Returns:
        Pairs of (current directory path, desired canonical directory path).
        Directories already at their canonical name are omitted.
    """
    renames: list[tuple[Path, Path]] = []
    for q_dir in sorted(src_dir.glob("Q_*/")):
        if not q_dir.is_dir():
            continue
        old_name = q_dir.name
        m = re.match(r"^Q_(\d+)_(.+)$", old_name)
        if not m:
            continue
        raw_id, title_part = m.group(1), m.group(2)
        pseudo_title = title_part.replace("_", " ")
        new_name = _canonical_dir_name(raw_id, pseudo_title)
        if old_name == new_name:
            continue
        new_path = src_dir / new_name
        renames.append((q_dir, new_path))
    return renames


def _update_package_declaration(java_file: Path, old_name: str, new_name: str) -> bool:
    """Replace the ``package <old_name>;`` line with ``package <new_name>;``.

    Args:
        java_file: Path to the ``.java`` file.
        old_name: Old directory/package name.
        new_name: New canonical directory/package name.

    Returns:
        ``True`` if the file was updated; ``False`` if the old package
        declaration was not found (the file is left unchanged).
    """
    try:
        text = java_file.read_text(encoding="utf-8")
    except OSError:
        return False

    new_text, count = re.subn(
        rf"^package {re.escape(old_name)};",
        f"package {new_name};",
        text,
        count=1,
        flags=re.MULTILINE,
    )
    if count == 0:
        return False
    java_file.write_text(new_text, encoding="utf-8")
    return True


def migrate(src_dir: Path, dry_run: bool, verbose: bool) -> int:
    """Perform or preview the migration.

    Args:
        src_dir: The ``src/`` directory to migrate.
        dry_run: When ``True``, only print what would happen without changing
            anything.
        verbose: When ``True``, print a line for each directory that is
            already canonical.

    Returns:
        Exit code: ``0`` on success, ``1`` if any rename failed.
    """
    if not src_dir.is_dir():
        print(f"ERROR: src directory not found: {src_dir}", file=sys.stderr)
        return 1

    renames = _compute_renames(src_dir)

    if not renames:
        print("All directories are already at canonical names. Nothing to do.")
        return 0

    mode_label = "DRY RUN -- " if dry_run else ""
    print(f"{mode_label}{len(renames)} director(y/ies) to rename:\n")

    failures = 0
    for old_path, new_path in renames:
        old_name = old_path.name
        new_name = new_path.name
        print(f"  RENAME: {old_name}  ->  {new_name}")

        if dry_run:
            continue

        if new_path.exists():
            print(
                f"    ERROR: target already exists: {new_path}",
                file=sys.stderr,
            )
            failures += 1
            continue

        try:
            old_path.rename(new_path)
        except OSError as exc:
            print(f"    ERROR renaming directory: {exc}", file=sys.stderr)
            failures += 1
            continue

        # Update package declarations in all .java files
        for java in new_path.glob("*.java"):
            updated = _update_package_declaration(java, old_name, new_name)
            if verbose:
                status = "updated" if updated else "no package line found"
                print(f"    {java.name}: {status}")

    if dry_run:
        print(
            f"\nDry run complete. Run with --execute to apply {len(renames)} rename(s)."
        )
        return 0

    if failures:
        print(f"\n{failures} rename(s) failed. Check errors above.", file=sys.stderr)
        return 1

    print(f"\nMigration complete. {len(renames)} director(y/ies) renamed.")
    print("Run validate_schema.py to confirm no violations remain.")
    return 0


# ---------------------------------------------------------------------------
# Entry point
# ---------------------------------------------------------------------------


def main() -> None:
    """Parse CLI arguments and run the migration."""
    parser = argparse.ArgumentParser(
        description=(
            "Rename src/Q_<n>_<Title> directories to the canonical "
            "Q_<4n>_<Title> schema."
        ),
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog=(
            "Safety:\n"
            "  Only the directory name and the 'package ...;' line are changed.\n"
            "  No implementation code is modified.\n\n"
            "Examples:\n"
            "  py migrate_schema.py --dry-run\n"
            "  py migrate_schema.py --execute\n"
            "  py migrate_schema.py --src-dir C:/path/to/src --dry-run\n"
        ),
    )
    group = parser.add_mutually_exclusive_group(required=True)
    group.add_argument(
        "--dry-run",
        action="store_true",
        help="Preview renames without applying them (safe, no changes made)",
    )
    group.add_argument(
        "--execute",
        action="store_true",
        help="Apply the renames (renames dirs and updates package declarations)",
    )
    parser.add_argument(
        "--src-dir",
        default=str(DEFAULT_SRC_DIR),
        help=f"Path to the src/ directory to migrate (default: {DEFAULT_SRC_DIR})",
    )
    parser.add_argument(
        "-v",
        "--verbose",
        action="store_true",
        help="Print a status line for each .java file processed",
    )
    args = parser.parse_args()
    sys.exit(
        migrate(
            src_dir=Path(args.src_dir),
            dry_run=args.dry_run,
            verbose=args.verbose,
        )
    )


if __name__ == "__main__":
    main()
