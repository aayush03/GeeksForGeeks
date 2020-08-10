package trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Aayush Srivastava
 */

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * <p>
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * <p>
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 */
public class DeleteNodesAndReturnForest {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<TreeNode> delNodesBFS(TreeNode root, int[] to_delete) {
        if (root == null)
            return new ArrayList<>();
        Set<TreeNode> resultSet = new HashSet<>();
        resultSet.add(root);

        if (to_delete.length == 0)
            return new ArrayList<>(resultSet);

        Set<Integer> deleteSet = new HashSet<>();
        for (int i : to_delete)
            deleteSet.add(i);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (deleteSet.contains(curr.val)) {
                resultSet.remove(curr);
                if (curr.left != null)
                    resultSet.add(curr.left);
                if (curr.right != null)
                    resultSet.add(curr.right);
            }
            if (curr.left != null) {
                queue.add(curr.left);
                if (deleteSet.contains(curr.left.val))
                    curr.left = null;
            }
            if (curr.right != null) {
                queue.add(curr.right);
                if (deleteSet.contains(curr.right.val))
                    curr.right = null;
            }
        }

        return new ArrayList<>(resultSet);
    }

    /**
     * Time: O(to_delete.length + N) where N is the number of nodes in the given tree.
     * Space: O(to_delete.length + H) where H is the height of the given tree.
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> forest = new ArrayList<>();
        if (root == null) {
            return forest;
        } else if (to_delete.length == 0) {
            forest.add(root);
            return forest;
        }

        Set<Integer> deleteSet = new HashSet<>();
        for (int i : to_delete)
            deleteSet.add(i);

        deleteNodes(root, deleteSet, forest);
        if (!deleteSet.contains(root.val))
            forest.add(root);

        return forest;
    }

    private TreeNode deleteNodes(TreeNode node, Set<Integer> deleteSet, List<TreeNode> forest) {
        if (node == null)
            return null;
        node.left = deleteNodes(node.left, deleteSet, forest);
        node.right = deleteNodes(node.right, deleteSet, forest);
        if (deleteSet.contains(node.val)) {
            if (node.left != null)
                forest.add(node.left);
            if (node.right != null)
                forest.add(node.right);
            return null;
        }
        return node;
    }
}
