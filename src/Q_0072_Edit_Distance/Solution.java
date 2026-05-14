package Q_0072_Edit_Distance;

/**
 * <h1><a href="https://leetcode.com/problems/edit-distance/">LeetCode #72: Edit Distance</a></h1>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> 2-D Dynamic Programming</p>
 *
 * <h2>Problem</h2>
 * <p>Given two strings <code>word1</code> and <code>word2</code>, return <em>the minimum number of operations required to convert <code>word1</code> to <code>word2</code></em>.</p>
 * <p>You have the following three operations permitted on a word:</p>
 * <ul>
 * <li>Insert a character</li>
 * <li>Delete a character</li>
 * <li>Replace a character</li>
 * </ul>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> word1 = "horse", word2 = "ros"
 * <strong>Output:</strong> 3
 * <strong>Explanation:</strong>
 * horse -&gt; rorse (replace 'h' with 'r')
 * rorse -&gt; rose (remove 'r')
 * rose -&gt; ros (remove 'e')
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> word1 = "intention", word2 = "execution"
 * <strong>Output:</strong> 5
 * <strong>Explanation:</strong>
 * intention -&gt; inention (remove 't')
 * inention -&gt; enention (replace 'i' with 'e')
 * enention -&gt; exention (replace 'n' with 'x')
 * exention -&gt; exection (replace 'n' with 'c')
 * exection -&gt; execution (insert 'u')
 * </pre>
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>0 &lt;= word1.length, word2.length &lt;= 500</code></li>
 * <li><code>word1</code> and <code>word2</code> consist of lowercase English letters.</li>
 * </ul>
 */

class Solution {
    public int minDistance(String word1, String word2) {
        
            return 0;
    }
}
