package trees;

public class FoldableBinaryTree {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(7);
        root.right = new Node(15);
        root.left.right = new Node(9);
        root.right.left = new Node(11);

        System.out.println(isFoldable(root));
    }

    public static boolean isFoldable(Node root)
    {
        // add your code here
        if(root == null)
            return true;
        if((root.left!= null && root.right == null) ||
                (root.left == null && root.right != null))
            return false;

        mirror(root.left);

        boolean res = isStructSame(root.left, root.right);

        return res;
    }

    static boolean isStructSame(Node a, Node b)
    {
        if (a == null && b == null)
            return true;
        if (a != null && b != null
                && isStructSame(a.left, b.left)
                && isStructSame(a.right, b.right))
            return true;

        return false;
    }

    public static void mirror(Node node)
    {
        if(node == null)
            return;
        else
        {
            Node temp;

            mirror(node.left);
            mirror(node.right);

            temp = node.left;
            node.left = node.right;
            node.right = temp;
        }

    }
}
