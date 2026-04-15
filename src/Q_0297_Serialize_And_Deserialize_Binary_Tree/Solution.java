package Q_0297_Serialize_And_Deserialize_Binary_Tree;

/**
 * LeetCode: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * NeetCode: https://neetcode.io/problems/serialize-and-deserialize-binary-tree
 * LeetCode #297: Serialize and Deserialize Binary Tree
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return "";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return null;
    }
}

class TreeNode { int val; TreeNode left, right; TreeNode(int x) { val = x; } TreeNode() {} TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; } }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));