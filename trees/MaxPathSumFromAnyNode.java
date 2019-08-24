package trees;

public class MaxPathSumFromAnyNode {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

    }

    private int findMaxSum(Node node, SumWrapper sumWrapper) {
        if (node == null)
            return 0;

        int left_sum = findMaxSum(node.left, sumWrapper);
        int right_sum = findMaxSum(node.right, sumWrapper);

        int max_single = Math.max(Math.max(left_sum,right_sum)+node.data,node.data);

        int maxSumOfTopMostLayer = Math.max(max_single,left_sum+right_sum+node.data);

        sumWrapper.sum = Math.max(maxSumOfTopMostLayer,sumWrapper.sum);

        return max_single;
    }


}


class SumWrapper {
    int sum;
}