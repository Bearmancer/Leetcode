package Q_0020_Valid_Parentheses;

/**
 * <h2><a href="https://leetcode.com/problems/valid-parentheses/">LeetCode #20: Valid Parentheses</a></h2>
 * <p><b>Difficulty:</b> Easy &nbsp;|&nbsp; <b>Category:</b> Stack</p>
 * <p><b>NeetCode:</b> <a href="https://neetcode.io/problems/valid-parentheses">https://neetcode.io/problems/valid-parentheses</a></p>
 *
 * <h3>Problem</h3>
 * <p>Given a string <code>s</code> containing just the characters <code>'('</code>, <code>')'</code>, <code>'{'</code>, <code>'}'</code>, <code>'['</code> and <code>']'</code>, determine if the input string is valid.</p>
 * <p>An input string is valid if:</p>
 * <ol>
 * <li>Open brackets must be closed by the same type of brackets.</li>
 * <li>Open brackets must be closed in the correct order.</li>
 * <li>Every close bracket has a corresponding open bracket of the same type.</li>
 * </ol>
 * <p> </p>
 * <p><strong class="example">Example 1:</strong></p>
 *
 * <p><strong>Input:</strong> <span class="example-io">s = "()"</span></p>
 * <p><strong>Output:</strong> <span class="example-io">true</span></p>
 *
 * <p><strong class="example">Example 2:</strong></p>
 *
 * <p><strong>Input:</strong> <span class="example-io">s = "()[]{}"</span></p>
 * <p><strong>Output:</strong> <span class="example-io">true</span></p>
 *
 * <p><strong class="example">Example 3:</strong></p>
 *
 * <p><strong>Input:</strong> <span class="example-io">s = "(]"</span></p>
 * <p><strong>Output:</strong> <span class="example-io">false</span></p>
 *
 * <p><strong class="example">Example 4:</strong></p>
 *
 * <p><strong>Input:</strong> <span class="example-io">s = "([])"</span></p>
 * <p><strong>Output:</strong> <span class="example-io">true</span></p>
 *
 * <p><strong class="example">Example 5:</strong></p>
 *
 * <p><strong>Input:</strong> <span class="example-io">s = "([)]"</span></p>
 * <p><strong>Output:</strong> <span class="example-io">false</span></p>
 *
 * <p> </p>
 * <p><strong>Constraints:</strong></p>
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
