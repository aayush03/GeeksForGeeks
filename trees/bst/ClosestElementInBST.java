package trees.bst;

/**
 * Given a BST with N nodes and a target node K.
 * The task is to find an integer having minimum
 * absolute difference with given target value K
 * and return this difference.
 */
public class ClosestElementInBST {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static int maxDiff(Node root, int k)
    {
        //add code here.
        int floor = floor(root,k);
        int ceil = ceil(root,k);

        int floorDiff = Math.abs(k-floor);
        int ceilDiff = Math.abs(k-ceil);

        return floorDiff < ceilDiff ? floorDiff : ceilDiff;
    }

    private static int floor(Node root, int key) {
        if (root == null)
            return Integer.MAX_VALUE;

        if (root.data == key)
            return root.data;

        if (root.data > key)
            return floor(root.left, key);

        int floorValue = floor(root.right, key);
        return floorValue <= key ? floorValue : root.data;
    }

    private static int ceil(Node root, int key) {
        if (root == null)
            return Integer.MIN_VALUE;

        if (root.data == key)
            return root.data;

        if (root.data < key)
            return ceil(root.right, key);

        int ceilValue = ceil(root.left, key);
        return ceilValue >= key ? ceilValue : root.data;
    }
}
