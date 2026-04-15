package Q_0010_Regular_Expression_Matching;

/**
 * <h2><a href="https://leetcode.com/problems/regular-expression-matching/">LeetCode #10: Regular Expression Matching</a></h2>
 * <p><b>Difficulty:</b> Hard &nbsp;|&nbsp; <b>Category:</b> 2-D Dynamic Programming</p>
 * <p><b>NeetCode:</b> <a href="https://neetcode.io/problems/regular-expression-matching">neetcode.io/problems/regular-expression-matching</a></p>
 *
 * <h3>Problem</h3>
 * <p>Given an input string <code>s</code> and a pattern <code>p</code>, implement regular expression matching with support for <code>'.'</code> and <code>'*'</code> where:</p>
 * <ul>
 * <li><code>'.'</code> Matches any single character.​​​​</li>
 * <li><code>'*'</code> Matches zero or more of the preceding element.</li>
 * </ul>
 * <p>Return a boolean indicating whether the matching covers the entire input string (not partial).</p>
 * <p> </p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre>
 * <strong>Input:</strong> s = "aa", p = "a"
 * <strong>Output:</strong> false
 * <strong>Explanation:</strong> "a" does not match the entire string "aa".
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre>
 * <strong>Input:</strong> s = "aa", p = "a*"
 * <strong>Output:</strong> true
 * <strong>Explanation:</strong> '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre>
 * <strong>Input:</strong> s = "ab", p = ".*"
 * <strong>Output:</strong> true
 * <strong>Explanation:</strong> ".*" means "zero or more (*) of any character (.)".
 * </pre>
 * <p> </p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 20</code></li>
 * <li><code>1 &lt;= p.length &lt;= 20</code></li>
 * <li><code>s</code> contains only lowercase English letters.</li>
 * <li><code>p</code> contains only lowercase English letters, <code>'.'</code>, and <code>'*'</code>.</li>
 * <li>It is guaranteed for each appearance of the character <code>'*'</code>, there will be a previous valid character to match.</li>
 * </ul>
 */

class Solution {
    public boolean isMatch(String s, String p) {
        
            return false;
    }
}
