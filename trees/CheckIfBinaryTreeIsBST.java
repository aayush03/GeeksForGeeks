package trees;

public class CheckIfBinaryTreeIsBST {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(8);
        root.right = new Node(12);
        root.right.left = new Node(9);
        root.right.right = new Node(20);
        CheckIfBinaryTreeIsBST tree = new CheckIfBinaryTreeIsBST();
        System.out.println(tree.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    private boolean isBST(Node root, int min, int max) {
        if (root == null)
            return true;

        if (root.data > min && root.data < max)
            return (isBST(root.left, min, root.data) && isBST(root.right, root.data, max));
        return false;
    }
}
