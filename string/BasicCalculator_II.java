package string;

import java.util.Stack;

/**
 * @author Aayush Srivastava
 */

/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: " 3+5 / 2 "
 * Output: 5
 * Note:
 *
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculator_II {

    /**
     * In the solution using stack, all the operating of '*' and '/' are performed immediately, so all the numbers in the stack are just to be summed up.
     * A further conclusion is, we can always sum up all the number of the stack except the latest one, without impacting the result.
     * So we just need two vars instead of the stack:
     *
     * tmp for the sum of all the number except the latest one;
     * num for the latest number we are handling.
     */
    public int calculateWithoutExtraSpace(String s) {
        int res = 0;
        int num = 0;
        int temp = 0;
        char opr = '+';
        for (char c : (s + "+").toCharArray()) {
            if (c == ' ')
                continue;
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else {
                switch (opr) {
                    case '+': {
                        res += temp;
                        temp = num;
                        break;
                    }
                    case '-': {
                        res += temp;
                        temp = -num;
                        break;
                    }
                    case '*': {
                        temp *= num;
                        break;
                    }
                    case '/': {
                        temp /= num;
                        break;
                    }
                }
                num = 0;
                opr = c;
            }
        }

        res += temp;
        return res;
    }

    /**
     * If all the operators have the same priority, we can calculate all the numbers and operators one by one and save just the latest result.
     * However, we need to handle the priorities of operators, i.e. '*' and '/' have higher priority than '+' and '-'.
     * So we use a stack to track the numbers who have been met.
     *
     * A straightforward algorithm is:
     *
     * Use a global var opr to remember the latest operator we have met, a global var num for the number we are dealing with, and a stack to accommodate the numbers.
     *
     * Go through the string by characters, and the rule is:
     *
     * If current character is blank, continue to next step.
     * If current character is digit, multiply num with 10 and add current value to num:
     *                 num = num * 10 + (chr - '0');
     * Otherwise, current character should be a operator, which means we have finished a new number (or hit the end of the string).
     * At the time, we process the number with the latest operator opr by the rule:
     * If opr is '+' or '-', we are not sure about the operating because there might be a operator with higher priority in the future. So add num or -num to the stack:
     *
     *                     case '+':
     *                         stack.push(num);
     *                         break;
     *                     case '-':
     *                         stack.push(-num);
     *                         break;
     * If opr is '*' or '/', we can perform the operating immediately. So get the latest number of the stack, perform the operating and push the result back to the stack:
     *
     *                     case '*':
     *                         stack.push(stack.pop() * num);
     *                         break;
     *                     case '/':
     *                         stack.push(stack.pop() / num);
     *                         break;
     * At last, we have a stack containing a couple of numbers and all of them have the same operator '+'. Just sum them up and return the result:
     *
     *         while(!stack.isEmpty())res += stack.pop();
     * Note that we need to initialize opr to '+' to handle the first number:
     *
     *          char opr = '+';
     * and add an extra '+' to the end of the string to make sure the latest number to be hanlded:
     *
     *         for(char chr : (s + "+").toCharArray()) {
     */
    public int calculate(String s) {
        int res = 0, num = 0;
        char opr = '+';
        Stack<Integer> stack = new Stack<>();
        for (char chr : (s + "+").toCharArray()) {
            if (chr == ' ') continue;
            if (Character.isDigit(chr)) {
                num = num * 10 + (chr - '0');
            } else {
                switch (opr) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                    default:
                        return -1;
                }
                num = 0;
                opr = chr;
            }
        }
        while (!stack.isEmpty()) res += stack.pop();

        return res;
    }
}
