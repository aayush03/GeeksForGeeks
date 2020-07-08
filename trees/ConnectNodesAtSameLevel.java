package trees;

import java.util.LinkedList;
import java.util.Queue;

public class ConnectNodesAtSameLevel {

    private static class Node {
        int data;
        Node left, right, nextRight;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", left=" + (left == null ? null : left.data) +
                    ", right=" + (right == null ? null : right.data) +
                    ", nextRight=" + nextRight +
                    '}';
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(3);
        root.right = new Node(5);
        root.left.left = new Node(4);
        root.left.right = new Node(1);
        root.right.left = new Node(2);

        connect(root);
        connectBFS(root);
    }

    static void connectBFS(Node root)
    {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        // null marker to represent end of current level
        q.add(null);

        // Do Level order of tree using NULL markers
        while (!q.isEmpty()) {
            Node p = q.peek();
            q.remove();
            if (p != null) {

                // next element in queue represents next
                // node at current Level
                p.nextRight = q.peek();

                // push left and right children of current
                // node
                if (p.left != null)
                    q.add(p.left);
                if (p.right != null)
                    q.add(p.right);
            }

            // if queue is not empty, push NULL to mark
            // nodes at this level are visited
            else if (!q.isEmpty())
                q.add(null);
        }
    }

    private static void connect(Node root) {

        if (root == null)
            return;

        // Set nextRight for root
        root.nextRight = null;

        // set nextRight of all levels one by one
        while (root != null) {
            Node curr = root;

            /* Connect all childrem nodes of root and children nodes of all other
               nodes at same level as root */
            while (curr != null) {
                // Set the nextRight pointer for root's left child
                if (curr.left != null) {

                    // If curr has right child, then right child is nextRight of
                    // root and we also need to set nextRight of right child
                    if (curr.right != null)
                        curr.left.nextRight = curr.right;
                    else
                        curr.left.nextRight = getNextRight(curr);
                }

                if (curr.right != null)
                    curr.right.nextRight = getNextRight(curr);

                // Set nextRight for other nodes in pre order fashion
                curr = curr.nextRight;
            }

            // start from the first node of next level
            if (root.left != null)
                root = root.left;
            else if (root.right != null)
                root = root.right;
            else
                root = getNextRight(root);
        }
    }

    static Node getNextRight(Node p) {
        Node temp = p.nextRight;

        /* Traverse nodes at p's level and find and return
           the first node's first child */
        while (temp != null) {
            if (temp.left != null)
                return temp.left;
            if (temp.right != null)
                return temp.right;
            temp = temp.nextRight;
        }

        // If all the nodes at p's level are leaf nodes then return NULL
        return null;
    }

}
