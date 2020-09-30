package heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Aayush Srivastava
 */
public class MergeKSortedLists {

    public ListNode mergeKListsWithoutHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return divide(lists, 0, lists.length - 1);
    }

    private ListNode divide(ListNode[] lists, int start, int end) {
        if (start == end)
            return lists[start];
        return mergeTwoSortedLists(divide(lists, start, (start + end) / 2), divide(lists, ((start + end) / 2) + 1, end));
    }

    private ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = null == l1 ? l2 : l1;

        return preHead.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        Queue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists)
            if (node != null)
                pq.add(node);

        while (!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;

            if (tail.next != null)
                pq.add(tail.next);
        }

        return dummy.next;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
