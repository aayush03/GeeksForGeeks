package trees;

/**
 * Given a binary tree, the task is to find the maximum path sum. The path may start and end at any node in the tree.
 */
public class MaximumPathSumFromAnyNode {

    static class Node {
        int data;
        Node left, right;

        Node(int key) {
            data = key;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(10);
        node1.left = new Node(2);
        node1.right = new Node(-25);
        node1.left.left = new Node(20);
        node1.left.right = new Node(1);
        node1.right.left = new Node(3);
        node1.right.right = new Node(4);

        System.out.println(findMaxSum(node1));
        System.out.println(findMaxSumOptimized(node1));

        Node root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(5);
        root.right.right = new Node(-2);

        System.out.println(findMaxSum(root));
        System.out.println(findMaxSumOptimized(root));
    }

    static int maxValue;
    static int findMaxSumOptimized(Node node)
    {
        //your code goes here
        maxValue = Integer.MIN_VALUE;
        maxPathDown(node);
        return maxValue;
    }

    private static int maxPathDown(Node node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.data);
        return Math.max(left, right) + node.data;
    }

    private static int findMaxSum(Node node) {
        if (node == null)
            return 0;
        return findMaxSum(node, false);
    }

    private static int findMaxSum(Node root, boolean isAnyParentRootTAken) {
        if (root == null && !isAnyParentRootTAken)
            return Integer.MIN_VALUE;
        if (root == null)
            return 0;
        if (!isAnyParentRootTAken) {
            int sumWithLeftSubTreeAndNode = root.data + findMaxSum(root.left, true);
            int sumWithRightSubTreeAndNode = root.data + findMaxSum(root.right, true);
            int sumWithBothSubTreeAndNode = root.data + findMaxSum(root.left, true) + findMaxSum(root.right, true);
            int maxSumForChildSubTrees = Math.max(findMaxSum(root.left, false), findMaxSum(root.right, false));

            return Math.max(sumWithLeftSubTreeAndNode, Math.max(sumWithRightSubTreeAndNode, Math.max(sumWithBothSubTreeAndNode, maxSumForChildSubTrees)));
        }

        int sumWithLeftSubTreeAndNode = root.data + findMaxSum(root.left, true);
        int sumWithRightSubTreeAndNode = root.data + findMaxSum(root.right, true);
        int zero = 0;

        return Math.max(sumWithLeftSubTreeAndNode, Math.max(sumWithRightSubTreeAndNode, zero));
    }
}
