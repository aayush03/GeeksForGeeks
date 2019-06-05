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

    private static class TreeNode {
        int data;
        int horizontalDistance;
        TreeNode left, right;

        public TreeNode(int data) {
            this.data = data;
            this.horizontalDistance = Integer.MAX_VALUE;
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
        System.out.println();
        TreeNode treeRoot = new TreeNode(4);
        treeRoot.left = new TreeNode(5);
        treeRoot.right = new TreeNode(2);
        treeRoot.right.left = new TreeNode(3);
        treeRoot.right.right = new TreeNode(7);
        treeRoot.right.left.left = new TreeNode(6);
        treeRoot.right.left.right = new TreeNode(1);
        tree.printBottomViewByLevel(treeRoot);
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

    private void printBottomViewByLevel(TreeNode root) {
        if (root == null)
            return;

        // Initialize a variable 'horizontalDistance' with 0 for the root element.
        int horizontalDistance = 0;

        // TreeMap which stores key value pair sorted on key value 
        Map<Integer, Integer> map = new TreeMap<>();

        // Queue to store tree nodes in level order traversal 
        Queue<TreeNode> queue = new LinkedList<>();

        // Assign initialized horizontal distance value to root 
        // node and add it to the queue. 
        root.horizontalDistance = horizontalDistance;
        queue.add(root);

        // Loop until the queue is empty (standard level order loop) 
        while (!queue.isEmpty())
        {
            TreeNode temp = queue.remove();

            // Extract the horizontal distance value from the 
            // dequeued tree node. 
            horizontalDistance = temp.horizontalDistance;

            // Put the dequeued tree node to TreeMap having key 
            // as horizontal distance. Every time we find a node 
            // having same horizontal distance we need to replace 
            // the data in the map. 
            map.put(horizontalDistance, temp.data);

            // If the dequeued node has a left child add it to the 
            // queue with a horizontal distance horizontalDistance-1.
            if (temp.left != null)
            {
                temp.left.horizontalDistance = horizontalDistance-1;
                queue.add(temp.left);
            }
            // If the dequeued node has a left child add it to the 
            // queue with a horizontal distance horizontalDistance+1.
            if (temp.right != null)
            {
                temp.right.horizontalDistance = horizontalDistance+1;
                queue.add(temp.right);
            }
        }

        // Extract the entries of map into a set to traverse 
        // an iterator over that. 
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();

        // Make an iterator 
        Iterator<Map.Entry<Integer, Integer>> iterator = set.iterator();

        // Traverse the map elements using the iterator. 
        while (iterator.hasNext())
        {
            Map.Entry<Integer, Integer> me = iterator.next();
            System.out.print(me.getValue()+" ");
        }
    }
}
