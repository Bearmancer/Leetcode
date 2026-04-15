package Q_0098_Validate_Binary_Search_Tree;

/**
 * <h2><a href="https://leetcode.com/problems/validate-binary-search-tree/">LeetCode #98: Validate Binary Search Tree</a></h2>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> Trees</p>
 * <p><b>NeetCode:</b> <a href="https://neetcode.io/problems/validate-binary-search-tree">neetcode.io/problems/validate-binary-search-tree</a></p>
 *
 * <h3>Problem</h3>
 * <p>Given the <code>root</code> of a binary tree, <em>determine if it is a valid binary search tree (BST)</em>.</p>
 * <p>A <strong>valid BST</strong> is defined as follows:</p>
 * <ul>
 * <li>The left <span data-keyword="subtree">subtree</span> of a node contains only nodes with keys <strong>strictly less than</strong> the node's key.</li>
 * <li>The right subtree of a node contains only nodes with keys <strong>strictly greater than</strong> the node's key.</li>
 * <li>Both the left and right subtrees must also be binary search trees.</li>
 * </ul>
 * <p> </p>
 * <p><strong class="example">Example 1:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree1.jpg" style="width: 302px; height: 182px;"/>
 * <pre>
 * <strong>Input:</strong> root = [2,1,3]
 * <strong>Output:</strong> true
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree2.jpg" style="width: 422px; height: 292px;"/>
 * <pre>
 * <strong>Input:</strong> root = [5,1,4,null,null,3,6]
 * <strong>Output:</strong> false
 * <strong>Explanation:</strong> The root node's value is 5 but its right child's value is 4.
 * </pre>
 * <p> </p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
 * <li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
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
    public boolean isValidBST(TreeNode root) {
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
