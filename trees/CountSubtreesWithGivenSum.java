package trees;

public class CountSubtreesWithGivenSum {

    static class Node {
        int data;
        Node left, right;

        Node(int key) {
            data = key;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(-10);
        root.right = new Node(3);
        root.left.left = new Node(9);
        root.left.right = new Node(8);
        root.right.left = new Node(-4);
        root.right.right = new Node(7);

        int x = 7;

        System.out.println(countSubtreesWithSum(root, x));

    }

    private static int countSubtreesWithSum(Node root, int x) {
        if (root == null)
            return 0;

        INT count = new INT(0);

        int leftSum = countSubtreesWithSum(root.left, x, count); //count subtrees for left children without root node data
        int rightSum = countSubtreesWithSum(root.right, x, count); //count subtrees for right children without root node data

        int sum = leftSum + rightSum + root.data;

        if (sum == x) //count subtrees for left & right children with root node data
            count.value++;

        return count.value;
    }

    private static int countSubtreesWithSum(Node root, int x, INT count) {
        if (root == null)
            return 0;

        int leftSum = countSubtreesWithSum(root.left, x, count); // get sum to calculate count of subtrees for left children without root node data
        int rightSum = countSubtreesWithSum(root.right, x, count); //get sum to calculate count of subtrees for right children without root node data

        int sum = leftSum + rightSum + root.data;

        if (sum == x) //count subtrees for left & right children with root node data
            count.value++;
        return sum;
    }
}


class INT {
    int value;

    public INT(int value) {
        this.value = value;
    }
}