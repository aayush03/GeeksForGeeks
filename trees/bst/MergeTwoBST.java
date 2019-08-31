package trees.bst;

public class MergeTwoBST {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

    }

    private static void merge(Node root1, Node root2) {
        //add code here.
        if (root1 == null && root2 == null)
            return;

        if (root1 == null) {
            inorderTraversal(root2);
            return;
        } else if (root2 == null) {
            inorderTraversal(root1);
            return;
        }

        Node temp1 = root1;

        Node prev1 = null;

        while (temp1.left != null) {
            prev1 = temp1;
            temp1 = temp1.left;
        }

        Node temp2 = root2;

        Node prev2 = null;

        while (temp2.left != null) {
            prev2 = temp2;
            temp2 = temp2.left;
        }

        if (temp1.data <= temp2.data) {
            System.out.print(temp1.data + " ");

            if (prev1 == null)
                merge(root1.right, root2);
            else {
                prev1.left = temp1.right;
                merge(root1, root2);
            }
        } else {
            System.out.print(temp2.data + " ");

            if (prev2 == null)
                merge(root1, root2.right);
            else {
                prev2.left = temp2.right;
                merge(root1, root2);
            }
        }
    }

    private static void inorderTraversal(Node root) {
        if (root == null)
            return;
        inorderTraversal(root.left);
        System.out.print(root.data + " ");
        inorderTraversal(root.right);
    }
}
