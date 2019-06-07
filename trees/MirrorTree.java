package trees;

public class MirrorTree {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.left.left = new Node(40);
        root.left.right = new Node(60);

        MirrorTree tree = new MirrorTree();
        tree.printInorder(root);
        System.out.println();
        tree.createMirrorTree(root);
        tree.printInorder(root);
    }

    private void printInorder(Node root) {
        if (root == null)
            return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    private void createMirrorTree(Node root) {
        if (root == null)
            return;
        else {
            Node right = root.right;
            root.right = root.left;
            root.left = right;
        }
        createMirrorTree(root.right);
        createMirrorTree(root.left);
    }

}
