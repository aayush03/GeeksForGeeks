package trees;

public class VerticalWidthOfBinaryTree {

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
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);
        root.right.right.right = new Node(9);
        System.out.println(verticalWidth(root));

        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        System.out.println(verticalWidth(node));
    }

    public static int verticalWidth(Node root) {
        HorizontalDistance rightMostNode = new HorizontalDistance(), leftMostNode = new HorizontalDistance();
        verticalWidthOfTree(root, rightMostNode, leftMostNode, new HorizontalDistance());

        // 1 is added to include root in the width
        return (Math.abs(leftMostNode.horizontalDistance) + rightMostNode.horizontalDistance) + 1;
    }

    private static void verticalWidthOfTree(Node root, HorizontalDistance rightMostNode,
                                            HorizontalDistance leftMostNode, HorizontalDistance curr) {
        if (root == null)
            return;

        verticalWidthOfTree(root.left, rightMostNode,
                leftMostNode, new HorizontalDistance(curr.horizontalDistance - 1));

        // if curr is decreased then get value in leftMostNode
        if (leftMostNode.horizontalDistance > curr.horizontalDistance)
            leftMostNode.horizontalDistance = curr.horizontalDistance;

        // if curr is increased then get value in rightMostNode
        if (rightMostNode.horizontalDistance < curr.horizontalDistance)
            rightMostNode.horizontalDistance = curr.horizontalDistance;

        verticalWidthOfTree(root.right, rightMostNode,
                leftMostNode, new HorizontalDistance(curr.horizontalDistance + 1));

    }

}

class HorizontalDistance {
    int horizontalDistance = 0;

    public HorizontalDistance() {
    }

    public HorizontalDistance(int horizontalDistance) {
        this.horizontalDistance = horizontalDistance;
    }
}