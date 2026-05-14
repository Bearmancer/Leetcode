package Q_0494_Target_Sum;

/**
 * <h1><a href="https://leetcode.com/problems/target-sum/">LeetCode #494: Target Sum</a></h1>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> 2-D Dynamic Programming</p>
 *
 * <h2>Problem</h2>
 * <p>You are given an integer array <code>nums</code> and an integer <code>target</code>.</p>
 * <p>You want to build an <strong>expression</strong> out of nums by adding one of the symbols <code>'+'</code> and <code>'-'</code> before each integer in nums and then concatenate all the integers.</p>
 * <ul>
 * <li>For example, if <code>nums = [2, 1]</code>, you can add a <code>'+'</code> before <code>2</code> and a <code>'-'</code> before <code>1</code> and concatenate them to build the expression <code>"+2-1"</code>.</li>
 * </ul>
 * <p>Return the number of different <strong>expressions</strong> that you can build, which evaluates to <code>target</code>.</p>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> nums = [1,1,1,1,1], target = 3
 * <strong>Output:</strong> 5
 * <strong>Explanation:</strong> There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> nums = [1], target = 1
 * <strong>Output:</strong> 1
 * </pre>
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 20</code></li>
 * <li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
 * <li><code>0 &lt;= sum(nums[i]) &lt;= 1000</code></li>
 * <li><code>-1000 &lt;= target &lt;= 1000</code></li>
 * </ul>
 */

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        
            return 0;
    }
}
