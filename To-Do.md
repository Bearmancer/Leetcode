# To-Do.md — Consolidated Backlog

_Last amended: 2026-04-15_

> Canonical per-repo to-do list. See `.github/plans/plan.md` for full critical-path details.
> Global tasks live in `~/.copilot/.github/plans/plan.md`.

---

## In Progress

| #   | Task | Priority | Notes                                     |
| --- | ---- | -------- | ----------------------------------------- |
| —   | —    | —        | All repo tasks completed as of 2026-04-15 |

---

## Pending — This Repo (leetcode)

| #   | Task                                                                                    | Priority | Depends On                             |
| --- | --------------------------------------------------------------------------------------- | -------- | -------------------------------------- |
| —   | —                                                                                       | —        | All repo tasks completed as of 2026-04-15 |
| R04 | Remove backward-compat wrapper methods from `leetcode_scraper.py` and `leetcode_rip.py` | ~~P1~~ | — | **done** |
| R05 | Run `uv run pytest` in `leetcode-extractor/` to confirm 7/7 still pass after R04        | ~~P1~~ | R04 | **done** |
| R06 | Run `black --check .` in `leetcode-extractor/` and fix if non-zero                      | ~~P1~~ | R04, R05 | **done** |
| R07 | Suggest push message and stage extractor repo                                           | P2       | R01–R06                                |

---

## Pending — Global (`~/.copilot`)

| #   | Task                                                                    | Priority | Plan ID                         | Notes                       |
| --- | ----------------------------------------------------------------------- | -------- | ------------------------------- | --------------------------- |
| G01 | Fix remaining MCP configs with `ctx7` (see T-MCP-FIX-EXPAND)            | P0       | T-MCP-FIX-EXPAND                | `~/.gemini/mcp_config.json` |
| G02 | Enforce Explore → Research → Plan → Prompt → Delegation in Orchestrator | P0       | T-SEQUENCE-GATE                 | —                           |
| G03 | Update Orchestrator CPM adherence rules (§CPM-RULES)                    | P0       | T-CPM-ENFORCE                   | —                           |
| G04 | Add §SUBPLAN-MANDATE to Planner instructions                            | P0       | T-PLANNER-SUBPLAN               | G03                         |
| G05 | Populate empty JetBrains global instruction files                       | P2       | T-COPILOT-INSTRUCTIONS-POPULATE | —                           |
| G06 | Fix Crush model pointing at OpenAI                                      | P0       | T-CRUSH-MODEL-FIX               | —                           |
| G07 | Fix JetBrains AI Assistant quota error state                            | P0       | T-JB-QUOTA-FIX                  | —                           |
| G08 | Canonicalize artifact roots and retire duplicates                       | P0       | T01                             | —                           |
| G09 | Normalize hook runtime compatibility                                    | P0       | T02                             | G08                         |
| G10 | Consolidate artifact families into canonical outputs                    | P0       | T04                             | G09                         |
| G11 | Run end-to-end validation and publish verification report               | P0       | T06                             | G10                         |

---

## Completed (this session)

| #   | Task                                                                                 | Completed  |
| --- | ------------------------------------------------------------------------------------ | ---------- |
| ✓   | Fix Context7 MCP banner crash (`ctx7` → `npx`)                                       | 2026-04-11 |
| ✓   | Create `python.instructions.md` (§UV, §FORMAT, §TYPES, §TESTING, §LEGACY)            | 2026-04-11 |
| ✓   | Expand + rename global hook: `git-commit-blocker.json` → `command-policy.json`       | 2026-04-11 |
| ✓   | Update §QA one-liner: name `vscode_askQuestions` tool explicitly in global instructions | 2026-04-11 |
| ✓   | Revert over-edits to §INIT and §HOOKS in global instructions                            | 2026-04-11 |
| ✓   | R04: Removed `_category_dir_name` (scraper) and `_dir_name` (both) backward-compat wrappers | 2026-04-11 |
| ✓   | R05: 7/7 tests pass after wrapper removal                                            | 2026-04-11 |
| ✓   | R06: `black --check` passes on all 4 Python files after wrapper removal              | 2026-04-11 |
| ✓   | Update global `plan.md` and repo `plan.md` with T07 + Agent Tasks section            | 2026-04-11 |
| ✓   | Extractor refactor: removed `Any`, typed dicts, shared BS4 walk (prior session)      | 2026-04-11 |
| ✓   | 7/7 tests passing in `leetcode-extractor/tests/`                                     | 2026-04-11 |
| ✓   | R01: Moved extractor to standalone repo `Bearmancer/Leetcode-Ripper`                | 2026-04-15 |
| ✓   | R02: Removed package-repo artifacts (`.idea/`, `Leetcode.iml`)                      | 2026-04-15 |
| ✓   | R03: Added Python `.gitignore` to standalone extractor repo                         | 2026-04-15 |
