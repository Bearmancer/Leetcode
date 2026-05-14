# AGENTS.md — AI Assistant Guidelines

## Core Rule: DO NOT Modify Solution Logic

This project contains LeetCode solution implementations. The AI assistant is strictly forbidden from modifying, fixing,
or altering any solution code.

### Explicitly Forbidden Operations

- ❌ Fixing logical errors of solutions
- ❌ Adding missing method implementations

### Allowed Operations

- ✅ Modifying Javadoc comment blocks `/** ... */` only
- ✅ Adding or fixing Javadoc documentation
- ✅ Adjusting HTML formatting within Javadoc tags
- ✅ Removing or adding file-level comments that are NOT part of the solution code

### Verification

Before submitting any change, verify:

1. Only Javadoc blocks were modified
2. No solution logic was touched
3. The class structure, methods, and algorithm remain identical to the original

### Enforcement

Any attempt to modify solution logic must be rejected. If the Javadoc update requires understanding the solution to
document it correctly, use only the LeetCode problem description (scraped from the API) as the source of truth — never
infer or generate solution-specific documentation.
