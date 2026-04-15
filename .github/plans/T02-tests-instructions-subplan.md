# T02 — Add example-driven tests and rewrite Python-first repo instructions

_Plan date: 2026-04-11_

## Current State

- `leetcode-extractor/tests/` either empty or absent after T01 stub.
- No behavioral tests covering `canonical_dir_name`, `synthesize_default_java_returns`, `build_html_javadoc`, or `extract_example_blocks`.
- `.github/copilot-instructions.md` describes a Java-first workflow with no Python guidance.
- `pyproject.toml` has no `testpaths` entry; pytest must be run with explicit path argument.

## Sub-Tasks

1. **Create** `leetcode-extractor/tests/test_extractor_refactor.py` with ≥7 test functions covering: `canonical_dir_name` zero-padding, `synthesize_default_java_returns` per type, `build_html_javadoc` parity, `extract_example_blocks` parsing — verify `(Select-String -Path 'tests\test_extractor_refactor.py' -Pattern 'def test_').Count -ge 7`.
2. **Run** `uv run pytest` inside `leetcode-extractor/` — verify exit code `0` and stdout contains `7 passed`.
3. **Modify** `.github/copilot-instructions.md` to lead with Python workflow section describing `uv run pytest` and `black .` invocations, and document Java `src/` as the downstream output target — verify `Select-String -Path '.github\copilot-instructions.md' -Pattern 'uv run pytest'` returns a match.
4. **Configure** `[tool.pytest.ini_options]` `testpaths = ["tests"]` in `leetcode-extractor/pyproject.toml` — verify `Select-String -Path 'pyproject.toml' -Pattern 'testpaths'` returns a match.
5. **Verify** at least one test asserts `canonical_dir_name` output against a hardcoded expected value (e.g., `assert canonical_dir_name(1, "Two Sum") == "Q_0001_Two_Sum"`) — verify with `Select-String -Path 'tests\test_extractor_refactor.py' -Pattern 'Q_0001_Two_Sum'` or equivalent fixture value.

## Success Criteria

```
{ "command": "cd leetcode-extractor; uv run pytest", "expected_exit_code": 0 }
{ "text_pattern": "7 passed", "file": "stdout of uv run pytest" }
{ "command": "Select-String -Path 'leetcode-extractor\\tests\\test_extractor_refactor.py' -Pattern 'def test_' | Measure-Object | Select-Object -ExpandProperty Count", "expected": ">= 7" }
```

## Predecessors

T01

## Successors

T03

## Research Inputs

none — test targets determined from inspection of `shared.py` exports.
