package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a binary tree, write a function to get the maximum width of the given tree.
 * The width of a tree is the maximum width among all levels.
 * The binary tree has the same structure as a full binary tree, but some nodes are null.
 * <p>
 * The width of one level is defined as the length between the end-nodes
 * (the leftmost and right most non-null nodes in the level, where the null nodes
 * between the end-nodes are also counted into the length calculation.
 * <p>
 * Sample Input:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 */
public class MaximumWidthOfBinaryTree_LC {

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

    public static void main(String[] args) {

    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNodeContext> q = new LinkedList<>();
        q.add(new TreeNodeContext(root, 0));
        int maxWidth = Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            int size = q.size();
            int minIndex = 0, maxIndex = 0;

            for (int i = 0; i < size; i++) {
                TreeNodeContext curr = q.poll();
                TreeNode node = curr.node;
                int index = curr.index;

                if (node.left != null)
                    q.add(new TreeNodeContext(node.left, index * 2));

                if (node.right != null)
                    q.add(new TreeNodeContext(node.right, (index * 2) + 1));

                if (i == 0)
                    minIndex = index;
                if (i == size - 1)
                    maxIndex = index;
            }

            maxWidth = Math.max(maxWidth, maxIndex - minIndex + 1);
        }

        return maxWidth;
    }

    class TreeNodeContext {
        public TreeNode node;
        public int index;

        public TreeNodeContext(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
