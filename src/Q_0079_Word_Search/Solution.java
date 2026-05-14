package Q_0079_Word_Search;

/**
 * <h1><a href="https://leetcode.com/problems/word-search/">LeetCode #79: Word Search</a></h1>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> Backtracking</p>
 *
 * <h2>Problem</h2>
 * <p>Given an <code>m x n</code> grid of characters <code>board</code> and a string <code>word</code>, return <code>true</code> <em>if</em> <code>word</code> <em>exists in the grid</em>.</p>
 * <p>The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.</p>
 * <h3>Example 1:</h3>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word2.jpg" style="width: 322px; height: 242px;"/>
 * <pre>
 * <strong>Input:</strong> board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * <strong>Output:</strong> true
 * </pre>
 * <h3>Example 2:</h3>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word-1.jpg" style="width: 322px; height: 242px;"/>
 * <pre>
 * <strong>Input:</strong> board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * <strong>Output:</strong> true
 * </pre>
 * <h3>Example 3:</h3>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/15/word3.jpg" style="width: 322px; height: 242px;"/>
 * <pre>
 * <strong>Input:</strong> board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * <strong>Output:</strong> false
 * </pre>
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>m == board.length</code></li>
 * <li><code>n = board[i].length</code></li>
 * <li><code>1 &lt;= m, n &lt;= 6</code></li>
 * <li><code>1 &lt;= word.length &lt;= 15</code></li>
 * <li><code>board</code> and <code>word</code> consists of only lowercase and uppercase English letters.</li>
 * </ul>
 * <p><strong>Follow up:</strong> Could you use search pruning to make your solution faster with a larger <code>board</code>?</p>
 */

class Solution {
    public boolean exist(char[][] board, String word) {
        
            return false;
    }
}
