package trees;

import java.util.HashSet;
import java.util.Set;

public class PrintAllPathsFromRootWithGivenSum {

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        node1.left = new Node(20);
        node1.right = new Node(3);
        node1.right.left = new Node(4);
        node1.right.right = new Node(15);
        node1.right.left.left = new Node(6);
        node1.right.left.right = new Node(7);
        node1.right.right.left = new Node(8);
        node1.right.right.right = new Node(9);

        printAllPaths(node1, 8);

        System.out.println();

        Node node2 = new Node(10);
        node2.left = new Node(28);
        node2.right = new Node(13);
        node2.right.left = new Node(14);
        node2.right.right = new Node(15);
        node2.right.left.left = new Node(21);
        node2.right.left.right = new Node(22);
        node2.right.right.left = new Node(23);
        node2.right.right.right = new Node(24);

        printAllPaths(node2, 38);


    }


    private static void printAllPaths(Node root, int sum) {
        if (root == null)
            return;
        if (root.val > sum)
            return;
        Set<Integer> set = new HashSet<>();
        set.add(root.val);
        printAllPathsUtil(root.left, sum - root.val, set);


        set = new HashSet<>();
        set.add(root.val);
        printAllPathsUtil(root.right, sum - root.val, set);

    }

    private static void printAllPathsUtil(Node root, int sum, Set<Integer> set) {
        if (root == null)
            return;
        if (root.val > sum)
            return;
        if (root.val == sum) {
            System.out.print("Path found: ");
            set.add(root.val);
            for (int i : set)
                System.out.print(i + " ");
            System.out.println();
        }
        set.add(root.val);
        printAllPathsUtil(root.left, sum - root.val, set);
        printAllPathsUtil(root.right, sum - root.val, set);
        set.remove(root.val);
    }

}
