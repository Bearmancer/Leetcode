package Q_0063_Unique_Paths_II;

/**
 * LeetCode: https://leetcode.com/problems/unique-paths-ii/
 * NeetCode: https://neetcode.io/problems/unique-paths-ii
 * LeetCode #63: Unique Paths II
 *
 * You are given an {@code m x n} integer array {@code grid}. There is a robot initially located at the top-left corner (i.e., {@code grid[0][0]}). The robot tries to move to the bottom-right corner (i.e., {@code grid[m - 1][n - 1]}). The robot can only move either down or right at any point in time.
 *
 * An obstacle and space are marked as {@code 1} or {@code 0} respectively in {@code grid}. A path that the robot takes cannot include any square that is an obstacle.
 *
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The testcases are generated so that the answer will be less than or equal to {@code 2 * 10^9}.
 *
 *
 * Example 1:
 *
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * Example 2:
 *
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * - {@code m == obstacleGrid.length}
 * - {@code n == obstacleGrid[i].length}
 * - {@code 1 <= m, n <= 100}
 * - {@code obstacleGrid[i][j]} is {@code 0} or {@code 1}.
 */

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
            return 0;
    }
}