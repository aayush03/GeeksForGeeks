package trees;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aayush Srivastava
 */

/**
 * A binary tree is given such that each node contains an additional random pointer which could point to any node in the tree or null.
 *
 * Return a deep copy of the tree.
 *
 * The tree is represented in the same input/output way as normal binary trees where each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (in the input) where the random pointer points to, or null if it does not point to any node.
 * You will be given the tree in class Node and you should return the cloned tree in class NodeCopy.
 * NodeCopy class is just a clone of Node class with the same attributes and constructors.
 *
 *
 * Example 1:
 *
 * Input: root = [[1,null],null,[4,3],[7,0]]
 * Output: [[1,null],null,[4,3],[7,0]]
 * Explanation: The original binary tree is [1,null,4,7].
 * The random pointer of node one is null, so it is represented as [1, null].
 * The random pointer of node 4 is node 7, so it is represented as [4, 3] where 3 is the index of node 7 in the array representing the tree.
 * The random pointer of node 7 is node 1, so it is represented as [7, 0] where 0 is the index of node 1 in the array representing the tree.
 *
 *
 * Example 2:
 *
 * Input: root = [[1,4],null,[1,0],null,[1,5],[1,5]]
 * Output: [[1,4],null,[1,0],null,[1,5],[1,5]]
 * Explanation: The random pointer of a node can be the node itself.
 *
 *
 * Example 3:
 *
 * Input: root = [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
 * Output: [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
 *
 *
 * Example 4:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Example 5:
 *
 * Input: root = [[1,null],null,[2,null],null,[1,null]]
 * Output: [[1,null],null,[2,null],null,[1,null]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 1000].
 * Each node's value is between [1, 10^6].
 */
public class CloneBinaryTreeWithRandomPointer {

    /**
     * This can be solved with a simple DFS with a HashMap.
     * First we check if the node is null, if not, we check if it's in our map,
     * if not - we create a copy of our current node and run DFS again on the left, right and random nodes.
     *
     * One edge case to notice is that if there's a cycle in the tree (a random pointer looking at previous visited node) then the map would never be filled.
     * To solve this we need to create the CopyNode, and immediately put it in the map.
     * That way, when there's a cycle - we'd just return the node we put in the map and end the infinite loop.
     */

    /**
     * Time complexity: O(n) - we run on the entire tree once. Our map acts as memoization to prevent redoing work.
     * Space copmlexity: O(n) - We're creating a hashmap to save all nodes in the tree.
     */
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node,NodeCopy> map = new HashMap<>();
        return dfs(root, map);
    }

    public NodeCopy dfs(Node root, Map<Node,NodeCopy> map){
        if (root==null)
            return null;

        if (map.containsKey(root))
            return map.get(root);

        NodeCopy newNode = new NodeCopy(root.val);
        map.put(root,newNode);

        newNode.left=dfs(root.left,map);
        newNode.right=dfs(root.right,map);
        newNode.random=dfs(root.random,map);

        return newNode;

    }

    class Node {
        int val;
        Node left;
        Node right;
        Node random;

        Node() {
        }

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right, Node random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    class NodeCopy {
        int val;
        NodeCopy left;
        NodeCopy right;
        NodeCopy random;

        NodeCopy() {
        }

        NodeCopy(int val) {
            this.val = val;
        }

        NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }
}
