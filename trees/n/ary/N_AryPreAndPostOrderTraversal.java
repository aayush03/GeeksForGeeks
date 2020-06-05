package trees.n.ary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class N_AryPreAndPostOrderTraversal {

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

    public List<Integer> postorder(Node root) {
        if(root == null)
            return new ArrayList<>();

        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();

        stack.add(root);

        while(!stack.isEmpty()) {
            Node node = stack.pollLast();
            res.addFirst(node.val);

            for(Node child : node.children) {
                stack.add(child);
            }
        }

        return res;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}