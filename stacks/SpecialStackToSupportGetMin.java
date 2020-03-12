package stacks;

import java.util.Stack;

public class SpecialStackToSupportGetMin extends Stack<Integer> {

    private Stack<Integer> min = new Stack<>();

    public static void main(String[] args) {
        SpecialStackToSupportGetMin stack = new SpecialStackToSupportGetMin();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(25);
        System.out.println(stack.getMin());
        stack.push(5);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
    }

    @Override
    public Integer push(Integer x) {
        if (isEmpty()) {
            min.push(x);
        } else {
            int y = min.peek();
            if(x < y)
                min.push(x);
            else
                min.push(y);
        }
        return super.push(x);
    }

    @Override
    public Integer pop() {
        min.pop();
        return super.pop();
    }

    int getMin() {
        return min.peek();
    }
}
