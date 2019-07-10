package stacks;

import java.util.Stack;

public class StockSpanProblem {

    public static void main(String[] args) {
        int[] arr = new int[]{100, 80, 60, 70, 60, 75, 85};
        computeStockSpan(arr, arr.length);
        System.out.println();
        int[] arr1 = new int[]{10, 4, 5, 90, 120, 80};
        computeStockSpan(arr1, arr1.length);
    }

    private static void computeStockSpan(int[] stockPrices, int n) {
        int[] span = new int[n];

        Stack<Integer> stack = new Stack<>();
        span[0] = 1;
        stack.push(0);

        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && stockPrices[i] >= stockPrices[stack.peek()])
                stack.pop();

            span[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());

            stack.push(i);
        }

        for (int i : span) {
            System.out.print(i + " ");
        }
    }

}
