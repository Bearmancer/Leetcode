# T04 — Remove verified duplicate folders and re-validate Java naming/package invariants

_Plan date: 2026-04-11_

## Current State

- `test/src/` contains flat Java files and some Q_* folders that may duplicate `src/Q_*/` equivalents.
- `neetcode 150/tests/` exists but is expected to be empty after T01 migration.
- `neetcode 150/__pycache__/` is generated interpreter output.
- Package declarations in `src/Q_*/` must match directory names; mismatched `Ii`/`Iii` suffixes have been observed historically.

## Sub-Tasks

1. **Compare** `test/src/Q_155_Min_Stack/` and `test/src/Q_One_Hundred_Fifty_Five_Min_Stack/` against `src/Q_0155_Min_Stack/` using `git diff --stat` or file content comparison — record decision (duplicate / keep) in `.github/notes/duplicate-folder-audit.txt`.
2. **Delete** `neetcode 150/tests/` after confirming `(Get-ChildItem 'neetcode 150\tests' -Recurse).Count -eq 0` — verify `Test-Path 'neetcode 150\tests'` returns `False`.
3. **Delete** `neetcode 150/__pycache__/` as generated output — verify `Test-Path 'neetcode 150\__pycache__'` returns `False`.
4. **Remove** confirmed-redundant `test/src/Q_*` folders — verify `(Get-ChildItem src -Recurse -Filter '*.java' | Where-Object { $_.FullName -match 'Q_0062' -or $_.FullName -match 'Q_0063' }).Count -ge 2` to confirm Unique_Paths variants survive.
5. **Run** duplicate-package scan: `Select-String -Path 'src\**\*.java' -Pattern 'package.*[Ii]{2}' -Recurse` — verify zero matches.
6. **Run** schema validation via `uv run python scripts/validate_schema.py` or equivalent — verify exit code `0`.

## Success Criteria

```
{ "command": "Select-String -Path 'C:\\Users\\Lance\\Desktop\\Leetcode\\src\\**\\*.java' -Pattern 'package.*[Ii]{2}' -Recurse | Measure-Object | Select-Object -ExpandProperty Count", "expected": "0" }
{ "command": "Test-Path 'C:\\Users\\Lance\\Desktop\\Leetcode\\neetcode 150\\'", "expected": "False" }
```

## Predecessors

T03

## Successors

none

## Research Inputs

none — duplicate candidates identified from direct workspace inventory.
