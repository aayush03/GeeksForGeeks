package trees.n.ary;

import java.util.ArrayList;
import java.util.List;

public class N_AryPreAndPostOrderTraversal {

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        list = traversePreOrder(root, list);
        return list;
    }

    public List<Integer> traversePreOrder(Node root, List<Integer> list){
        if(root != null){
            list.add(root.val);
            for(Node node : root.children){
                traversePreOrder(node, list);
            }
        }
        return list;
    }

    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        list = traversePostOrder(root, list);
        return list;
    }

    private List<Integer> traversePostOrder(Node root, List<Integer> list) {
        if (root != null) {
            for (Node child : root.children) {
                traversePostOrder(child, list);
            }
            list.add(root.val);
        }
        return list;
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