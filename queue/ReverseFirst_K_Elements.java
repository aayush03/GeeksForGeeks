package queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseFirst_K_Elements {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        ReverseFirst_K_Elements obj = new ReverseFirst_K_Elements();

        queue = obj.modifyQueue(queue, 3);

        queue.stream().forEach(integer -> System.out.print(integer + " "));
    }

    public Queue<Integer> modifyQueue(Queue<Integer> q, int k) {
        Queue<Integer> queue = new LinkedList<>();

        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= k; i++) {
            stack.push(q.remove());
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        while (!q.isEmpty()) {
            queue.add(q.remove());
        }

        return queue;
    }
}
