package stacks;

import java.util.Stack;

/**
 * @author Aayush Srivastava
 */

/**
 * Given two sequences pushed and popped with distinct values, return true if and only if this
 * could have been the result of a sequence of push and pop operations on an initially empty stack.
 * <p>
 * Sample Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * <p>
 * <p>
 * Note:
 * <p>
 * 1.) 0 <= pushed.length == popped.length <= 1000
 * 2.) 0 <= pushed[i], popped[i] < 1000
 * 3.) pushed is a permutation of popped.
 * 4.) pushed and popped have distinct values.
 */
public class ValidateStackSequences {

    public static void main(String[] args) {
        int[] pushed = {1, 2, 3, 4, 5}, popped = {4, 5, 3, 2, 1};
        System.out.println(new ValidateStackSequences().validateStackSequencesWithoutExtraSpace(pushed, popped));
    }


    /**
     * The idea is to use the original pushed array as a stack itself
     */
    public boolean validateStackSequencesWithoutExtraSpace(int[] pushed, int[] popped) {
        int i = 0, j = 0;
        for (int x : pushed) {
            pushed[i++] = x;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                --i;
                ++j;
            }

        }
        return i == 0;
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int x : pushed) {
            stack.push(x);
            while (!stack.empty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.empty();
    }
}
