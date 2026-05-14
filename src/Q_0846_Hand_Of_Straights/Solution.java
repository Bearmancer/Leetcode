package Q_0846_Hand_Of_Straights;

/**
 * <h1><a href="https://leetcode.com/problems/hand-of-straights/">LeetCode #846: Hand of Straights</a></h1>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> Greedy</p>
 *
 * <h2>Problem</h2>
 * <p>Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size <code>groupSize</code>, and consists of <code>groupSize</code> consecutive cards.</p>
 * <p>Given an integer array <code>hand</code> where <code>hand[i]</code> is the value written on the <code>i<sup>th</sup></code> card and an integer <code>groupSize</code>, return <code>true</code> if she can rearrange the cards, or <code>false</code> otherwise.</p>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * <strong>Output:</strong> true
 * <strong>Explanation:</strong> Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> hand = [1,2,3,4,5], groupSize = 4
 * <strong>Output:</strong> false
 * <strong>Explanation:</strong> Alice's hand can not be rearranged into groups of 4.
 *
 * </pre>
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>1 &lt;= hand.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>0 &lt;= hand[i] &lt;= 10<sup>9</sup></code></li>
 * <li><code>1 &lt;= groupSize &lt;= hand.length</code></li>
 * </ul>
 * <p><strong>Note:</strong> This question is the same as 1296: <a href="https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/" target="_blank">https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/</a></p>
 */

class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        
            return false;
    }
}
