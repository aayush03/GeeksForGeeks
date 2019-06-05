package trees;

public class CheckIfBinaryTreeIsHeightBalanced {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.left.right = new Node(3);

        Node root2 = new Node(10);
        root2.left = new Node(20);
        root2.right = new Node(30);
        root2.left.left = new Node(40);
        root2.left.right = new Node(60);

        CheckIfBinaryTreeIsHeightBalanced treeIsBalanced = new CheckIfBinaryTreeIsHeightBalanced();
        System.out.println(treeIsBalanced.isBalancedBinaryTree(root1));
        System.out.println(treeIsBalanced.isBalancedBinaryTree(root2));
    }

    private boolean isBalancedBinaryTree(Node root) {
        return isBalancedBinaryTree(root, new HeightOfTreeWrapper());
    }

    private boolean isBalancedBinaryTree(Node root, HeightOfTreeWrapper heightOfTree) {
        if (root == null)
            return true;

        HeightOfTreeWrapper leftTreeHeight = new HeightOfTreeWrapper();
        HeightOfTreeWrapper rightTreeHeight = new HeightOfTreeWrapper();

        boolean isLeftTreeHeightBalanced = isBalancedBinaryTree(root.left, leftTreeHeight);
        boolean isRightTreeHeightBalanced = isBalancedBinaryTree(root.right, rightTreeHeight);

        int heightOfLeftSubTree = leftTreeHeight.height;
        int heightOfRightSubTree = rightTreeHeight.height;

        heightOfTree.height = (heightOfLeftSubTree > heightOfRightSubTree ? heightOfLeftSubTree : heightOfRightSubTree) + 1;

        if (Math.abs(heightOfLeftSubTree - heightOfRightSubTree) >= 2)
            return false;

        else
            return isLeftTreeHeightBalanced && isRightTreeHeightBalanced;
    }
}

/**
 * Used to indirectly pass value of height as reference
 */
class HeightOfTreeWrapper {
    int height = 0;
}
