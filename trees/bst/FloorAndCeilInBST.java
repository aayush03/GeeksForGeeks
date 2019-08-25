package trees.bst;

public class FloorAndCeilInBST {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(10);
        node.left = new Node(5);
        node.right = new Node(15);
        node.right.left = new Node(12);
        node.right.right = new Node(30);

        FloorAndCeilInBST bst = new FloorAndCeilInBST();

        System.out.println("Floor of 14 is "+bst.floor(node,14));
        System.out.println("Ceil of 11 is "+bst.ceil(node,11));
    }

    private int floor(Node root, int key) {
        if (root == null)
            return Integer.MAX_VALUE;

        if (root.data == key)
            return root.data;

        if (root.data > key)
            return floor(root.left, key);

        int floorValue = floor(root.right, key);
        return floorValue <= key ? floorValue : root.data;
    }

    private int ceil(Node root, int key) {
        if (root == null)
            return Integer.MIN_VALUE;

        if (root.data == key)
            return root.data;

        if (root.data < key)
            return ceil(root.right, key);

        int ceilValue = ceil(root.left, key);
        return ceilValue >= key ? ceilValue : root.data;
    }
}
