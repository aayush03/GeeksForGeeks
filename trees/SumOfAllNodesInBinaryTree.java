package trees;

public class SumOfAllNodesInBinaryTree {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(3);
        root.left.left = new Node(30);
        root.left.right = new Node(80);
        root.left.right.left = new Node(60);
        SumOfAllNodesInBinaryTree tree = new SumOfAllNodesInBinaryTree();
        System.out.println(tree.sum(root));
    }

    private int sum(Node root) {
        if (root == null)
            return 0;
        return root.data + sum(root.left) + sum(root.right);
    }
}
