package Q_0100_Same_Tree;

/**
 * <h2><a href="https://leetcode.com/problems/same-tree/">LeetCode #100: Same Tree</a></h2>
 * <p><b>Difficulty:</b> Easy &nbsp;|&nbsp; <b>Category:</b> Trees</p>
 * <p><b>NeetCode:</b> <a href="https://neetcode.io/problems/same-tree">neetcode.io/problems/same-tree</a></p>
 *
 * <h3>Problem</h3>
 * <p>Given the roots of two binary trees <code>p</code> and <code>q</code>, write a function to check if they are the same or not.</p>
 * <p>Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.</p>
 * <p> </p>
 * <p><strong class="example">Example 1:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex1.jpg" style="width: 622px; height: 182px;"/>
 * <pre>
 * <strong>Input:</strong> p = [1,2,3], q = [1,2,3]
 * <strong>Output:</strong> true
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex2.jpg" style="width: 382px; height: 182px;"/>
 * <pre>
 * <strong>Input:</strong> p = [1,2], q = [1,null,2]
 * <strong>Output:</strong> false
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex3.jpg" style="width: 622px; height: 182px;"/>
 * <pre>
 * <strong>Input:</strong> p = [1,2,1], q = [1,1,2]
 * <strong>Output:</strong> false
 * </pre>
 * <p> </p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li>The number of nodes in both trees is in the range <code>[0, 100]</code>.</li>
 * <li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
 * </ul>
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return false;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
