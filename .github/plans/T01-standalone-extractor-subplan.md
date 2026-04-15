# T01 — Create standalone Python extractor project from `neetcode 150/`

_Plan date: 2026-04-11_

## Current State

- `neetcode 150/` folder holds `leetcode_scraper.py`, `leetcode_rip.py`, `shared.py` as ad hoc scripts — no project metadata, no build backend, no package boundary.
- No `pyproject.toml`, no `tests/` directory, no formatter config.
- All three files are importable only if the caller is in the same directory.

## Sub-Tasks

1. **Create** `leetcode-extractor/pyproject.toml` with `[project]` name/version/dependencies, `[tool.black]` line-length config, and `[tool.pytest.ini_options]` testpaths — verify with `Test-Path 'C:\Users\Lance\Desktop\Leetcode\leetcode-extractor\pyproject.toml'` returns `True`.
2. **Move** `neetcode 150/leetcode_scraper.py` → `leetcode-extractor/leetcode_scraper.py` — verify `Test-Path 'neetcode 150\leetcode_scraper.py'` returns `False` and `Test-Path 'leetcode-extractor\leetcode_scraper.py'` returns `True`.
3. **Move** `neetcode 150/leetcode_rip.py` → `leetcode-extractor/leetcode_rip.py` — same before/after `Test-Path` verification pattern.
4. **Move** `neetcode 150/shared.py` → `leetcode-extractor/shared.py` — verify `Test-Path 'leetcode-extractor\shared.py'` returns `True`.
5. **Create** `leetcode-extractor/tests/` directory and stub `test_extractor_refactor.py` — verify `Test-Path 'leetcode-extractor\tests\test_extractor_refactor.py'` returns `True`.
6. **Delete** residual `neetcode 150/` folder after all files moved — verify `Test-Path 'neetcode 150\'` returns `False`.

## Success Criteria

```
{ "command": "Test-Path 'C:\\Users\\Lance\\Desktop\\Leetcode\\leetcode-extractor\\pyproject.toml'", "expected": "True" }
{ "command": "Test-Path 'C:\\Users\\Lance\\Desktop\\Leetcode\\neetcode 150\\'", "expected": "False" }
{ "command": "Test-Path 'C:\\Users\\Lance\\Desktop\\Leetcode\\leetcode-extractor\\shared.py'", "expected": "True" }
```

## Predecessors

none

## Successors

T02

## Research Inputs

none — structure determined from direct workspace inspection.
