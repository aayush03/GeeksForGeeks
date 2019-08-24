package trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeFromLinkedList {

    private static class Tree {
        int data;
        Tree left, right;

        public Tree(int data) {
            this.data = data;
        }
    }

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(12);
        head.next.next = new Node(15);
        head.next.next.next = new Node(25);
        head.next.next.next.next = new Node(30);
        head.next.next.next.next.next = new Node(36);

        BinaryTreeFromLinkedList tree = new BinaryTreeFromLinkedList();
        tree.levelOrderTraversal(tree.convertLinkedListToBinaryTree(head, null));

    }

    Tree convertLinkedListToBinaryTree(Node head, Tree node) {
        Queue<Tree> queue = new LinkedList<>();

        if (head == null)
            return null;

        node = new Tree(head.data);
        queue.add(node);

        head = head.next;

        while (head != null) {
            Tree parent = queue.poll();

            Tree leftChild, rightChild = null;
            leftChild = new Tree(head.data);
            queue.add(leftChild);
            head = head.next;
            if (head != null) {
                rightChild = new Tree(head.data);
                queue.add(rightChild);
                head = head.next;
            }

            parent.left = leftChild;
            parent.right = rightChild;
        }

        return node;
    }


    private void levelOrderTraversal(Tree root) {
        Queue<Tree> queue = new LinkedList<>();

        ((LinkedList<Tree>) queue).push(root);

        while (!queue.isEmpty()) {
            Tree curr = queue.poll();
            System.out.print(curr.data + " ");

            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }

}
