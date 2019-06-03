package stacks;


/**
 * ############### Most Important question for Stacks along with variations ###############
 */

import java.util.Stack;

/**
 * Span for i(th) day is no of consecutive days before it and including it with same or smaller value
 * <p>
 * Time Complexity = O(n) ; Space Complexity = O(n) extra space
 */

/** ------------------- Check final solution on Geeks ------------------- */
public class SpanOfPricesOfStocks {


    public static void main(String[] args) {
        int[] arr = {10, 5, 3, 8, 20, 18, 16, 17};
        int n = arr.length - 1;

        SpanOfPricesOfStocks obj = new SpanOfPricesOfStocks();

        obj.printSpan(arr, n);
    }

    private void printSpan(int arr[], int n) {
        Stack<Integer> s = new Stack<>();

        s.push(0); //We store indices in stack

        System.out.println("1"); //Span of first element of array

        for (int i = 1; i < n; i++) {
            while (!s.isEmpty() && arr[s.peek()] <= arr[i]) {
                s.pop();
                System.out.println((s.isEmpty() ? (i + 1) : (i - s.peek())));
                s.push(i);
            }
        }
    }
}
