# Leetcode workspace refactor research

## Workspace-verified current state

- Repository root `.` contains `.git/`, `.github/`, `neetcode 150/`, `scripts/`, `src/`, `test/`, and generated output `out/`.
- `neetcode 150/` currently contains `AGENTS.md`, `DSA-Vault/`, `neetcode150_rip.py`, `neetcode150_scraper.py`, an empty `neetcode150_test/`, and generated `__pycache__/`.
- `neetcode 150/neetcode150_scraper.py` is a monolithic Python CLI that fetches LeetCode GraphQL data and writes Java files grouped by category directories such as `01_arrays_and_hashing/`; its CLI flags are `--output-dir`, `--delay`, `--force`, `--category`, and `--verbose`.
- `neetcode 150/neetcode150_rip.py` is a second monolithic Python CLI that writes directly into `src/Q_<4-digit>_<Title_Case>/...` and already contains Roman numeral preservation helpers (`_ROMAN_RE`, `_is_roman_numeral`, `_title_segment_to_words`).
- `scripts/validate_schema.py` enforces the canonical `src/Q_<4-digit>_<Title_Case_Words_Digit_Spelled_Out>/` naming scheme, matching `package <dir_name>;`, presence of a LeetCode URL in JavaDoc, and 4-digit zero padding. Its helper also preserves Roman numerals.
- `src/` already uses the zero-padded canonical schema. Verified examples include `src/Q_0062_Unique_Paths/` and `src/Q_0063_Unique_Paths_II/`.
- `src/Q_0062_Unique_Paths/Solution.java` defines `uniquePaths(int m, int n)` for LeetCode #62. `src/Q_0063_Unique_Paths_II/Solution.java` defines `uniquePathsWithObstacles(int[][] obstacleGrid)` for LeetCode #63. These are related problems, not duplicates.
- `src/` already preserves Roman numerals in package and directory names, for example `Q_0045_Jump_Game_II`, `Q_0123_Best_Time_To_Buy_And_Sell_Stock_III`, `Q_0213_House_Robber_II`, and `Q_0518_Coin_Change_II`.
- `test/src/` contains legacy and experimental Java artifacts: flat files `MinStack.java` and `topkelements.java`, plus legacy directories `Q_155_Min_Stack/`, `Q_One_Hundred_Fifty_Five_Min_Stack/`, and `Q_Three_Hundred_Forty_Seven_Top_K_Frequent_Elements/`.
- Canonical equivalents already exist in `src/`: `src/Q_0155_Min_Stack/MinStack.java` and `src/Q_0347_Top_K_Frequent_Elements/Solution.java`.
- `test/src/MinStack.java` and `test/src/Q_155_Min_Stack/MinStack.java` contain older Min Stack implementations or variants; `test/src/Q_One_Hundred_Fifty_Five_Min_Stack/MinStack.java` and `test/src/Q_Three_Hundred_Forty_Seven_Top_K_Frequent_Elements/Solution.java` are stub-style legacy artifacts.
- `Desktop/Leetcode/.github/copilot-instructions.md` is still Java-centric. It describes `src/Q_<id>_<Title>/...` as canonical, documents `neetcode150_scraper.py` as the source of truth, and treats `test/src/` as an experimental mixed-style area.
- `neetcode 150/AGENTS.md` is repository agent guidance, not editor configuration. It documents run commands, formatting/testing expectations, and contributor conventions for the Python scripts and Java outputs.
- The separate workspace folder `.config/kilo/kilo.jsonc` is the Kilo configuration location; `neetcode 150/AGENTS.md` is not a Kilo config file.
- `Dev/Leetcode/` contains an older Java-only repository with `.git/`, `lib/`, and `src/`. Its `src/` still uses unpadded directory names such as `Q_141_Linked_List_Cycle/`, `Q_143_Reorder_List/`, `Q_19_Remove_Nth_Node_From_Linked_List/`, and `Q_21_Merge_Two_Sorted_Linked_List/`.
- The current Desktop repository already contains canonical zero-padded counterparts for those older Dev directories, so the consolidation path should copy the richer Desktop repository into `~/Dev` instead of merging old Dev source files into Desktop.
- Existing repo planning artifacts include `.github/plans/plan.md`; there is no existing concern-scoped research file for this refactor.
- No root `To-Do.md` file was found.
- No inline `//todo` markers were found in the active repository search scope.

## External evidence

### Python packaging guidance

- The Python Packaging User Guide recommends a simple standalone project layout with `pyproject.toml`, `README.md`, optional `LICENSE`, a `src/<package>/` directory, and a top-level `tests/` directory.
- The guide recommends creating `__init__.py` inside the package directory and storing build metadata in `pyproject.toml` under `[project]` with a declared build backend under `[build-system]`.

### Pytest guidance

- Pytest good practices recommend using a `src/` layout for new projects.
- Pytest supports keeping tests in a top-level `tests/` directory outside the application package.
- Pytest recommends editable installs (`pip install -e .`) for local development and recommends `--import-mode=importlib` for new projects.
- Pytest discovers tests from `tests/` using files named `test_*.py` or `*_test.py` and functions or methods prefixed with `test`.

### Git history audit guidance

- `git fsck` verifies object connectivity and validity.
- `git fsck --unreachable` reports objects that are not reachable from refs.
- `git fsck --dangling` reports objects that are present but never directly used.
- `git fsck --no-reflogs` excludes reflog-only reachability and is the documented way to surface commits that used to be referenced but now only survive in reflogs.
- `git fsck --lost-found` writes dangling objects into `.git/lost-found/` for inspection.

## Project pattern analysis

- The current repository has two Python extraction roles mixed into one folder: `neetcode150_scraper.py` exports category-grouped Java stubs, while `neetcode150_rip.py` writes directly into the Java `src/` tree.
- The current Java repository treats `src/` as the canonical solved/problem tree and `test/src/` as experimental scratch space.
- The empty `neetcode 150/neetcode150_test/` folder and the legacy `test/src/Q_*` folders are already superseded by the canonical `src/` tree.
- Roman numeral preservation is already implemented in code; the refactor should protect that behavior from regression instead of re-solving it blindly.
- The highest-risk operation is repository consolidation from `Desktop/Leetcode` into `Dev/Leetcode` because it crosses repository roots and can accidentally overwrite older local history or omit the two explicitly requested flat Java files.

## Verified implementation guidance

- Treat `neetcode 150/` as the source material for a new standalone Python extractor project, but keep the existing CLI flags and request/backoff behavior stable while modularizing internals.
- Split the standalone Python project into package modules for API access, schema naming, rendering/writing, and CLI entry points so that tests can target small pure functions instead of the whole script.
- Add preliminary Python tests around already-verified behavior: Roman numeral preservation, canonical directory naming, example testcase extraction/parsing, and one or two file-generation smoke checks.
- Prefer tests derived from LeetCode-provided examples already exposed in `question.content` and `question.exampleTestcases` rather than invented fixtures.
- Rewrite `.github/copilot-instructions.md` so it describes the new Python project as the primary concern, while still documenting the Java `src/` tree only where the Python extractor intentionally targets it.
- Safe removal candidates after verification are: `neetcode 150/neetcode150_test/` (empty), `neetcode 150/__pycache__/` (generated), and the legacy duplicate folders under `test/src/` whose canonical equivalents already exist in `src/`.
- Do not classify `neetcode 150/DSA-Vault/` as integrated Java output; keep it unless the user explicitly wants the learning-content docs split or deleted.
- Before any copy into `~/Dev`, record directory inventories, confirm the two requested flat files still exist, and confirm the destination path to avoid destructive path mix-ups.
- After any copy into `~/Dev`, verify Python tests, formatter/linter commands, schema validation for `src/`, and a duplicate-package scan before deleting anything from the source repo.
- Git unreachable-commit inspection remains a command-based audit step because direct terminal execution is not available in this session; the plan must keep it as an explicit verification gate, not an assumed clean state.

## Execution follow-up

- The standalone extractor was completed in-place inside `neetcode 150/` by adding `pyproject.toml`, `shared.py`, and `tests/`, then refactoring both entry-point scripts to use the shared helpers.
- The Python regression suite was completed and passes via `py -m unittest discover -s "neetcode 150/tests" -p "test_*.py" -v`.
- Verified duplicate/generated folders were removed: `neetcode 150/neetcode150_test/`, `neetcode 150/__pycache__/`, and legacy duplicate `test/src/Q_*` folders, while keeping `test/src/MinStack.java` and `test/src/topkelements.java`.
- The naming cleanup finished with readable acronym preservation in canonical `src/`, including `Q_0146_LRU_Cache` and `Q_0230_Kth_Smallest_Element_In_A_BST`.
- `git fsck --full --dangling --unreachable --no-reflogs` found 17 unreachable commits in the Desktop repo and 17 in the older Dev repo. No pruning or history mutation was performed.
- The desired in-place overwrite of `~/Dev/Leetcode` was blocked by a file lock, so the verified canonical copy was created at `~/Dev/Leetcode-canonical-20260410` instead, and `~/Dev/Leetcode-Temp` was also copied successfully.

## Sources

- Workspace files read during research:
  - `.github/copilot-instructions.md`
  - `.github/plans/plan.md`
  - `neetcode 150/AGENTS.md`
  - `neetcode 150/neetcode150_scraper.py`
  - `neetcode 150/neetcode150_rip.py`
  - `scripts/validate_schema.py`
  - `src/Q_0062_Unique_Paths/Solution.java`
  - `src/Q_0063_Unique_Paths_II/Solution.java`
  - `src/Q_0155_Min_Stack/MinStack.java`
  - `src/Q_0347_Top_K_Frequent_Elements/Solution.java`
  - `test/src/MinStack.java`
  - `test/src/topkelements.java`
  - `test/src/Q_155_Min_Stack/MinStack.java`
  - `test/src/Q_One_Hundred_Fifty_Five_Min_Stack/MinStack.java`
  - `test/src/Q_Three_Hundred_Forty_Seven_Top_K_Frequent_Elements/Solution.java`
- External references fetched during research:
  - `https://packaging.python.org/en/latest/tutorials/packaging-projects/`
  - `https://docs.pytest.org/en/stable/explanation/goodpractices.html`
  - `https://git-scm.com/docs/git-fsck`
