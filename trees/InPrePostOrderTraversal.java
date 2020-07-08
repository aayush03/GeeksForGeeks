package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Aayush Srivastava
 */
public class InPrePostOrderTraversal {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);
        System.out.print("InOrder Traversal : ");
        inOrder(root);
        System.out.println();
        System.out.print("PreOrder Traversal : ");
        preOrder(root);
        System.out.println();
        System.out.print("PostOrder Traversal : ");
        postOrder(root);
        System.out.println();
        levelOrderTraversalLineByLine(root);
    }

    static void levelOrderTraversalLineByLine(Node root) {
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

    }

    static void inOrder(Node root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    static void preOrder(Node root) {
        if (root == null)
            return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static void postOrder(Node root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }
}
