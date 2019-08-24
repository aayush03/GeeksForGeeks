package trees;

public class BinaryTreeToDoublyLinkedList {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    Node head;

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.left.left = new Node(40);
        root.left.right = new Node(60);
        BinaryTreeToDoublyLinkedList tree = new BinaryTreeToDoublyLinkedList();
        tree.binaryTreeToDoblyLinkedList(root);
        tree.printDoublyLinkedList();
    }

    Node binaryTreeToDoblyLinkedList(Node root) {
        inOrder(root);
        //Node temp = head;
        while (head.left != null)
            head = head.left;

        /*For converting to circular DLL
        head.left = temp;
        temp.right = head;*/
        return head;
    }

    void inOrder(Node root) {
        if (root.left != null)
            inOrder(root.left);
        if (head == null)
            head = new Node(root.data);
        else {
            head.right = root;
            root.left = head;
            head = root;
        }
        if (root.right != null)
            inOrder(root.right);
    }

    private void printDoublyLinkedList() {
        while (head.right != null) {
            System.out.print(head.data + " ");
            head = head.right;
        }
        System.out.print(head.data);
    }
}
