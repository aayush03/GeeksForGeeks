package sorting;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        //find mid
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode mid = slow;
        mid = mid.next;
        slow.next = null;

        //sort for left half
        ListNode a = sortList(head);
        //sort for right half
        ListNode b = sortList(mid);

        //call merge on sorted halves
        return merge(a, b);
    }

    private ListNode merge(ListNode a, ListNode b) {
        if (a == null)
            return b;
        if (b == null)
            return a;

        ListNode head;
        ListNode curr;

        if (a.val < b.val) {
            head = a;
            curr = a;
            a = a.next;
        } else {
            head = b;
            curr = b;
            b = b.next;
        }

        while (a != null && b != null) {
            if (a.val < b.val) {
                curr.next = a;
                curr = curr.next;
                a = a.next;
            } else {
                curr.next = b;
                curr = curr.next;
                b = b.next;
            }
        }

        if (a != null)
            curr.next = a;

        if (b != null)
            curr.next = b;

        return head;
    }
}

class ListNode {
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