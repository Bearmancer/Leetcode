package Q_0130_Surrounded_Regions;

/**
 * <h2><a href="https://leetcode.com/problems/surrounded-regions/">LeetCode #130: Surrounded Regions</a></h2>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> Graphs</p>
 * <p><b>NeetCode:</b> <a href="https://neetcode.io/problems/surrounded-regions">neetcode.io/problems/surrounded-regions</a></p>
 *
 * <h3>Problem</h3>
 * <p>You are given an <code>m x n</code> matrix <code>board</code> containing <strong>letters</strong> <code>'X'</code> and <code>'O'</code>, <strong>capture regions</strong> that are <strong>surrounded</strong>:</p>
 * <ul>
 * <li><strong>Connect</strong>: A cell is connected to adjacent cells horizontally or vertically.</li>
 * <li><strong>Region</strong>: To form a region <strong>connect every</strong> <code>'O'</code> cell.</li>
 * <li><strong>Surround</strong>: A region is surrounded if none of the <code>'O'</code> cells in that region are on the edge of the board. Such regions are <strong>completely enclosed </strong>by <code>'X'</code> cells.</li>
 * </ul>
 * <p>To capture a <strong>surrounded region</strong>, replace all <code>'O'</code>s with <code>'X'</code>s <strong>in-place</strong> within the original board. You do not need to return anything.</p>
 * <p> </p>
 * <p><strong class="example">Example 1:</strong></p>
 *
 * <p><strong>Input:</strong> <span class="example-io">board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]</span></p>
 * <p><strong>Output:</strong> <span class="example-io">[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]</span></p>
 * <p><strong>Explanation:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/xogrid.jpg" style="width: 367px; height: 158px;"/>
 * <p>In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.</p>
 *
 * <p><strong class="example">Example 2:</strong></p>
 *
 * <p><strong>Input:</strong> <span class="example-io">board = [["X"]]</span></p>
 * <p><strong>Output:</strong> <span class="example-io">[["X"]]</span></p>
 *
 * <p> </p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>m == board.length</code></li>
 * <li><code>n == board[i].length</code></li>
 * <li><code>1 &lt;= m, n &lt;= 200</code></li>
 * <li><code>board[i][j]</code> is <code>'X'</code> or <code>'O'</code>.</li>
 * </ul>
 */

class Solution {
    public void solve(char[][] board) {
        
    }
}
