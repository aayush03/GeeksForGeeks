package stacks;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingTwoQueue {

    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();

    public static void main(String[] args) {
        push(1);
        push(2);
        push(3);
        push(4);
        push(5);

        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());

    }


    /* The method push to push element into the stack */
    static void push(int a) {
        q1.add(a);
    }

    /*The method pop which return the element poped out of the stack*/
    static int pop() {
        int size = q1.size();
        int s = size;
        int x = 0;
        while (!q1.isEmpty()) {
            x = q1.peek();
            if (--size >= 1)
                q2.add(x);

            q1.remove();
        }
        int k = s != 0 ? x : -1;
        q1 = q2;
        q2 = new LinkedList<>();

        return k;

    }
}
