package trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ZigZagTraversalOfBinaryTree {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.right.right = new Node(4);
        node.right.left = new Node(5);
        node.left.right = new Node(6);
        node.left.left = new Node(7);

        zigZagTraversalOfTree(node);
    }

    private static void zigZagTraversalOfTree(Node root) {
        if (root == null)
            return;

        Stack<Node> currLevel = new Stack<>();
        Stack<Node> nextLevel = new Stack<>();

        currLevel.push(root);
        boolean leftToRight = true;
        while (!currLevel.isEmpty()) {

            Node node = currLevel.pop();
            System.out.print(node.data+" ");

            if (leftToRight) {
                if (node.right != null)
                    nextLevel.push(node.right);

                if (node.left != null)
                    nextLevel.push(node.left);
            } else {
                if (node.left != null)
                    nextLevel.push(node.left);

                if (node.right != null)
                    nextLevel.push(node.right);
            }

            if (currLevel.isEmpty()) {
                leftToRight = !leftToRight;
                Stack<Node> temp = currLevel;
                currLevel = nextLevel;
                nextLevel = temp;
            }
        }
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
}
