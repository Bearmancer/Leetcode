# Copilot instructions

## Repository overview
- `../leetcode-extractor/` is the standalone Python extraction project. Treat it as a sibling repo used to fetch LeetCode problem metadata and generate Java stubs.
- `src/Q_<4-digit-id>_<Title>/...` is the canonical Java solution tree.
- `test/src/` is a scratch area for experiments and local runners; do not normalize it unless the task is explicitly about that folder.

## Extractor repo shape
- `../leetcode-extractor/leetcode_rip.py` is the preferred package-based Java stub entrypoint for canonical `src/` output.
- `../leetcode-extractor/leetcode_scraper.py` is the preferred flat-export entrypoint for category output.
- `../leetcode-extractor/shared.py` contains the shared naming, HTML cleanup, example extraction, and stub classification helpers.
- `../leetcode-extractor/tests/` contains stdlib `unittest` regression checks. Prefer adding or updating tests there when you touch extractor logic.
- `../leetcode-extractor/pyproject.toml` defines the standalone Python project metadata and runtime dependencies.

## Commands
- Run the rip workflow:
  - `py "..\leetcode-extractor\leetcode_rip.py" --verbose`
  - `py "..\leetcode-extractor\leetcode_rip.py" --category "Arrays & Hashing" --force`
- Run the flat export workflow:
  - `py "..\leetcode-extractor\leetcode_scraper.py" --force --verbose`
  - `py "..\leetcode-extractor\leetcode_scraper.py" --category "Arrays & Hashing" --force`
- Run the Python preliminary checks:
  - `py -m unittest discover -s "..\leetcode-extractor\tests" -p "test_*.py" -v`
- Validate canonical Java package naming:
  - `py "scripts/validate_schema.py"`
- Preview or apply Java directory/package migration:
  - `py "scripts/migrate_schema.py" --dry-run`
  - `py "scripts/migrate_schema.py" --execute`

## Naming rules
- Preserve Roman numerals as `II`, `III`, `IV`, `VI`, `VII`, `VIII`, `IX`, `XI`, and `XII`.
- Preserve readability for common acronyms such as `LRU`, `LFU`, `BST`, `API`, `SQL`, `URL`, `CPU`, `DFS`, and `DP`.
- Do not collapse related-but-distinct problems. `Q_0062_Unique_Paths` and `Q_0063_Unique_Paths_II` are different questions.
- When a package or directory name changes, update both the directory name and the first `package ...;` line in the Java file.

## Editing guidelines
- Keep CLI flags stable for both extractor entry points: `--output-dir`, `--delay`, `--force`, `--category`, and `--verbose`.
- Prefer extending `shared.py` over duplicating naming or HTML logic in both scripts.
- Derive smoke-test fixtures from real LeetCode HTML/examples whenever practical instead of inventing synthetic cases.
- Keep Python changes minimal and explicit; avoid magic strings when a shared constant or helper can hold the rule.
- Preserve the current Java file layout: public API first, helpers below, and local node/helper classes in the same file.

## Validation expectations
- After changing the extractor logic, run the unittest suite in `../leetcode-extractor/tests/`.
- After changing Java package naming or directory names, run `scripts/validate_schema.py`.
- If you rename generated packages, re-run the migration/validation pass before finishing.
