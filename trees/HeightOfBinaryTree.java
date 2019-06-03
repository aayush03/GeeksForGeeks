package trees;

public class HeightOfBinaryTree {

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
        HeightOfBinaryTree tree = new HeightOfBinaryTree();
        System.out.println("Height of tree : " + tree.height(root));
    }

    int height(Node root) {
        if (root == null)
            return 0;
        return max(height(root.left), height(root.right)) + 1;
    }

    int max(int x, int y) {
        return x > y ? x : y;
    }
}
