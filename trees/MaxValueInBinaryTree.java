package trees;

public class MaxValueInBinaryTree {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    int maxVal = 0;

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(3);
        root.left.left = new Node(30);
        root.left.right = new Node(80);
        root.left.right.left = new Node(60);

        MaxValueInBinaryTree tree = new MaxValueInBinaryTree();
        System.out.println(tree.getMaxValue(root));
        System.out.println(tree.maxValue(root));
    }

    int getMaxValue(Node root) {
        inOrder(root);
        return maxVal;
    }

    void inOrder(Node root) {
        if (root.left != null)
            inOrder(root.left);
        if (root.data > maxVal)
            maxVal = root.data;
        if (root.right != null)
            inOrder(root.right);
    }

    int maxValue(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;

        return max(root.data, maxValue(root.left), maxValue(root.right));
    }

    int max(int x, int y, int z) {
        if (x >= y && x >= z)
            return x;
        else if (y >= x && y >= z)
            return y;
        else
            return z;
    }
}
