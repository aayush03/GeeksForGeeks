package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Threaded Binary Trees are used to make the inorder traversal
 * faster and do it without stack and without recursion.
 * A binary tree is made threaded by making all right child pointers
 * that would normally be NULL point to the inorder successor of the node (if it exists).
 */
public class SingleThreadedBinaryTree {

    private static class Node {
        int data;
        Node left, right;
        boolean rightThread;

        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(6);
        root.left = new Node(3);
        root.right = new Node(8);
        root.left.left = new Node(1);
        root.left.right = new Node(5);
        root.right.left = new Node(7);
        root.right.right = new Node(11);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(13);

        SingleThreadedBinaryTree tree = new SingleThreadedBinaryTree();
        tree.createThreadedTree(root);
        tree.inOrderTraversal(root);
    }

    private void createThreadedTree(Node node) {
        Queue<Node> queue = new LinkedList<>();

        populateQueueWithInOrderTraversedElements(node, queue);

        createThreadedTree(node, queue);
    }

    private void populateQueueWithInOrderTraversedElements(Node node, Queue<Node> queue) {
        if (node == null)
            return;

        if (node.left != null)
            populateQueueWithInOrderTraversedElements(node.left, queue);

        queue.add(node);

        if (node.right != null)
            populateQueueWithInOrderTraversedElements(node.right, queue);
    }

    private void createThreadedTree(Node node, Queue<Node> queue) {
        if (node == null)
            return;

        if (node.left != null)
            createThreadedTree(node.left, queue);

        queue.remove();

        if (node.right != null)
            createThreadedTree(node.right, queue);
        else { // If right pointer is NULL, link it to the inorder successor and set 'rightThread' bit.
            node.right = queue.peek();
            node.rightThread = true;
        }
    }

    private Node leftMostNode(Node node) {
        if (node == null)
            return null;
        while (node.left != null)
            node = node.left;
        return node;
    }

    private void inOrderTraversal(Node root) {
        Node current = leftMostNode(root);

        while (current != null) {
            System.out.print(current.data + " ");

            // If this node is a thread node, then go to inorder successor
            if (current.rightThread)
                current = current.right;
            else // Else go to the leftmost child in right subtree
                current = leftMostNode(current.right);
        }
    }
}
