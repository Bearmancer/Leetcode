package Q_0572_Subtree_Of_Another_Tree;

/**
 * <h1><a href="https://leetcode.com/problems/subtree-of-another-tree/">LeetCode #572: Subtree of Another Tree</a></h1>
 * <p><b>Difficulty:</b> Easy &nbsp;|&nbsp; <b>Category:</b> Trees</p>
 *
 * <h2>Problem</h2>
 * <p>Given the roots of two binary trees <code>root</code> and <code>subRoot</code>, return <code>true</code> if there is a subtree of <code>root</code> with the same structure and node values of<code> subRoot</code> and <code>false</code> otherwise.</p>
 * <p>A subtree of a binary tree <code>tree</code> is a tree that consists of a node in <code>tree</code> and all of this node's descendants. The tree <code>tree</code> could also be considered as a subtree of itself.</p>
 * <h3>Example 1:</h3>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/04/28/subtree1-tree.jpg" style="width: 532px; height: 400px;"/>
 * <pre>
 * <strong>Input:</strong> root = [3,4,5,1,2], subRoot = [4,1,2]
 * <strong>Output:</strong> true
 * </pre>
 * <h3>Example 2:</h3>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/04/28/subtree2-tree.jpg" style="width: 502px; height: 458px;"/>
 * <pre>
 * <strong>Input:</strong> root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * <strong>Output:</strong> false
 * </pre>
 * <h2>Constraints</h2>
 * <ul>
 * <li>The number of nodes in the <code>root</code> tree is in the range <code>[1, 2000]</code>.</li>
 * <li>The number of nodes in the <code>subRoot</code> tree is in the range <code>[1, 1000]</code>.</li>
 * <li><code>-10<sup>4</sup> &lt;= root.val &lt;= 10<sup>4</sup></code></li>
 * <li><code>-10<sup>4</sup> &lt;= subRoot.val &lt;= 10<sup>4</sup></code></li>
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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
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
