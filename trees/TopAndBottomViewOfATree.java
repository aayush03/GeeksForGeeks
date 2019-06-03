package trees;

import java.util.*;

public class TopAndBottomViewOfATree {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(5);
        root.right = new Node(2);
        root.right.left = new Node(3);
        root.right.right = new Node(7);
        root.right.left.left = new Node(6);
        root.right.left.right = new Node(1);
        TopAndBottomViewOfATree tree = new TopAndBottomViewOfATree();
        tree.printTopView(root);
        System.out.println();
        tree.printBottomView(root);
    }

    private void printTopView(Node root) {
        class QueueObject {
            Node node;
            int horizontalDistance;

            public QueueObject(Node node, int horizontalDistance) {
                this.node = node;
                this.horizontalDistance = horizontalDistance;
            }
        }

        Queue<QueueObject> queue = new LinkedList<>();
        Map<Integer, Node> topViewMap = new TreeMap<>();

        if (root == null)
            return;
        else
            queue.add(new QueueObject(root, 0));

        while (!queue.isEmpty()) {
            QueueObject temp = queue.poll();
            if (!topViewMap.containsKey(temp.horizontalDistance))
                topViewMap.put(temp.horizontalDistance, temp.node);

            if (temp.node.left != null) {
                queue.add(new QueueObject(temp.node.left, temp.horizontalDistance - 1));
            }
            if (temp.node.right != null) {
                queue.add(new QueueObject(temp.node.right, temp.horizontalDistance + 1));
            }

        }

        for (Map.Entry<Integer, Node> entry : topViewMap.entrySet()) {
            System.out.print(entry.getValue().data + " ");
        }
    }

    private void printBottomView(Node root) {
        class QueueObject {
            Node node;
            int horizontalDistance;

            public QueueObject(Node node, int horizontalDistance) {
                this.node = node;
                this.horizontalDistance = horizontalDistance;
            }
        }

        Queue<QueueObject> queue = new LinkedList<>();
        Map<Integer, Node> topViewMap = new TreeMap<>();

        if (root == null)
            return;
        else
            queue.add(new QueueObject(root, 0));

        while (!queue.isEmpty()) {
            QueueObject temp = queue.poll();
            topViewMap.put(temp.horizontalDistance, temp.node);

            if (temp.node.left != null) {
                queue.add(new QueueObject(temp.node.left, temp.horizontalDistance - 1));
            }
            if (temp.node.right != null) {
                queue.add(new QueueObject(temp.node.right, temp.horizontalDistance + 1));
            }

        }

        for (Map.Entry<Integer, Node> entry : topViewMap.entrySet()) {
            System.out.print(entry.getValue().data + " ");
        }
    }
}
