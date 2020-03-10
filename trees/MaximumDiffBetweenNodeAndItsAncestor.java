package trees;

/**
 * Given a Binary Tree, you need to find the maximum value which you can get by subtracting the value of
 * node B from the value of node A, where A and B are two nodes of the binary tree and A is an ancestor of B.
 */
public class MaximumDiffBetweenNodeAndItsAncestor {

    static class Node {
        int data;
        Node left, right;

        Node(int key) {
            data = key;
            left = right = null;
        }
    }

    public static void main(String[] args) {

    }

    int maxDiff(Node root) {
        Diff d = new Diff();
        maxDiff(root, d);
        return d.diff;
    }

    int maxDiff(Node root, Diff d) {
        if (root == null)
            return Integer.MAX_VALUE;
        if (root.left == null && root.right == null)
            return root.data;
        int l = maxDiff(root.left, d);
        int r = maxDiff(root.right, d);
        int m = Math.min(l, r);
        d.diff = Math.max(d.diff, root.data - m);

        return Math.min(m, root.data);
    }
}


class Diff {
    int diff;

    Diff() {
        this.diff = Integer.MIN_VALUE;
    }
}