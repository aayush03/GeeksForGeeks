package trees.n.ary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MaxDepthOfN_AryTree {

    public List<Integer> preorder(Node root) {
        if(root == null)
            return new ArrayList<>();

        LinkedList<Node> stack = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        stack.add(root);

        while(!stack.isEmpty()) {
            Node curr = stack.pollLast();
            res.add(curr.val);
            Collections.reverse(curr.children);

            for(Node node : curr.children) {
                stack.add(node);
            }
        }

        return res;
    }
}
