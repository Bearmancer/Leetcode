package Q_0199_Binary_Tree_Right_Side_View;

import java.util.*;

/**
 * LeetCode: https://leetcode.com/problems/binary-tree-right-side-view/
 * NeetCode: https://neetcode.io/problems/binary-tree-right-side-view
 * LeetCode #199: Binary Tree Right Side View
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
    public List<Integer> rightSideView(TreeNode root) {
        return new ArrayList<>();
    }
}

class TreeNode { int val; TreeNode left, right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; } }