package trees.bst;

import java.util.HashSet;
import java.util.Set;

public class PairSumInBST {
    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    static boolean findPair(Node root, int sum) {
        // Your code
        return findPairUtil(root, sum, new HashSet<>());
    }

    static boolean findPairUtil(Node node, int sum, Set<Integer> nodesSet) {
        if (node == null)
            return false;

        if (findPairUtil(node.left, sum, nodesSet))
            return true;

        if (nodesSet.contains(sum - node.data))
            return true;
        else
            nodesSet.add(node.data);

        return findPairUtil(node.right, sum, nodesSet);

    }
}
