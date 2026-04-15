package Q_0235_Lowest_Common_Ancestor_Of_A_Binary_Search_Tree;

/**
 * <h2><a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/">LeetCode #235: Lowest Common Ancestor of a Binary Search Tree</a></h2>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> Trees</p>
 * <p><b>NeetCode:</b> <a href="https://neetcode.io/problems/lowest-common-ancestor-of-a-binary-search-tree">neetcode.io/problems/lowest-common-ancestor-of-a-binary-search-tree</a></p>
 *
 * <h3>Problem</h3>
 * <p>Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.</p>
 * <p>According to the <a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">definition of LCA on Wikipedia</a>: “The lowest common ancestor is defined between two nodes <code>p</code> and <code>q</code> as the lowest node in <code>T</code> that has both <code>p</code> and <code>q</code> as descendants (where we allow <strong>a node to be a descendant of itself</strong>).”</p>
 * <p> </p>
 * <p><strong class="example">Example 1:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarysearchtree_improved.png" style="width: 200px; height: 190px;"/>
 * <pre>
 * <strong>Input:</strong> root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * <strong>Output:</strong> 6
 * <strong>Explanation:</strong> The LCA of nodes 2 and 8 is 6.
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarysearchtree_improved.png" style="width: 200px; height: 190px;"/>
 * <pre>
 * <strong>Input:</strong> root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * <strong>Output:</strong> 2
 * <strong>Explanation:</strong> The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre>
 * <strong>Input:</strong> root = [2,1], p = 2, q = 1
 * <strong>Output:</strong> 2
 * </pre>
 * <p> </p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li>The number of nodes in the tree is in the range <code>[2, 10<sup>5</sup>]</code>.</li>
 * <li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
 * <li>All <code>Node.val</code> are <strong>unique</strong>.</li>
 * <li><code>p != q</code></li>
 * <li><code>p</code> and <code>q</code> will exist in the BST.</li>
 * </ul>
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return null;
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
