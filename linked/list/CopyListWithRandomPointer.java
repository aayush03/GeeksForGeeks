package linked.list;

/**
 * @author Aayush Srivastava
 */
public class CopyListWithRandomPointer {

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    private static final int ARBITRARY_HEAD = 0;
    public static Node copyRandomList(Node head) {
        Node temp = head, originalListNext;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (temp != null) {
            originalListNext = temp.next;

            Node copy = new Node(temp.val);
            temp.next = copy;
            copy.next = originalListNext;

            temp = originalListNext;
        }

        // Second round: assign random pointers for the copy nodes.
        temp = head;
        while (temp != null) {
            if (temp.random != null) {
                temp.next.random = temp.random.next;
            }
            temp = temp.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        temp = head;
        Node pseudoHead = new Node(ARBITRARY_HEAD);
        Node copy, copyTemp = pseudoHead;

        while (temp != null) {
            originalListNext = temp.next.next;

            // extract the copy
            copy = temp.next;
            copyTemp.next = copy;
            copyTemp = copy;

            // restore the original list
            temp.next = originalListNext;

            temp = originalListNext;
        }

        return pseudoHead.next;
    }
}

