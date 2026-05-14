package Q_0076_Minimum_Window_Substring;

/**
 * <h1><a href="https://leetcode.com/problems/minimum-window-substring/">LeetCode #76: Minimum Window Substring</a></h1>
 * <p><b>Difficulty:</b> Hard &nbsp;|&nbsp; <b>Category:</b> Sliding Window</p>
 *
 * <h2>Problem</h2>
 * <p>Given two strings <code>s</code> and <code>t</code> of lengths <code>m</code> and <code>n</code> respectively, return <em>the <strong>minimum window</strong></em> <span data-keyword="substring-nonempty"><strong><em>substring</em></strong></span><em> of </em><code>s</code><em> such that every character in </em><code>t</code><em> (<strong>including duplicates</strong>) is included in the window</em>. If there is no such substring, return <em>the empty string </em><code>""</code>.</p>
 * <p>The testcases will be generated such that the answer is <strong>unique</strong>.</p>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> s = "ADOBECODEBANC", t = "ABC"
 * <strong>Output:</strong> "BANC"
 * <strong>Explanation:</strong> The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> s = "a", t = "a"
 * <strong>Output:</strong> "a"
 * <strong>Explanation:</strong> The entire string s is the minimum window.
 * </pre>
 * <h3>Example 3:</h3>
 * <pre>
 * <strong>Input:</strong> s = "a", t = "aa"
 * <strong>Output:</strong> ""
 * <strong>Explanation:</strong> Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * </pre>
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>m == s.length</code></li>
 * <li><code>n == t.length</code></li>
 * <li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
 * <li><code>s</code> and <code>t</code> consist of uppercase and lowercase English letters.</li>
 * </ul>
 * <p><strong>Follow up:</strong> Could you find an algorithm that runs in <code>O(m + n)</code> time?</p>
 */

class Solution {
    public String minWindow(String s, String t) {
        
            return "";
    }
}
