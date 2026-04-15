package Q_0102_Binary_Tree_Level_Order_Traversal;

import java.util.*;

/**
 * LeetCode: https://leetcode.com/problems/binary-tree-level-order-traversal/
 * NeetCode: https://neetcode.io/problems/binary-tree-level-order-traversal
 * LeetCode #102: Binary Tree Level Order Traversal
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        return new ArrayList<>();
    }
}

class TreeNode { int val; TreeNode left, right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; } }