package trees;

public class CheckIfSumOfNodesEqualsRoot {
    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(8);
        root.left.left = new Node(-1);
        root.left.right = new Node(3);
        root.left.right.left = new Node(1);
        root.left.right.right = new Node(2);
        CheckIfSumOfNodesEqualsRoot tree = new CheckIfSumOfNodesEqualsRoot();
        System.out.println(tree.checkSum(root));
    }

    boolean checkSum(Node root) {
        if (root == null)
            return true;

        if (root.left == null && root.right == null)
            return true;
        int sum = 0;
        if (root.left != null)
            sum += root.left.data;
        if (root.right != null)
            sum += root.right.data;

        return (sum == root.data && checkSum(root.left) && checkSum(root.right));

    }
}
