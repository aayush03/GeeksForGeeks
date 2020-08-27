package amazon.online_assessment;

/**
 * @author Aayush Srivastava
 */
public class DistanceBetweenNodesInBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static int findDistance(int[] is, int i, int j) {
        TreeNode root = null;
        for (int k = 0; k < is.length; k++) {
            root = buildBST(root, is[k]);
        }
        TreeNode lca = findLeastCommonAncestor(root, i, j);
        int distance = findDistanceFromLCA(lca, i) + findDistanceFromLCA(lca, j);
        return distance;
    }

    private static int findDistanceFromLCA(TreeNode lca, int i) {
        int distanceSum = 0;
        while (true) {
            if (lca != null) {
                if (lca.val == i)
                    return distanceSum;
                else if (lca.val < i) {
                    distanceSum++;
                    lca = lca.right;
                } else if (lca.val > i) {
                    distanceSum++;
                    lca = lca.left;
                }
            } else
                return distanceSum;
        }
    }

    private static TreeNode findLeastCommonAncestor(TreeNode root, int i, int j) {
        while (true) {
            if (root.val > i && root.val > j) {
                root = root.left;
            } else if (root.val < i && root.val < j) {
                root = root.right;
            } else {
                return root;
            }
        }
    }

    private static TreeNode buildBST(TreeNode root, int node) {
        if (root == null) {
            root = new TreeNode(node);
            return root;
        } else if (root.val < node) {
            if (root.right == null)
                root.right = new TreeNode(node);
            else
                buildBST(root.right, node);
        } else if (root.val > node) {
            if (root.left == null)
                root.left = new TreeNode(node);
            else
                buildBST(root.left, node);
        }
        return root;
    }
}
