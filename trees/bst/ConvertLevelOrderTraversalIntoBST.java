package trees.bst;

public class ConvertLevelOrderTraversalIntoBST {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    static Node constructBst(int arr[]) {
        if (arr.length == 0)
            return null;

        Node root = null;

        for (int i = 0; i < arr.length; i++)
            root = LevelOrder(root, arr[i]);

        return root;
    }

    static Node LevelOrder(Node root, int data) {
        if (root == null)
            return new Node(data);

        if (data <= root.data)
            root.left = LevelOrder(root.left, data);
        else
            root.right = LevelOrder(root.right, data);

        return root;
    }
}
