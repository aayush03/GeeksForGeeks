package queue;

import java.util.Stack;

public class QueueUsingTwoStacks {

    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public static void main(String[] args) {

        QueueUsingTwoStacks queue = new QueueUsingTwoStacks();

        queue.insert(2);
        queue.insert(3);
        System.out.println(queue.remove());
        queue.insert(4);
        System.out.println(queue.remove());
    }

    /* The method insert to push element
       into the queue */
    void insert(int B) {
        s1.push(B);
    }


    /* The method remove which return the
      element popped out of the queue*/
    int remove() {
        int k = -1;
        while (s1.size() > 1) {
            k = s1.pop();
            s2.push(k);
        }

        if (!s1.isEmpty())
            k = s1.pop();

        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }

        return k;
    }
}
