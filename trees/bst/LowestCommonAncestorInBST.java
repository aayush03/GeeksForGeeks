package trees.bst;

public class LowestCommonAncestorInBST {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    Node lca(Node node, int n1, int n2) {
        if (node == null)
            return null;

        // If both n1 and n2 are smaller than root,
        // then LCA lies in left subtree
        if (node.data > n1 && node.data > n2)
            return lca(node.left, n1, n2);

        // If both n1 and n2 are greater than root,
        // then LCA lies in right subtree
        if (node.data < n1 && node.data < n2)
            return lca(node.right, n1, n2);

        return node;
    }

    Node lcaIterative(Node root, int n1, int n2) {
        while (root != null) {
            // If both n1 and n2 are smaller than root, 
            // then LCA lies in left subtree
            if (root.data > n1 && root.data > n2)
                root = root.left;

                // If both n1 and n2 are greater than root, 
                // then LCA lies in the right subtree
            else if (root.data < n1 && root.data < n2)
                root = root.right;

            else break;
        }

        return root;
    }
}
