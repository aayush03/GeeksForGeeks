package trees.bst;

/**
 * Time Complexity for search, insert and delete operations is O(h), where h is the height of the tree
 */
public class SearchInsertDeleteInBST {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(20);
        root.left.left = new Node(3);
        root.left.right = new Node(8);
        SearchInsertDeleteInBST tree = new SearchInsertDeleteInBST();
        int key = 4;
        System.out.println(tree.search(root, key));
        tree.insert(root, key);
        System.out.println(tree.search(root, key));
        tree.delete(root, key);
        System.out.println(tree.search(root, key));
    }

    private boolean search(Node root, int key) {
        if (root == null)
            return false;
        if (root.data == key)
            return true;
        if (root.data < key)
            return search(root.right, key);
        else
            return search(root.left, key);
    }

    private Node insert(Node root, int key) {
        if (root == null)
            return new Node(key);
        if (root.data == key)
            return root;
        else if (root.data < key)
            root.right = insert(root.right, key);
        else
            root.left = insert(root.left, key);
        return root;
    }

    private Node delete(Node root, int key) {
        if (root == null)
            return root;
        if (root.data > key) {
            root.left = delete(root.left, key);
            return root;
        } else if (root.data < key) {
            root.right = delete(root.right, key);
            return root;
        } else {
            if (isLeaf(root)) {
                return null;
            } else if (root.left == null) {
                Node temp = root.right;
                return temp;
            } else if (root.right == null) {
                Node temp = root.left;
                return temp;
            } else {
                Node successor = getInorderSuccessor(root.right);
                root.data = successor.data;
                root.right = delete(root.right, successor.data);
                return root;
            }
        }
    }

    private boolean isLeaf(Node root) {
        if (root.left == null && root.right == null)
            return true;
        return false;
    }

    private Node getInorderSuccessor(Node root) {
        while (root.left != null)
            root = root.left;
        return root;
    }
}
