package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VerticalOrderTraversal {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(5);
        VerticalOrderTraversal tree = new VerticalOrderTraversal();
        tree.verticalOrderTraversal(root);
    }

    private void verticalOrderTraversal(Node root) {
        TreeMap<Integer, List<Integer>> m = new TreeMap<>();
        int horizontalDistance = 0;
        getVerticalOrder(root, horizontalDistance, m);

        for (Map.Entry<Integer, List<Integer>> entry : m.entrySet()) {
            List list = entry.getValue();
            for (int i = 0; i < list.size(); i++)
                System.out.print(list.get(i) + " ");
        }
    }

    private void getVerticalOrder(Node root, int horizontalDistance, TreeMap<Integer, List<Integer>> nodesAtHorizontalDistanceMap) {
        if (root == null)
            return;

        //get the list at 'horizontalDistance'
        List<Integer> listOfNodesAtHorizontalDistance = nodesAtHorizontalDistanceMap.get(horizontalDistance);

        // Store current node in map 'nodesAtHorizontalDistanceMap'
        if (listOfNodesAtHorizontalDistance == null) {
            listOfNodesAtHorizontalDistance = new ArrayList<>();
            listOfNodesAtHorizontalDistance.add(root.data);
        } else
            listOfNodesAtHorizontalDistance.add(root.data);

        nodesAtHorizontalDistanceMap.put(horizontalDistance, listOfNodesAtHorizontalDistance);

        // Store nodes in left subtree
        getVerticalOrder(root.left, horizontalDistance - 1, nodesAtHorizontalDistanceMap);

        // Store nodes in right subtree
        getVerticalOrder(root.right, horizontalDistance + 1, nodesAtHorizontalDistanceMap);
    }
}
