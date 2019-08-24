package trees;

import java.util.LinkedList;
import java.util.Queue;

public class ConstructTreeFromPostOrderAndInOrder {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        int in[] = new int[] { 4, 8, 2, 5, 1, 6, 3, 7 };
        int post[] = new int[] { 8, 4, 5, 2, 6, 7, 3, 1 };
        int n = in.length;

        ConstructTreeFromPostOrderAndInOrder tree = new ConstructTreeFromPostOrderAndInOrder();
        tree.levelOrderTraversalLineByLine(tree.buildTree(in,post,n));
    }

    private Node buildTree(int in[], int post[], int n) {
        Index pIndex = new Index();
        pIndex.index = n - 1;
        return buildUtil(in, post, 0, n - 1, pIndex);
    }

    private Node buildUtil(int in[], int post[], int inStart, int inEnd, Index pIndex) {
        // Base case
        if (inStart > inEnd)
            return null;

        /* Pick current node from Postrder traversal using
           postIndex and decrement postIndex */
        Node node = new Node(post[pIndex.index]);
        (pIndex.index)--;

        /* If this node has no children then return */
        if (inStart == inEnd)
            return node;

        /* Else find the index of this node in Inorder
           traversal */
        int inIndex = search(in, inStart, inEnd, node.data);

        /* Using index in Inorder traversal, construct left and
           right subtress */
        node.right = buildUtil(in, post, inIndex + 1, inEnd, pIndex);
        node.left = buildUtil(in, post, inStart, inIndex - 1, pIndex);

        return node;
    }

    private int search(int arr[], int start, int end, int value) {
        int i;
        for (i = start; i <= end; i++) {
            if (arr[i] == value)
                break;
        }
        return i;
    }

    private void levelOrderTraversalLineByLine(Node root) {
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

class Index {
    int index;
}
