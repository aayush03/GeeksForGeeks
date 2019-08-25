package trees.bst;

public class PrintNodesInGivenRange {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    void printNearNodes(Node node, int k1, int k2) {
        // Your code here
        if (node == null)
            return;

        if (node.data > k1)
            printNearNodes(node.left, k1, k2);

        if (node.data >= k1 && node.data <= k2)
            System.out.print(node.data + " ");

        if (k2 > node.data)
            printNearNodes(node.right, k1, k2);
    }
}
