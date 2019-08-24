package trees;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class CheckIfNodesAreCousinsAndNotSiblings {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

    }

    private boolean isCousin(Node root, int node1, int node2) {
        Queue<Node> queue = new LinkedList<>();

        ((LinkedList<Node>) queue).add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            Set<Integer> levelByLevelNodes = new HashSet<>();
            for (int i=0; i<size;i++){
                Node curr = queue.poll();
                if ((curr.left.data == node1 && curr.right.data == node2) ||
                        (curr.left.data == node2 && curr.right.data == node1))
                    return false;
                levelByLevelNodes.add(curr.data);
                if (curr.left != null)
                    ((LinkedList<Node>) queue).add(curr.left);
                if (curr.right != null)
                    ((LinkedList<Node>) queue).add(curr.right);
            }

            if (levelByLevelNodes.contains(node1) && levelByLevelNodes.contains(node2))
                return true;
        }

        return false;
    }

    private boolean isSibling(Node root, int node1, int node2) {
        if (root == null)
            return false;
        return (root.left.data == node1 && root.right.data == node2) ||
                (root.left.data == node2 && root.right.data == node1) ||
                (isSibling(root.left,node1,node2)) ||
                (isSibling(root.right,node1,node2));
    }
}
