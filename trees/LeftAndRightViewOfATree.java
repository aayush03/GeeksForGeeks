package trees;

import java.util.LinkedList;
import java.util.Queue;

public class LeftAndRightViewOfATree {

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
        LeftAndRightViewOfATree tree = new LeftAndRightViewOfATree();
        tree.printLeftViewOfTree(root);
        System.out.println();
        tree.printRightViewOfTree(root);
    }

    private void printLeftViewOfTree(Node root) {
        Queue<Node> queue = new LinkedList<>();

        ((LinkedList<Node>) queue).push(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                Node curr = queue.poll();
                if (i == 1)
                    System.out.print(curr.data + " ");

                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null)
                    queue.add(curr.right);
            }
        }
    }

    private void printRightViewOfTree(Node root) {
        Queue<Node> queue = new LinkedList<>();

        ((LinkedList<Node>) queue).push(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                Node curr = queue.poll();
                if (i == size)
                    System.out.print(curr.data + " ");

                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null)
                    queue.add(curr.right);
            }
        }
    }

}
