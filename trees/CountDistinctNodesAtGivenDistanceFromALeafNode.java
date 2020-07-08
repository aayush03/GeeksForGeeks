package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Aayush Srivastava
 */
public class CountDistinctNodesAtGivenDistanceFromALeafNode {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);
        printKDistantfromLeaf(root, 2);
        System.out.println();
        System.out.println(resultSet.size());

        Set<Integer> set = new HashSet<>();
        kDistantFromLeafUtil(root, 0, 2, set);
        System.out.println(set.size());
    }


    static void kDistantFromLeafUtil(Node node, int pathLen, int k, Set<Integer> set) {
        if (node == null)
            return;
        pathLen++;
        if (node.left == null && node.right == null && pathLen - k - 1 >= 0) {
            set.add(node.data);
            return;
        }
        kDistantFromLeafUtil(node.left, pathLen, k, set);
        kDistantFromLeafUtil(node.right, pathLen, k, set);

    }

    static Set<Integer> resultSet = new HashSet<>();
    static int printKDistantfromLeaf(Node node, int k) {
        if (node == null)
            return -1;
        int lk = printKDistantfromLeaf(node.left, k);
        int rk = printKDistantfromLeaf(node.right, k);
        boolean isLeaf = lk == -1 && lk == rk;
        if (lk == 0 || rk == 0 || (isLeaf && k == 0)) {
            System.out.print(" " + node.data);
            resultSet.add(node.data);
        }
        if (isLeaf && k > 0)
            return k - 1; // leaf node 
        if (lk > 0 && lk < k)
            return lk - 1; // parent of left leaf 
        if (rk > 0 && rk < k)
            return rk - 1; // parent of right leaf 

        return -2;

    }

}
