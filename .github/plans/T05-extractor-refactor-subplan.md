# T05 — Refactor extractor branding, default returns, and shared bs4 formatting

_Plan date: 2026-04-11_

## Current State

- `leetcode-extractor/leetcode_scraper.py` has `_category_dir_name(category)` and `_dir_name(problem)` wrapper methods that delegate to `shared.py` functions and add no logic.
- `leetcode-extractor/leetcode_rip.py` has `_dir_name(pid, title)` wrapper method with a docstring block but no implementation logic beyond delegating to `canonical_dir_name`.
- `shared.py` exports: `canonical_dir_name`, `shared_category_dir_name`, `build_html_javadoc`, `extract_example_blocks`, `synthesize_default_java_returns`.
- Branding references to "neetcode" may appear in comments or output strings.

## Sub-Tasks

1. **Delete** `_category_dir_name()` method from `leetcode_scraper.py`; **inline** the body as `shared_category_dir_name(category, CATEGORY_DIR_NAMES)` at the `cat_dir` assignment site — verify `Select-String -Path 'leetcode_scraper.py' -Pattern 'def _category_dir_name'` returns zero matches.
2. **Delete** `_dir_name()` method from `leetcode_scraper.py`; **inline** as `canonical_dir_name(problem["id"], problem["title"])` at all call sites — verify `Select-String -Path 'leetcode_scraper.py' -Pattern 'def _dir_name'` returns zero matches.
3. **Delete** `_dir_name()` method from `leetcode_rip.py` including its docstring block; **inline** as `canonical_dir_name(pid, title)` and `canonical_dir_name(problem["id"], problem["title"])` at respective call sites — verify `Select-String -Path 'leetcode_rip.py' -Pattern 'def _dir_name'` returns zero matches.
4. **Verify** `from shared import` in both `leetcode_scraper.py` and `leetcode_rip.py` covers `canonical_dir_name`, `shared_category_dir_name`, `build_html_javadoc`, `extract_example_blocks`, `synthesize_default_java_returns` — run `Select-String -Pattern 'from shared import' -Path 'leetcode_scraper.py','leetcode_rip.py'` and confirm matches in both.
5. **Run** `uv run pytest` — verify exit code `0` and stdout contains `7 passed`.
6. **Run** `black --check .` inside `leetcode-extractor/` — verify exit code `0`.

## Success Criteria

```
{ "command": "Select-String -Path 'C:\\Users\\Lance\\Desktop\\Leetcode\\leetcode-extractor\\leetcode_scraper.py' -Pattern 'def _category_dir_name|def _dir_name' | Measure-Object | Select-Object -ExpandProperty Count", "expected": "0" }
{ "command": "Select-String -Path 'C:\\Users\\Lance\\Desktop\\Leetcode\\leetcode-extractor\\leetcode_rip.py' -Pattern 'def _dir_name' | Measure-Object | Select-Object -ExpandProperty Count", "expected": "0" }
{ "command": "cd 'C:\\Users\\Lance\\Desktop\\Leetcode\\leetcode-extractor'; uv run pytest", "expected_exit_code": 0 }
{ "command": "cd 'C:\\Users\\Lance\\Desktop\\Leetcode\\leetcode-extractor'; black --check .", "expected_exit_code": 0 }
```

## Predecessors

T06

## Successors

T07

## Research Inputs

none — wrapper presence confirmed by direct `Select-String` before deletion.
