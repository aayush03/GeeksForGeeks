package stacks;

import java.util.Stack;

public class DeleteMiddleElementOfStack {

    public static void main(String[] args) {
        DeleteMiddleElementOfStack obj = new DeleteMiddleElementOfStack();

        Stack<Integer> s = obj.getStackForTesting();
        obj.deleteMidIterative(s, s.size(), 0);
        obj.printElementsInStack(s);

        s = obj.getStackForTesting();
        obj.deleteMidRecursive(s, s.size(), 0);
        obj.printElementsInStack(s);
    }

    public Stack<Integer> deleteMidRecursive(Stack<Integer> s, int sizeOfStack, int current) {
        if (current == sizeOfStack / 2) {
            s.pop();
            return s;
        }

        int x = s.pop();
        current++;

        s = deleteMidRecursive(s, sizeOfStack, current);

        s.push(x);

        return s;
    }

    public Stack<Integer> deleteMidIterative(Stack<Integer> s, int sizeOfStack, int current) {
        if (sizeOfStack == 1)
            return s;

        Stack<Integer> st = new Stack<>();

        int mid = (sizeOfStack / 2) + 1;

        for (int i = 1; i < mid; i++)
            st.push(s.pop());

        s.pop();

        while (!st.isEmpty()) {
            s.push(st.pop());
        }


        return s;
    }

    private Stack<Integer> getStackForTesting() {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        return s;
    }

    private void printElementsInStack(Stack<Integer> s) {
        while (!s.isEmpty())
            System.out.print(s.pop() + " ");
        System.out.println();
    }
}
