package stacks;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {

    Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        StackUsingQueue obj = new StackUsingQueue();

        obj.push(10);
        obj.push(20);

        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
    }

    private void push(int n) {

        int size = queue.size();

        queue.add(n);

        for (int i = 0; i < size; i++) {
            int x = queue.remove();
            queue.add(x);
        }
    }

    private int pop() {
        if (isEmpty()) {
            System.out.println("No Elements");
            return -1;
        }
        return queue.remove();
    }

    private int peek() {
        if (isEmpty())
            return -1;
        return queue.peek();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }


}
