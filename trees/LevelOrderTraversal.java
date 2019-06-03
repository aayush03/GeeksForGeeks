package trees;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal {

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
        root.left.left = new Node(15);
        root.left.right = new Node(5);
        root.right.left = new Node(25);
        root.right.right = new Node(60);
        LevelOrderTraversal tree = new LevelOrderTraversal();
        tree.levelOrderTraversal(root);
        System.out.println();
        tree.levelOrderTraversalLineByLine(root);
        int level = 3;
        System.out.println("Width at level "+level+" = "+tree.widthAtLevel(root,3));
    }

    private void levelOrderTraversal(Node root) {
        Queue<Node> queue = new LinkedList<>();

        ((LinkedList<Node>) queue).push(root);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print(curr.data+ " ");

            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }

    private void levelOrderTraversalLineByLine(Node root) {
        Queue<Node> queue = new LinkedList<>();

        ((LinkedList<Node>) queue).push(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=1;i<=size; i++) {
                Node curr = queue.poll();
                System.out.print(curr.data + " ");

                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null)
                    queue.add(curr.right);
            }
            System.out.println();
            maxWidth = size;
        }

        System.out.println("Maximum Width of tree = "+maxWidth);
    }

    private int widthAtLevel(Node root, int level) {
        if (root == null)
            return 0;
        if (level ==1)
            return 1;
        return widthAtLevel(root.left, level-1)+widthAtLevel(root.right,level-1);
    }
}
