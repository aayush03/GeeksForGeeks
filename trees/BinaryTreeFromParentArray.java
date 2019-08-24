package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array of size N that represents a Tree in such a way that
 * array indexes are values in tree nodes and array values give the
 * parent node of that particular index (or node). The value of the
 * root node index would always be -1 as there is no parent for root.
 * Construct the standard linked representation of Binary Tree from
 * this array representation.
 */
public class BinaryTreeFromParentArray {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

        int[] arr = {-1, 0, 0, 1, 1, 3, 5};

        levelOrderTraversalLineByLine(createTree(arr, arr.length));
    }

    public static Node createTree(int arr[], int n) {
        if (n <= 0)
            return null;
        int indexOfArrayToBeUsedAsValue = 0;
        Queue<Node> queue = new LinkedList<>();

        Node root = new Node(indexOfArrayToBeUsedAsValue++);
        queue.add(root);

        while (indexOfArrayToBeUsedAsValue < n) {
            Node parent = queue.poll();
            if (parent != null && parent.data == arr[indexOfArrayToBeUsedAsValue]) {
                if (parent.left == null) {
                    parent.left = new Node(indexOfArrayToBeUsedAsValue++);
                    queue.add(parent);
                    queue.add(parent.left);
                    if (parent.right != null)
                        queue.add(parent.right);
                } else {
                    parent.right = new Node(indexOfArrayToBeUsedAsValue++);
                    queue.add(parent);
                    if (parent.left != null)
                        queue.add(parent.left);
                    queue.add(parent.right);
                }
            }
        }

        return root;
    }

    private static void levelOrderTraversalLineByLine(Node root) {
        Queue<Node> queue = new LinkedList<>();

        ((LinkedList<Node>) queue).push(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                Node curr = queue.poll();
                System.out.print(curr.data + " ");

                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null)
                    queue.add(curr.right);
            }
            System.out.println();
        }

    }


}
