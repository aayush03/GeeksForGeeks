package trees.bst;

public class CountNodesInGivenRange {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    void getCountOfNode(Node node, int k1, int k2, Count nodeCount) {
        // Your code here
        if (node == null)
            return;

        if (node.data > k1)
            getCountOfNode(node.left, k1, k2, nodeCount);

        if (node.data >= k1 && node.data <= k2)
            nodeCount.count++;

        if (k2 > node.data)
            getCountOfNode(node.right, k1, k2, nodeCount);
    }
}

class Count {
    int count;
}
