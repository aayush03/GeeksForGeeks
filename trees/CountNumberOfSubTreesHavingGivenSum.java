package trees;

/**
 * @author Aayush Srivastava
 */
public class CountNumberOfSubTreesHavingGivenSum {

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

        System.out.println(new CountNumberOfSubTreesHavingGivenSum().countSubtreesWithSum(root, x));

    }

    /*class Pair{
        int sum=0;
        int count=0;

    };
    Pair helper(Node root,int x)
    {
        Pair p = new Pair();
        if(root==null)
        {
            p.sum=0;
            p.count=0;
            return p;
        }
        Pair left=helper(root.left,x);
        Pair right=helper(root.right,x);

        if(left.sum+right.sum+root.data==x)
        {
            p.count=1+left.count+right.count;
        }
        else
            p.count=left.count+right.count;

        p.sum=left.sum+right.sum+root.data;
        return p;
    }
    int countSubtreesWithSum(Node root, int x)
    {
        if (root == null)return 0;

        Pair result=helper(root,x);
        return result.count;

    }*/

    int countSubtreesWithSum(Node root, int targetSum) {
        if (root == null)
            return targetSum == 0 ? 1 : 0;
        countSubtreesWithSumUtil(root, targetSum);

        return count;
    }

    int count = 0;

    private int countSubtreesWithSumUtil(Node root, int targetSum) {
        if (root == null)
            return 0;
        int left = countSubtreesWithSumUtil(root.left, targetSum);
        int right = countSubtreesWithSumUtil(root.right, targetSum);
        int sum = root.data + left + right;
        if (sum == targetSum)
            count++;
        return sum;
    }
}
