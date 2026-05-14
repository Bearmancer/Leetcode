package Q_1046_Last_Stone_Weight;

/**
 * <h1><a href="https://leetcode.com/problems/last-stone-weight/">LeetCode #1046: Last Stone Weight</a></h1>
 * <p><b>Difficulty:</b> Easy &nbsp;|&nbsp; <b>Category:</b> Heap / Priority Queue</p>
 *
 * <h2>Problem</h2>
 * <p>You are given an array of integers <code>stones</code> where <code>stones[i]</code> is the weight of the <code>i<sup>th</sup></code> stone.</p>
 * <p>We are playing a game with the stones. On each turn, we choose the <strong>heaviest two stones</strong> and smash them together. Suppose the heaviest two stones have weights <code>x</code> and <code>y</code> with <code>x &lt;= y</code>. The result of this smash is:</p>
 * <ul>
 * <li>If <code>x == y</code>, both stones are destroyed, and</li>
 * <li>If <code>x != y</code>, the stone of weight <code>x</code> is destroyed, and the stone of weight <code>y</code> has new weight <code>y - x</code>.</li>
 * </ul>
 * <p>At the end of the game, there is <strong>at most one</strong> stone left.</p>
 * <p>Return <em>the weight of the last remaining stone</em>. If there are no stones left, return <code>0</code>.</p>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> stones = [2,7,4,1,8,1]
 * <strong>Output:</strong> 1
 * <strong>Explanation:</strong>
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> stones = [1]
 * <strong>Output:</strong> 1
 * </pre>
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>1 &lt;= stones.length &lt;= 30</code></li>
 * <li><code>1 &lt;= stones[i] &lt;= 1000</code></li>
 * </ul>
 */

class Solution {
    public int lastStoneWeight(int[] stones) {
        
            return 0;
    }
}
