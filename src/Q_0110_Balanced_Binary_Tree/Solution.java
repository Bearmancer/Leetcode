package Q_0110_Balanced_Binary_Tree;

/**
 * <h2><a href="https://leetcode.com/problems/balanced-binary-tree/">LeetCode #110: Balanced Binary Tree</a></h2>
 * <p><b>Difficulty:</b> Easy &nbsp;|&nbsp; <b>Category:</b> Trees</p>
 * <p><b>NeetCode:</b> <a href="https://neetcode.io/problems/balanced-binary-tree">neetcode.io/problems/balanced-binary-tree</a></p>
 *
 * <h3>Problem</h3>
 * <p>Given a binary tree, determine if it is <span data-keyword="height-balanced"><strong>height-balanced</strong></span>.</p>
 * <p> </p>
 * <p><strong class="example">Example 1:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_1.jpg" style="width: 342px; height: 221px;"/>
 * <pre>
 * <strong>Input:</strong> root = [3,9,20,null,null,15,7]
 * <strong>Output:</strong> true
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_2.jpg" style="width: 452px; height: 301px;"/>
 * <pre>
 * <strong>Input:</strong> root = [1,2,2,3,3,null,null,4,4]
 * <strong>Output:</strong> false
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre>
 * <strong>Input:</strong> root = []
 * <strong>Output:</strong> true
 * </pre>
 * <p> </p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li>The number of nodes in the tree is in the range <code>[0, 5000]</code>.</li>
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
    public boolean isBalanced(TreeNode root) {
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
