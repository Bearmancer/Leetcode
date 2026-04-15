# T03 — Copy the rich repository into `~/Dev` and capture unreachable git history

_Plan date: 2026-04-11_

## Current State

- Canonical working copy lives at `C:\Users\Lance\Desktop\Leetcode\`.
- `C:\Users\Lance\Dev\` either empty or contains an older partial copy.
- Git object database at `Desktop/Leetcode/.git/` may have unreachable blobs from prior squash/rebase operations not captured elsewhere.
- `test/src/MinStack.java` and `test/src/topkelements.java` are explicitly called out as must-preserve files.

## Sub-Tasks

1. **Run** `git fsck --full --dangling --unreachable --no-reflogs` inside `C:\Users\Lance\Desktop\Leetcode` and **write** output to `C:\Users\Lance\Desktop\Leetcode\.github\notes\git-fsck-pre-copy.txt` — verify `Test-Path '.github\notes\git-fsck-pre-copy.txt'` returns `True`.
2. **Inventory** `Desktop/Leetcode/src/` with `(Get-ChildItem src -Recurse -Filter '*.java').Count` and write count to `C:\Users\Lance\Desktop\Leetcode\.github\notes\pre-copy-src-inventory.txt` — verify file exists and is non-empty.
3. **Copy** `C:\Users\Lance\Desktop\Leetcode\` to `C:\Users\Lance\Dev\Leetcode-canonical-20260410\` using `Copy-Item -Path . -Destination ..\..\Dev\Leetcode-canonical-20260410 -Recurse` — verify `Test-Path 'C:\Users\Lance\Dev\Leetcode-canonical-20260410\leetcode-extractor\pyproject.toml'` returns `True`.
4. **Verify** `Test-Path 'C:\Users\Lance\Dev\Leetcode-canonical-20260410\test\src\MinStack.java'` returns `True`.
5. **Verify** `Test-Path 'C:\Users\Lance\Dev\Leetcode-canonical-20260410\test\src\topkelements.java'` returns `True`.
6. **Run** `uv run pytest` inside `C:\Users\Lance\Dev\Leetcode-canonical-20260410\leetcode-extractor\` — verify exit code `0`.

## Success Criteria

```
{ "command": "Test-Path 'C:\\Users\\Lance\\Dev\\Leetcode-canonical-20260410\\leetcode-extractor\\pyproject.toml'", "expected": "True" }
{ "command": "Test-Path 'C:\\Users\\Lance\\Dev\\Leetcode-canonical-20260410\\test\\src\\MinStack.java'", "expected": "True" }
{ "command": "Test-Path 'C:\\Users\\Lance\\Desktop\\Leetcode\\.github\\notes\\git-fsck-pre-copy.txt'", "expected": "True" }
```

## Predecessors

T02

## Successors

T04

## Research Inputs

none — paths determined from direct workspace inspection.
