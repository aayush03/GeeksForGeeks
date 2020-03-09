package trees;

/**
 * The diameter of a tree T is the largest of the following quantities:
 * The diameter of T's left sub
 * The diameter of T's right sub
 * The longest path between leaves that goes through the root of T (this can be computed from the heights of the subtrees of T).
 */

/**
 * The longest path between leaves that goes through a particular node say, nd can be calculated as:
 * 1 + height of left subtree of nd + height of right subtree of nd
 */

/**
 * Therefore, final Diameter of a node can be calculated as:
 * Diameter = maximum(lDiameter, rDiameter, 1 + lHeight + rHeight)
 * Where,
 * lDiameter = Diameter of left subtree
 * rDiameter = Diameter of right subtree
 * lHeight = Height of left subtree
 * rHeight = Height of right subtree
 */

/**
 * Time Complexity: O(N^2), where N is the number of nodes in the binary tree.
 */
public class DiameterOfTree {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    int res = 0;

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        DiameterOfTree tree = new DiameterOfTree();
        System.out.println("Diameter of treee = "+tree.diameter(root));

        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.right.left = new Node(4);
        node.right.right = new Node(6);
        node.right.left.right = new Node(5);
        node.right.left.right.left = new Node(7);
        tree.diameterOfTreeEfficient(node);
        System.out.println("Diameter of tree = " + tree.res);

    }

    /**
     * Time Complexity: O(N)
     */
    private int diameterOfTreeEfficient(Node root) {
        if (root == null)
            return 0;

        int lHeight = diameterOfTreeEfficient(root.left);
        int rHeight = diameterOfTreeEfficient(root.right);

        res = Math.max(res, 1 + lHeight + rHeight);

        return  1 + Math.max(lHeight, rHeight);
    }

    private int diameter(Node root) {
        if (root == null)
            return 0;
        int left_height = height(root.left);
        int right_height = height(root.right);

        int left_diameter = diameter(root.left);
        int right_diameter = diameter(root.right);

        return max(left_diameter, right_diameter, 1 + left_height + right_height);
    }

    private int height(Node root) {
        if (root == null)
            return 0;
        return 1 + max(height(root.left), height(root.right));
    }

    private int max(int x, int y) {
        return (x > y) ? x : y;
    }

    private int max(int x, int y, int z) {
        return (x > y && x > z) ? x : ((y > x && y > z) ? y : z);
    }
}
