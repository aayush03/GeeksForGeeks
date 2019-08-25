package trees.bst;

public class MinimumElementInBST {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    private static int minValue(Node root)
    {
        if (root == null)
            return -1;
        if (root.left != null)
            return minValue(root.left);

        return root.data;
    }
}
