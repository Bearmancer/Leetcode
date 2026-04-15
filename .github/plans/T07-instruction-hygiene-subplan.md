# T07 — Instruction hygiene: Python rules file, JSON hook consolidation, Context7 fix

_Plan date: 2026-04-11_

## Current State

- `%APPDATA%\Code - Insiders\User\mcp.json` uses `"command": "ctx7"` which outputs an ANSI banner before entering JSON-RPC mode, crashing the MCP connection.
- `~/.copilot/hooks/git-commit-blocker.json` covers only git history mutation; uses incorrect tool name string instead of array; does not cover destructive filesystem ops or safety-bypass flags.
- No Python-specific instruction file exists at `~/.copilot/instructions/python.instructions.md`.
- `copilot-instructions.instructions.md` §QA section does not name `vscode_askQuestions` explicitly.

## Sub-Tasks

1. **Create** `C:\Users\Lance\.copilot\instructions\python.instructions.md` with `applyTo: "**/*.py"` frontmatter and sections §UV, §FORMAT, §TYPES, §TESTING, §LEGACY — verify `Select-String -Path 'C:\Users\Lance\.copilot\instructions\python.instructions.md' -Pattern '§UV|§FORMAT|§TYPES' | Measure-Object | Select-Object -ExpandProperty Count` returns `3`.
2. **Modify** `%APPDATA%\Code - Insiders\User\mcp.json`: replace `"command": "ctx7"` entry with `"command": "npx", "args": ["-y", "@upstash/context7-mcp@latest"]` — verify `Get-Content 'mcp.json' | ConvertFrom-Json` exits `0` and `Select-String -Path 'mcp.json' -Pattern '"command": "ctx7"'` returns zero matches.
3. **Rename** `~/.copilot/hooks/git-commit-blocker.json` → `command-policy.json`; **add** rules for `block-destructive-filesystem` and `block-safety-bypass-flags`; **fix** `"tool"` field to array `["run_in_terminal", "Bash"]` — verify `Get-Content 'command-policy.json' | ConvertFrom-Json` exits `0` and `Select-String -Pattern 'block-destructive-filesystem'` returns a match.
4. **Modify** `~/.copilot/instructions/copilot-instructions.instructions.md` §QA: add sentence naming `vscode_askQuestions` as the required tool for disambiguation — verify `Select-String -Path 'copilot-instructions.instructions.md' -Pattern 'vscode_askQuestions'` returns a match.
5. **Update** repo `plan.md` CPM table: add T07 row with `P1, float=1, completed`; **update** global `plan.md` with T-CONTEXT7-FIX, T-PYTHON-RULES-FILE, T-HOOK-CONSOLIDATE, T-BLACK-ENFORCE-RULE, T-HOOK-VERIFY, T-AGENT-TASKS-SECTION rows — verify each task ID appears in their respective plan files via `Select-String`.
6. **Create** `To-Do.md` at repo root with R01–R07 pending rows and G01–G11 global rows — verify `Test-Path 'C:\Users\Lance\Desktop\Leetcode\To-Do.md'` returns `True` and `(Select-String -Path 'To-Do.md' -Pattern '^| R').Count -ge 7`.

## Success Criteria

```
{ "command": "Test-Path 'C:\\Users\\Lance\\.copilot\\instructions\\python.instructions.md'", "expected": "True" }
{ "command": "Select-String -Path 'C:\\Users\\Lance\\.copilot\\hooks\\command-policy.json' -Pattern 'block-destructive-filesystem'", "expected_exit_code": 0 }
{ "command": "Get-Content 'C:\\Users\\Lance\\.copilot\\hooks\\command-policy.json' | ConvertFrom-Json", "expected_exit_code": 0 }
{ "command": "Select-String -Path 'C:\\Users\\Lance\\.copilot\\instructions\\copilot-instructions.instructions.md' -Pattern 'vscode_askQuestions'", "expected_exit_code": 0 }
```

## Predecessors

T05

## Successors

none

## Research Inputs

none — file paths verified by direct `Test-Path` inspection before modification.
