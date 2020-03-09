package trees;

import java.util.*;

/**
 * Given an array of size N that represents a Tree in such a way that array indexes are values in tree nodes
 * and array values give the parent node of that particular index (or node).
 * The value of the root node index would always be -1 as there is no parent for root.
 * Construct the standard linked representation of Binary Tree from this array representation.
 */

/**
 * The first line of the input contains an integer 'T' denoting the number of test cases.
 * Then 'T' test cases follow. Each test case consists of two lines.
 * First line of each test case contains an integer N denoting the size of the tree array .
 * Second line of each test case consists of 'N' space separated integers denoting the elements of the array .
 */

/**
 * Sample Input:
 * 2
 * 7
 * -1 0 0 1 1 3 5
 * 5
 * -1 0 0 2 2
 */
public class ConstructBinaryTreeFromParentArray {

    static class Node {
        int data;
        Node left, right;

        Node(int key) {
            data = key;
            left = right = null;
        }
    }

    private static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        int[] arr = new int[]{-1, 0, 0, 1, 1, 3, 5};
        Node node = createTree(arr, arr.length);
        printLevelOrder(node);

        arr = new int[]{3, 19, 1, 41, 35, 29, 27, 11, 17, 23, 9, 15, 33, 13, 39, 23, 19, 25, 21, 1, 33, 15, 31, 21, 5, 7, 37, 29, 7, 11, 31, 39, -1, 27, 3, 9, 25, 17, 13, 41, 37, 35};
        node = createTree(arr, arr.length);
        printLevelOrder(node);

        arr = new int[]{1, 13, 31, 7, 17, 33, 27, 1, 5, 15, 19, 3, 33, 17, 19, 21, 23, 25, 31, 11, 29, 13, 27, 7, 25, -1, 23, 15, 3, 11, 21, 5, 9, 9};
        node = createTree(arr, arr.length);
        printLevelOrder(node);

    }

    public static Node createTree(int arr[], int n) {
        Map<Integer, Node> map = new HashMap<>();
        Node root = null;
        for (int i = 0; i < n; i++)
            map.put(i, new Node(i));
        for (int i = 0; i < n; i++) {
            if (arr[i] != -1) {
                Node parent = map.get(arr[i]);
                Node child = map.get(i);
                if (parent.left == null)
                    parent.left = child;
                else
                    parent.right = child;
            } else
                root = map.get(i);
        }
        return root;
    }

    public static void printLevelOrder(Node root) {
        int h = height(root);
        int i;
        for (i = 1; i <= h; i++) {
            result.clear();
            printGivenLevel(root, i);
            Collections.sort(result);
            for (int j = 0; j < result.size(); j++)
                System.out.print(result.get(j) + " ");
        }
        System.out.println();
    }

    public static int height(Node root) {
        if (root == null)
            return 0;
        int lheight = height(root.left);
        int rheight = height(root.right);

        if (lheight > rheight)
            return lheight + 1;

        return rheight + 1;
    }

    private static void printGivenLevel(Node node, int level) {
        if (node == null)
            return;
        if (level == 1)
            result.add(node.data);
        else {
            printGivenLevel(node.left, level - 1);
            printGivenLevel(node.right, level - 1);
        }
    }
}
