package trees.n.ary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aayush Srivastava
 */
public class CloneN_AryTree {

    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * Time complexity: O(n) - we run on the entire tree once.
     */
    public Node cloneTree(Node root) {
        return dfs(root);
    }

    private Node dfs(Node root) {
        if (root == null)
            return null;
        Node newNode = new Node(root.val);
        for (Node child : root.children)
            newNode.children.add(dfs(child));
        return newNode;
    }
}
