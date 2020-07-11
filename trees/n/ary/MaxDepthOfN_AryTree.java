package trees.n.ary;

import java.util.LinkedList;
import java.util.Queue;

public class MaxDepthOfN_AryTree {

    public int maxDepth(Node root) {
        if (root == null)
            return 0;
        Queue<Node> q = new LinkedList<>();

        q.add(root);
        int count = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node curr = q.poll();

                for (Node node : curr.children) {
                    q.add(node);
                }
            }
            count++;
        }


        return count;
    }
}
