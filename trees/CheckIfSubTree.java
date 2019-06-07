package trees;

/**
 * Check if S is a subtree of T
 */
public class CheckIfSubTree {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node sTree = new Node(10);
        sTree.left = new Node(4);
        sTree.right = new Node(6);
        sTree.right.left = new Node(30);

        Node tTree = new Node(26);
        tTree.left = new Node(10);
        tTree.left.left = new Node(4);
        tTree.left.right = new Node(6);
        tTree.left.right.left = new Node(30);
        tTree.right = new Node(3);
        tTree.right.right = new Node(3);

        CheckIfSubTree tree = new CheckIfSubTree();
        System.out.println(tree.isSubTree(tTree, sTree));
    }

    private boolean isSubTree(Node T, Node S) {
        return isRootNodeSame(T, S);
    }

    private boolean isRootNodeSame(Node T, Node S) {
        if (T == null && S == null)
            return true;
        if (T == null || S == null)
            return false;

        if (T.data == S.data)
            return isSubTreeSame(T, S);

        return isSubTreeSame(T.left, S) || isSubTreeSame(T.right, S);
    }

    private boolean isSubTreeSame(Node T, Node S) {
        if (T == null && S == null)
            return true;
        else if (T == null || S == null)
            return false;

        if (T.data == S.data)
            return isSubTreeSame(T.left, S.left) && isSubTreeSame(T.right, S.right);

        return false;
    }
}
