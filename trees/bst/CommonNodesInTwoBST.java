package trees.bst;

import jdk.nashorn.internal.ir.SetSplitState;

import java.util.Set;
import java.util.TreeSet;

public class CommonNodesInTwoBST {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public void printCommon(Node root1,Node root2)
    {
        //add code here.
        Set<Integer> set1 = new TreeSet<>();
        Set<Integer> set2 = new TreeSet<>();

        getInorderSet(root1,set1);
        getInorderSet(root2,set2);

        set1.retainAll(set2);

        for (Integer i : set1)
            System.out.print(i+" ");
    }

    private void getInorderSet(Node root, Set<Integer> set) {
        if (root == null)
            return;
        getInorderSet(root.left,set);
        set.add(root.data);
        getInorderSet(root.right,set);
    }
}
