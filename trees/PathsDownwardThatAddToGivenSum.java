package trees;

public class PathsDownwardThatAddToGivenSum {

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    static int count = 0;

    public static void main(String[] args) {
        Node node3 = new Node(10);
        node3.left = new Node(5);
        node3.right = new Node(-3);
        node3.left.left = new Node(3);
        node3.left.right = new Node(2);
        node3.right.right = new Node(11);
        node3.left.left.left = new Node(3);
        node3.left.left.right = new Node(-2);
        node3.left.right.right = new Node(1);

        System.out.println(countPathsThatAddToGivenSum(node3, 8));
    }

    private static int countPathsThatAddToGivenSum(Node root, int sum) {
        if (root == null)
            return 0;

        countPathsUtil(root, 0, sum);
        countPathsThatAddToGivenSum(root.left, sum);
        countPathsThatAddToGivenSum(root.right, sum);

        return count;
    }

    private static void countPathsUtil(Node root, int currentSum, final int totalSum) {
        if (root == null)
            return;
        if (root.val + currentSum == totalSum)
            count++;
        countPathsUtil(root.left, currentSum + root.val, totalSum);
        countPathsUtil(root.right, currentSum + root.val, totalSum);
    }

}
