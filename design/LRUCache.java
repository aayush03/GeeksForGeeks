package design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aayush Srivastava
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    class DoublyLinkedListNode {
        int key;
        int value;
        DoublyLinkedListNode next;
        DoublyLinkedListNode prev;

        public DoublyLinkedListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    private int capacity;
    private Map<Integer, DoublyLinkedListNode> cache;
    private DoublyLinkedListNode head;
    private DoublyLinkedListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        DoublyLinkedListNode node = cache.get(key);

        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        DoublyLinkedListNode node = new DoublyLinkedListNode(key, value);
        if (cache.size() == 0) {
            head = node;
            tail = node;
            cache.put(key, node);
        }
        else if (cache.containsKey(key)) {
            DoublyLinkedListNode temp = cache.get(key);
            if (temp.prev == null) {
                temp.value = value;
            } else {
                moveToHead(temp);
                head.value = value;
            }
        } else {
            if (cache.size() < capacity) {
                node.next = head;
                head.prev = node;
                head = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;

                removeFromTail();
            }

            cache.put(key, node);
        }
    }

    private void removeFromTail() {
        cache.remove(tail.key);
        tail = tail.prev;
        tail.next = null;
    }

    private void moveToHead(DoublyLinkedListNode node) {
        if (node.prev == null) {
            return;
        }

        if (node.next == null) {//last element in the
            tail = node.prev;
            tail.next = null;
            node.next = head;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.next = head;
            node.prev = null;
        }
        head.prev = node;
        head = node;
        head.prev = null;
    }
}
