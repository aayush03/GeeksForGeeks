package trees;

/**
 * Check Solution
 */
public class CheckIfEveryNodeIsSumOfItsDescendants {

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
        root.right = new Node(4);
        root.right.left = new Node(3);
        root.right.right = new Node(1);
        CheckIfEveryNodeIsSumOfItsDescendants tree = new CheckIfEveryNodeIsSumOfItsDescendants();
        System.out.println(tree.checkSum(root, 0));
    }

    private boolean checkSum(Node root, Integer sum) {
        if (root == null) {
            sum = 0;
            return true;
        }
        if (root.left == null || root.right == null) {
            sum = root.data;
            return true;
        }
        int left_sum = 0, right_sum = 0;
        if (!checkSum(root.left, left_sum) || !checkSum(root.right, right_sum))
            return false;
        if (root.data != left_sum + right_sum)
            return false;
        sum = root.data + left_sum + right_sum;
        return true;
        /*if (root.data == getSum(root.left) + getSum(root.right) && checkSum(root.left) && checkSum(root.right))
            return true;
        return false;*/
        //return root.data == sum(root.left) + sum(root.right);
    }

    private int getSum(Node root) {
        if (root == null)
            return 0;
        return getSum(root.left) + getSum(root.right);
    }

    private int sum(Node root) {
        if (root == null)
            return 0;
        return root.data + sum(root.left) + sum(root.right);
    }
}
