package trees;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-inorder-traversal/description/
public class TraverseDeepFirst {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1, null, null);
        TreeNode left1 = new TreeNode(2, null, null);
        TreeNode left2 = new TreeNode(3, null, null);
        TreeNode right1 = new TreeNode(4, null, null);
        TreeNode right2 = new TreeNode(5, null, null);
        TreeNode right3 = new TreeNode(6, null, null);
        tree.left = left1;
        tree.right = right1;
        left1.left = left2;
        left1.right = right3;
        right1.right = right2;

        System.out.println(new TraverseDeepFirst().inorderTraversal(tree));
    }

    // Traverses Binary Tree in order: leftmost child, it's parent, and right subtree
    // t: O(n) - recursion calls
    // s: O(n) - recursion stack
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> traversal = new ArrayList<>();
        traverseDeepLeftFirst(traversal, root);
        return traversal;
    }

    public void traverseDeepLeftFirst(List<Integer> traversal, TreeNode root) {
        if (root != null) {
            traverseDeepLeftFirst(traversal, root.left);
            traversal.add(root.val);
            traverseDeepLeftFirst(traversal, root.right);
        }
    }

    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            return String.valueOf(val);
        }
    };

}
