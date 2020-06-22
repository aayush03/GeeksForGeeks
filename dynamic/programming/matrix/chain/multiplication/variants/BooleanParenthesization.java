package dynamic.programming.matrix.chain.multiplication.variants;

/**
 * @author Aayush Srivastava
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Given a boolean expression with following symbols.
 * <p>
 * Symbols
 * 'T' ---> true
 * 'F' ---> false
 * And following operators filled between symbols
 * <p>
 * Operators
 * &   ---> boolean AND
 * |   ---> boolean OR
 * ^   ---> boolean XOR
 * Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
 */
public class BooleanParenthesization {

    public static void main(String[] args) {
        System.out.println("Minimum Parenthesis required to evaluate the expression as true using recursion : " + new BooleanParenthesization().countWaysToParenthesizeRecursive("T^F&T"));
        System.out.println("Minimum Parenthesis required to evaluate the expression as true using recursion : " + new BooleanParenthesization().countWaysToParenthesizeRecursive("T^F|F"));
        System.out.println("Minimum Parenthesis required to evaluate the expression as true using recursion : " + new BooleanParenthesization().countWaysToParenthesizeRecursive("T|T&F^T"));
        System.out.println("Minimum Parenthesis required to evaluate the expression as true using recursion : " + new BooleanParenthesization().countWaysToParenthesizeRecursive("T|F^T"));
        System.out.println();
        System.out.println("Minimum Parenthesis required to evaluate the expression as true using memoization : " + new BooleanParenthesization().countWaysToParenthesizeMemoization("T^F&T"));
        System.out.println("Minimum Parenthesis required to evaluate the expression as true using memoization : " + new BooleanParenthesization().countWaysToParenthesizeMemoization("T^F|F"));
        System.out.println("Minimum Parenthesis required to evaluate the expression as true using memoization : " + new BooleanParenthesization().countWaysToParenthesizeMemoization("T|T&F^T"));
        System.out.println("Minimum Parenthesis required to evaluate the expression as true using memoization : " + new BooleanParenthesization().countWaysToParenthesizeMemoization("T|F^T"));
    }

    private int countWaysToParenthesizeRecursive(String s) {
        return countWaysToParenthesizeRecursive(s, 0, s.length() - 1, true);
    }

    /**
     * So input will be like
     * [T, OR, F, AND, T, XOR, F]
     * Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
     * ((T OR F) AND (T XOR F)) -----> this is how we can group them and it will return to true.
     * ((T OR F AND T) XOR (F)) -----> this is another way.
     * <p>
     * This is somewhat similar to Matrix Chain Multiplication where we had to group the input dimensions in a certain manner.
     * <p>
     * symbol[]    = {T, F, T}
     * operator[]  = {^, &}
     * <p>
     * So our expression will become ==> [T ^ F & T] and we have to find number of ways.
     * <p>
     * 4 step approach for Matrix Chain Multiplication related problems.
     * 1) Find i and j ====> it can be i=0; and j = len()-1;
     * 2) Find Base Condition :
     * Before Base condition one important twist in this question.
     * <p>
     * Assume Question is :  [T ^ F]
     * Now we know T ^ T = false
     * T ^ F = true
     * F ^ T = true
     * F ^ F = false.
     * So we can have false on either side.
     * Hence in sub-problem we might also want to find false records in both left and right because they all will contribute
     * to becoming true.
     * <p>
     * 2 (false) XOR (4 true).
     * <p>
     * Now if i match any false with any true, i will get true, hence we need to solve for both
     * true and false in our sub-problems.
     * solve(arr, i, j, isTrue)
     * // Base Condition
     * if(i > j) return 0;
     * it(i==j) {
     * if(isTrue) {
     * if(arr[i] == true) return 1;
     * return 0;
     * } else {
     * // VICE VERSA.
     * }
     * }
     * <p>
     * 3) Choose K
     * since operators are always sandwiched between T and False, so we will start k = 1
     * and do k+=2 every iteration.
     */
    private int countWaysToParenthesizeRecursive(String s, int i, int j, boolean isTrue) {
        if (i > j)
            return 0;
        if (i == j) {
            if (isTrue)
                return s.charAt(i) == 'T' ? 1 : 0;
            return s.charAt(i) == 'F' ? 1 : 0;
        }

        int answer = 0;

        for (int k = i + 1; k <= j - 1; k = k + 2) {
            int trueWaysInLeft = countWaysToParenthesizeRecursive(s, i, k - 1, true);
            int falseWaysInLeft = countWaysToParenthesizeRecursive(s, i, k - 1, false);
            int trueWaysInRight = countWaysToParenthesizeRecursive(s, k + 1, j, true);
            int falseWaysInRight = countWaysToParenthesizeRecursive(s, k + 1, j, false);

            char operatorAtK = s.charAt(k);

            if (operatorAtK == '&') {
                if (isTrue) { // We have to find true with AND operator
                    // that's only possible when left is true and right is true
                    answer += trueWaysInLeft * trueWaysInRight;
                } else { // We have to find false with AND OPERATOR
                    answer += (trueWaysInLeft * falseWaysInRight) + (falseWaysInLeft * trueWaysInRight)
                            + (falseWaysInLeft * falseWaysInRight);
                }
            } else if (operatorAtK == '|') {
                if (isTrue) { // We have to find true with OR operator
                    // that's only possible when left is true and right is true
                    answer += (trueWaysInLeft * trueWaysInRight) +
                            (trueWaysInLeft * falseWaysInRight) +
                            (falseWaysInLeft * trueWaysInRight);
                } else { // We have to find false with OR OPERATOR
                    answer += falseWaysInLeft * falseWaysInRight;
                }
            } else if (operatorAtK == '^') {
                if (isTrue) { // We have to find true with XOR Operator
                    answer += (trueWaysInLeft * falseWaysInRight) + (falseWaysInLeft * trueWaysInRight);
                } else {
                    answer += (falseWaysInLeft * falseWaysInRight) + (trueWaysInLeft * trueWaysInRight);
                }
            }
        }

        return answer;
    }


    private Map<String, Integer> memo;

    private int countWaysToParenthesizeMemoization(String s) {
        memo = new HashMap<>();
        return countWaysToParenthesizeMemoization(s, 0, s.length() - 1, true);
    }

    private int countWaysToParenthesizeMemoization(String s, int i, int j, boolean isTrue) {
        String EMPTY_STRING = "";
        String key = EMPTY_STRING + i + j + isTrue;
        if (i > j) {
            memo.put(key, 0);
            return 0;
        }
        if (i == j) {
            if (isTrue) {
                int value = s.charAt(i) == 'T' ? 1 : 0;
                memo.put(key, value);
                return value;
            } else {
                int value = s.charAt(i) == 'F' ? 1 : 0;
                memo.put(key, value);
                return value;
            }
        }

        int answer = 0;

        for (int k = i + 1; k <= j - 1; k = k + 2) {
            String partialLeftKeyTrue = EMPTY_STRING + i + (k - 1) + "true";
            String partialLeftKeyFalse = EMPTY_STRING + i + (k - 1) + "false";
            String partialRightKeyTrue = EMPTY_STRING + (k + 1) + j + "true";
            String partialRightKeyFalse = EMPTY_STRING + (k + 1) + j + "false";

            int trueWaysInLeft = memo.containsKey(partialLeftKeyTrue) ? memo.get(partialLeftKeyTrue) : countWaysToParenthesizeRecursive(s, i, k - 1, true);
            int falseWaysInLeft = memo.containsKey(partialLeftKeyFalse) ? memo.get(partialLeftKeyFalse) : countWaysToParenthesizeRecursive(s, i, k - 1, false);
            int trueWaysInRight = memo.containsKey(partialRightKeyTrue) ? memo.get(partialRightKeyTrue) : countWaysToParenthesizeRecursive(s, k + 1, j, true);
            int falseWaysInRight = memo.containsKey(partialRightKeyFalse) ? memo.get(partialRightKeyFalse) : countWaysToParenthesizeRecursive(s, k + 1, j, false);

            char operatorAtK = s.charAt(k);

            if (operatorAtK == '&') {
                if (isTrue) { // We have to find true with AND operator
                    // that's only possible when left is true and right is true
                    answer += trueWaysInLeft * trueWaysInRight;
                } else { // We have to find false with AND OPERATOR
                    answer += (trueWaysInLeft * falseWaysInRight) + (falseWaysInLeft * trueWaysInRight)
                            + (falseWaysInLeft * falseWaysInRight);
                }
            } else if (operatorAtK == '|') {
                if (isTrue) { // We have to find true with OR operator
                    // that's only possible when left is true and right is true
                    answer += (trueWaysInLeft * trueWaysInRight) +
                            (trueWaysInLeft * falseWaysInRight) +
                            (falseWaysInLeft * trueWaysInRight);
                } else { // We have to find false with OR OPERATOR
                    answer += falseWaysInLeft * falseWaysInRight;
                }
            } else if (operatorAtK == '^') {
                if (isTrue) { // We have to find true with XOR Operator
                    answer += (trueWaysInLeft * falseWaysInRight) + (falseWaysInLeft * trueWaysInRight);
                } else {
                    answer += (falseWaysInLeft * falseWaysInRight) + (trueWaysInLeft * trueWaysInRight);
                }
            }
        }
        memo.put(key, answer);
        return answer;
    }
}
