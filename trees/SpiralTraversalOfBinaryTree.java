package trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SpiralTraversalOfBinaryTree {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(7);
        root.left.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(4);

        SpiralTraversalOfBinaryTree tree = new SpiralTraversalOfBinaryTree();
        tree.printSpiralTraversal(root);
    }

    private void printSpiralTraversal(Node node) {
        Queue<Node> queue = new LinkedList<>();

        ((LinkedList<Node>) queue).push(node);
        int count = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            Stack<Node> nodeStack = new Stack<>();
            for (int i = 1; i <= size; i++) {
                Node curr = queue.poll();
                if (count % 2 == 0)
                    System.out.print(curr.data + " ");
                else
                    nodeStack.push(curr);

                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null)
                    queue.add(curr.right);
            }


            if (count % 2 != 0) {
                while (!nodeStack.isEmpty())
                    System.out.print(nodeStack.pop().data + " ");
            }

            count++;
        }

    }
}
