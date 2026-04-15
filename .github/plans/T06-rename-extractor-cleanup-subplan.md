# T06 — Rename `neetcode 150/` to `leetcode-extractor/` and clean generated leftovers

_Plan date: 2026-04-11_

## Current State

- `neetcode 150/` is the extractor directory with Python source, tests, and generated/stale subdirectories.
- Stale artifacts present: `04_stack/` (duplicate Java in wrong location), `DSA-Vault/` (stale markdown study notes), `AGENTS.md` (duplicated to `.kilo/agents/`), `__pycache__/` (generated).
- All Python source has been migrated under the new name as part of T01.

## Sub-Tasks

1. **Rename** `neetcode 150/` to `leetcode-extractor/` using `Rename-Item -Path 'neetcode 150' -NewName 'leetcode-extractor'` — verify `Test-Path 'neetcode 150\'` returns `False` and `Test-Path 'leetcode-extractor\'` returns `True`.
2. **Delete** `leetcode-extractor/04_stack/` — verify `Test-Path 'leetcode-extractor\04_stack'` returns `False`.
3. **Delete** `leetcode-extractor/DSA-Vault/` — verify `Test-Path 'leetcode-extractor\DSA-Vault'` returns `False`.
4. **Delete** `leetcode-extractor/AGENTS.md` — verify `Test-Path 'leetcode-extractor\AGENTS.md'` returns `False`.
5. **Delete** `leetcode-extractor/__pycache__/` — verify `Test-Path 'leetcode-extractor\__pycache__'` returns `False`.
6. **Run** `uv run pytest` inside `leetcode-extractor/` — verify exit code `0` and stdout contains `7 passed`.

## Success Criteria

```
{ "command": "Test-Path 'C:\\Users\\Lance\\Desktop\\Leetcode\\leetcode-extractor'", "expected": "True" }
{ "command": "Test-Path 'C:\\Users\\Lance\\Desktop\\Leetcode\\neetcode 150'", "expected": "False" }
{ "command": "Get-ChildItem 'C:\\Users\\Lance\\Desktop\\Leetcode\\leetcode-extractor' | Where-Object { $_.Name -in '04_stack','DSA-Vault','AGENTS.md','__pycache__' } | Measure-Object | Select-Object -ExpandProperty Count", "expected": "0" }
{ "command": "cd 'C:\\Users\\Lance\\Desktop\\Leetcode\\leetcode-extractor'; uv run pytest", "expected_exit_code": 0 }
```

## Predecessors

none

## Successors

T05

## Research Inputs

none — artifact inventory verified by direct `Get-ChildItem` inspection before deletion.
