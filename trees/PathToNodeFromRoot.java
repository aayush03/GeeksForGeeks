package trees;

import java.util.ArrayList;
import java.util.List;

public class PathToNodeFromRoot {

    private static class Node {
        int data;
        Node left, right;

        Node(int data) {
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
        int x = 5;
        printPath(root, x);
    }

    private static void printPath(Node root, int x) {
        List<Integer> arr = new ArrayList<>();

        if (hasPath(root, arr, x)) {
            for (int i = 0; i < arr.size() - 1; i++)
                System.out.print(arr.get(i) + "->");
            System.out.print(arr.get(arr.size() - 1));
        } else
            System.out.print("No Path");
    }

    private static boolean hasPath(Node root, List<Integer> pathList, int x) {
        if (root == null)
            return false;

        pathList.add(root.data);

        if (root.data == x)
            return true;

        if (hasPath(root.left, pathList, x) ||
                hasPath(root.right, pathList, x))
            return true;

        pathList.remove(pathList.size() - 1);
        return false;
    }

}
