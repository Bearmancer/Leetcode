package Q_0239_Sliding_Window_Maximum;

/**
 * <h1><a href="https://leetcode.com/problems/sliding-window-maximum/">LeetCode #239: Sliding Window Maximum</a></h1>
 * <p><b>Difficulty:</b> Hard &nbsp;|&nbsp; <b>Category:</b> Sliding Window</p>
 *
 * <h2>Problem</h2>
 * <p>You are given an array of integers <code>nums</code>, there is a sliding window of size <code>k</code> which is moving from the very left of the array to the very right. You can only see the <code>k</code> numbers in the window. Each time the sliding window moves right by one position.</p>
 * <p>Return <em>the max sliding window</em>.</p>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> nums = [1,3,-1,-3,5,3,6,7], k = 3
 * <strong>Output:</strong> [3,3,5,5,6,7]
 * <strong>Explanation:</strong>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       <strong>3</strong>
 *  1 [3  -1  -3] 5  3  6  7       <strong>3</strong>
 *  1  3 [-1  -3  5] 3  6  7      <strong> 5</strong>
 *  1  3  -1 [-3  5  3] 6  7       <strong>5</strong>
 *  1  3  -1  -3 [5  3  6] 7       <strong>6</strong>
 *  1  3  -1  -3  5 [3  6  7]      <strong>7</strong>
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> nums = [1], k = 1
 * <strong>Output:</strong> [1]
 * </pre>
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= k &lt;= nums.length</code></li>
 * </ul>
 */

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
            return new int[0];
    }
}
