package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aayush Srivastava
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
