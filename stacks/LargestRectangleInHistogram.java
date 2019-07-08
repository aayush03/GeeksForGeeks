package stacks;

import java.util.Stack;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] arr = new int[]{6, 2, 5, 4, 5, 1, 6};

        System.out.println(getMaxArea(arr, arr.length));
    }

    private static int getMaxArea(int[] histogram, int n) {
        Stack<Integer> stack = new Stack<>();

        int top;
        int maxArea = 0;
        int areaWithTop;
        int i = 0;
        while (i < n) {
            if (stack.isEmpty() || histogram[stack.peek()] <= histogram[i])
                stack.push(i++);
            else {
                top = stack.pop();

                areaWithTop = histogram[top] * (stack.isEmpty() ? i : i - stack.peek() - 1);

                if (maxArea < areaWithTop)
                    maxArea = areaWithTop;
            }
        }

        while (!stack.isEmpty()) {
            top = stack.pop();

            areaWithTop = histogram[top] * (stack.isEmpty() ? n : n - stack.peek() - 1);

            if (maxArea < areaWithTop)
                maxArea = areaWithTop;
        }

        return maxArea;
    }
}
