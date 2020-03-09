package trees;

/**
 * The LCA or Lowest Common Ancestor of any two nodes N1 and N2 is
 * defined as the common ancestor of both the nodes which is closest
 * to them. That is the distance of the common ancestor from the nodes
 * N1 and N2 should be least possible.
 */
public class LowestCommonAncestorInBinaryTree {

    private static class Node {
        int data;
        Node left, right;

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

        LowestCommonAncestorInBinaryTree tree = new LowestCommonAncestorInBinaryTree();
        System.out.println(tree.findLCA(root, 4, 5).data);
        System.out.println(tree.findLCA(root, 4, 6).data);
        System.out.println(tree.findLCA(root, 3, 4).data);
        System.out.println(tree.findLCA(root, 2, 4).data);
    }

    private Node findLCA(Node root, int n1, int n2) {
        if (root == null)
            return null;
        if (root.data == n1 || root.data == n2)
            return root;

        Node left_lca = findLCA(root.left, n1, n2);
        Node right_lca = findLCA(root.right, n1, n2);

        if (left_lca != null && right_lca != null)
            return root;
        return left_lca != null ? left_lca : right_lca;
    }
}
