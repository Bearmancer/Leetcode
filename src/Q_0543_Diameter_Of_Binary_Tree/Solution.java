package Q_0543_Diameter_Of_Binary_Tree;

/**
 * <h2><a href="https://leetcode.com/problems/diameter-of-binary-tree/">LeetCode #543: Diameter of Binary Tree</a></h2>
 * <p><b>Difficulty:</b> Easy &nbsp;|&nbsp; <b>Category:</b> Trees</p>
 * <p><b>NeetCode:</b> <a href="https://neetcode.io/problems/diameter-of-binary-tree">neetcode.io/problems/diameter-of-binary-tree</a></p>
 *
 * <h3>Problem</h3>
 * <p>Given the <code>root</code> of a binary tree, return <em>the length of the <strong>diameter</strong> of the tree</em>.</p>
 * <p>The <strong>diameter</strong> of a binary tree is the <strong>length</strong> of the longest path between any two nodes in a tree. This path may or may not pass through the <code>root</code>.</p>
 * <p>The <strong>length</strong> of a path between two nodes is represented by the number of edges between them.</p>
 * <p> </p>
 * <p><strong class="example">Example 1:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/03/06/diamtree.jpg" style="width: 292px; height: 302px;"/>
 * <pre>
 * <strong>Input:</strong> root = [1,2,3,4,5]
 * <strong>Output:</strong> 3
 * <strong>Explanation:</strong> 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre>
 * <strong>Input:</strong> root = [1,2]
 * <strong>Output:</strong> 1
 * </pre>
 * <p> </p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
 * <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
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
    public int diameterOfBinaryTree(TreeNode root) {
        return 0;
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
