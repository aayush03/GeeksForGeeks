package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aayush Srivastava
 */

/**
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 *
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 *
 * Output: [7,4,1]
 *
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 *
 *
 *
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 *
 *
 * Note:
 *
 * The given tree is non-empty.
 * Each node in the tree has unique values 0 <= node.val <= 500.
 * The target node is a node in the tree.
 * 0 <= K <= 1000.
 */
public class AllNodesDistanceK_InBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    Map<TreeNode, Integer> map = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        buildNodeDistanceMap(root, target);
        dfs(root, 0, K, res);
        return res;
    }

    private void buildNodeDistanceMap(TreeNode root, TreeNode target) {
        if (root == null)
            return;
        if (root == target) {
            map.put(root, 0);
            return;
        }
        buildNodeDistanceMap(root.left, target);
        if (map.containsKey(root.left)) {
            map.put(root, map.get(root.left) + 1);
            return;
        }

        buildNodeDistanceMap(root.right, target);
        if (map.containsKey(root.right)) {
            map.put(root, map.get(root.right) + 1);
            return;
        }

    }

    private void dfs(TreeNode root, int dist, final int K, List<Integer> list) {
        if (root == null)
            return;
        if (map.containsKey(root))
            dist = map.get(root);

        if (dist == K)
            list.add(root.val);

        dfs(root.left, dist + 1, K, list);
        dfs(root.right, dist + 1, K, list);
    }
}
