package Q_0020_Valid_Parentheses;

/**
 * <h1><a href="https://leetcode.com/problems/valid-parentheses/">LeetCode #20: Valid Parentheses</a></h1>
 * <p><b>Difficulty:</b> Easy &nbsp;|&nbsp; <b>Category:</b> Stack</p>
 *
 * <h2>Problem</h2>
 * <p>Given a string <code>s</code> containing just the characters <code>'('</code>, <code>')'</code>, <code>'{'</code>, <code>'}'</code>, <code>'['</code> and <code>']'</code>, determine if the input string is valid.</p>
 * <p>An input string is valid if:</p>
 * <ol>
 * <li>Open brackets must be closed by the same type of brackets.</li>
 * <li>Open brackets must be closed in the correct order.</li>
 * <li>Every close bracket has a corresponding open bracket of the same type.</li>
 * </ol>
 * <h3>Example 1:</h3>
 *
 * <p><strong>Input:</strong> <span class="example-io">s = "()"</span></p>
 * <p><strong>Output:</strong> <span class="example-io">true</span></p>
 *
 * <h3>Example 2:</h3>
 *
 * <p><strong>Input:</strong> <span class="example-io">s = "()[]{}"</span></p>
 * <p><strong>Output:</strong> <span class="example-io">true</span></p>
 *
 * <h3>Example 3:</h3>
 *
 * <p><strong>Input:</strong> <span class="example-io">s = "(]"</span></p>
 * <p><strong>Output:</strong> <span class="example-io">false</span></p>
 *
 * <h3>Example 4:</h3>
 *
 * <p><strong>Input:</strong> <span class="example-io">s = "([])"</span></p>
 * <p><strong>Output:</strong> <span class="example-io">true</span></p>
 *
 * <h3>Example 5:</h3>
 *
 * <p><strong>Input:</strong> <span class="example-io">s = "([)]"</span></p>
 * <p><strong>Output:</strong> <span class="example-io">false</span></p>
 *
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>s</code> consists of parentheses only <code>'()[]{}'</code>.</li>
 * </ul>
 */

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (isClosingBracket(c)) {
                if (stack.isEmpty()) return false;
                if (!isMatching(stack.pop(), c)) return false;
                continue;
            }
            stack.push(c);
        }

        return stack.isEmpty();
    }

    private static boolean isMatching(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '{' && closing == '}') ||
                (opening == '[' && closing == ']');
    }

    private static boolean isClosingBracket(char c) {
        return c == ']' || c == ')' || c == '}';
    }
}
