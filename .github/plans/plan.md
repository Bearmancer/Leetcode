# plan.md — Authoritative Execution Plan

_Last amended: 2026-04-11_

> Execution finished for the prior task set. The new LeetCode extractor refactor request is tracked below as T05 without altering the completed history.
> The workspace consolidation follow-up is tracked below as T06 without altering the completed history.

## Critical Path

> Tasks ordered by float (ascending). Float = 0 means on the critical path.

| #   | ID  | Title | Tier | Float | Issue | Status |
| --- | --- | ----- | ---- | ----- | ----- | ------ |
| 1   | T01 | Create standalone Python extractor project from `neetcode 150/` | P0 | 0 | — | completed |
| 2   | T02 | Add example-driven tests and rewrite Python-first repo instructions | P0 | 0 | — | completed |
| 3   | T03 | Copy the rich repository into `~/Dev` and capture unreachable git history | P0 | 0 | — | completed |
| 4   | T04 | Remove verified duplicate folders and re-validate Java naming/package invariants | P0 | 0 | — | completed |
| 5   | T06 | Rename `neetcode 150/` to `leetcode-extractor/` and clean generated leftovers | P0 | 0 | — | completed |
| 6   | T05 | Refactor extractor branding, default returns, and shared bs4 formatting | P0 | 0 | — | completed |
| 7   | T07 | Instruction hygiene: Python rules file, JSON hook consolidation, Context7 fix | P1 | 1 | — | completed |

## Task Details

### T01 · Create standalone Python extractor project from `neetcode 150/`

**Tier**: P0 · **Float**: 0 · **Issue**: — · **Status**: completed

**Sub-plan**: `.github/plans/T01-standalone-extractor-subplan.md`

**Predecessors**: none
**Successors**: T02

---

### T02 · Add example-driven tests and rewrite Python-first repo instructions

**Tier**: P0 · **Float**: 0 · **Issue**: — · **Status**: completed

**Sub-plan**: `.github/plans/T02-tests-instructions-subplan.md`

**Predecessors**: T01
**Successors**: T03

---

### T03 · Copy the rich repository into `~/Dev` and capture unreachable git history

**Tier**: P0 · **Float**: 0 · **Issue**: — · **Status**: completed

**Sub-plan**: `.github/plans/T03-dev-copy-git-history-subplan.md`

**Predecessors**: T02
**Successors**: T04

---

### T04 · Remove verified duplicate folders and re-validate Java naming/package invariants

**Tier**: P0 · **Float**: 0 · **Issue**: — · **Status**: completed

**Sub-plan**: `.github/plans/T04-duplicate-removal-java-subplan.md`

**Predecessors**: T03
**Successors**: none

---

### T06 · Rename `neetcode 150/` to `leetcode-extractor/` and clean generated leftovers

**Tier**: P0 · **Float**: 0 · **Issue**: — · **Status**: completed

**Sub-plan**: `.github/plans/T06-rename-extractor-cleanup-subplan.md`

**Predecessors**: none
**Successors**: T05

---

### T05 · Refactor extractor branding, default returns, and shared bs4 formatting

**Tier**: P0 · **Float**: 0 · **Issue**: — · **Status**: completed

**Sub-plan**: `.github/plans/T05-extractor-refactor-subplan.md`

**Predecessors**: T06
**Successors**: T07

---

### T07 · Instruction hygiene: Python rules file, JSON hook consolidation, Context7 fix

**Tier**: P1 · **Float**: 1 · **Issue**: — · **Status**: completed

**Sub-plan**: `.github/plans/T07-instruction-hygiene-subplan.md`

**Predecessors**: T05
**Successors**: none

---
