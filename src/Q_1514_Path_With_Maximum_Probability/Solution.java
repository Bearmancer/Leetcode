package Q_1514_Path_With_Maximum_Probability;

/**
 * LeetCode: https://leetcode.com/problems/path-with-maximum-probability/
 * NeetCode: https://neetcode.io/problems/path-with-maximum-probability
 * LeetCode #1514: Path with Maximum Probability
 *
 * You are given an undirected weighted graph of {@code n} nodes (0-indexed), represented by an edge list where {@code edges[i] = [a, b]} is an undirected edge connecting the nodes {@code a} and {@code b} with a probability of success of traversing that edge {@code succProb[i]}.
 *
 * Given two nodes {@code start} and {@code end}, find the path with the maximum probability of success to go from {@code start} to {@code end} and return its success probability.
 *
 * If there is no path from {@code start} to {@code end}, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 *
 * Example 2:
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 *
 * Example 3:
 *
 *
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 *
 *
 * Constraints:
 *
 * - {@code 2 <= n <= 10^4}
 * - {@code 0 <= start, end < n}
 * - {@code start != end}
 * - {@code 0 <= a, b < n}
 * - {@code a != b}
 * - {@code 0 <= succProb.length == edges.length <= 2*10^4}
 * - {@code 0 <= succProb[i] <= 1}
 * - There is at most one edge between every two nodes.
 */

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        
            return 0.0;
    }
}