package trees;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 *
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure
 * and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all
 * of this node's descendants. The tree s could also be considered as a subtree of itself.
 *
 * Sample Input :
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 */
public class CheckIfSubTree {

    private static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode sTree = new TreeNode(10);
        sTree.left = new TreeNode(4);
        sTree.right = new TreeNode(6);
        sTree.right.left = new TreeNode(30);

        TreeNode tTree = new TreeNode(26);
        tTree.left = new TreeNode(10);
        tTree.left.left = new TreeNode(4);
        tTree.left.right = new TreeNode(6);
        tTree.left.right.left = new TreeNode(30);
        tTree.right = new TreeNode(3);
        tTree.right.right = new TreeNode(3);

        CheckIfSubTree tree = new CheckIfSubTree();
        System.out.println(tree.isSubtree(tTree, sTree));
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null)
            return true;
        else if (s == null || t == null)
            return false;
        return isStructureSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isStructureSame(TreeNode s, TreeNode t) {
        if (s == null && t == null)
            return true;
        if (s != null && t != null &&
                (s.val == t.val) &&
                isStructureSame(s.left, t.left) &&
                isStructureSame(s.right, t.right))
            return true;
        return false;
    }
}
