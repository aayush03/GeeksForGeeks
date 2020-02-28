package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeSerializeBinaryTree {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(39);
        root.left = new Node(23);
        root.left.left = new Node(32);
        root.left.right = new Node(5);
        root.left.left.right = new Node(34);
        root.left.left.right.left = new Node(7);

        levelOrderTraversal(root);
        System.out.println();

        ArrayList<Integer> A = new ArrayList<>();

        serialize(root, A);

        root = deSerialize(A);

        levelOrderTraversal(root);
    }

    public static void serialize(Node root, ArrayList<Integer> A) {
        if (root == null)
            return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr == null)
                A.add(null);
            else {
                A.add(curr.data);
                q.add(curr.left);
                q.add(curr.right);
            }
        }
    }

    public static Node deSerialize(ArrayList<Integer> A) {
        if (A == null || A.size() == 0 || A.get(0) == null)
            return null;

        Node root = new Node(A.get(0));

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        int i = 1;
        int n = A.size();
        while (!q.isEmpty()) {
            Node curr = q.poll();

            Node left = null, right = null;

            if (i < n && A.get(i) != null)
                left = new Node(A.get(i));
            i++;
            if (i < n && A.get(i) != null)
                right = new Node(A.get(i));
            i++;

            curr.left = left;
            curr.right = right;

            if (left != null)
                q.add(left);
            if (right != null)
                q.add(right);
        }

        return root;

    }

    private static void levelOrderTraversal(Node root) {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print(curr.data + " ");

            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }
}
