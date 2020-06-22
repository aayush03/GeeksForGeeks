package string;

import java.util.Stack;

/**
 * @author Aayush Srivastava
 */

/**
 * Return the result of evaluating a given boolean expression, represented as a string.
 * <p>
 * An expression can either be:
 * <p>
 * "t", evaluating to True;
 * "f", evaluating to False;
 * "!(expr)", evaluating to the logical NOT of the inner expression expr;
 * "&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
 * "|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
 * <p>
 * <p>
 * Sample Input: expression = "!(f)"
 * Output: true
 */
public class ParseBooleanExpression {


    public static void main(String[] args) {
        System.out.println("Expression evaluation : " + new ParseBooleanExpression().parseBoolExpr("!(f)"));
        System.out.println("Expression evaluation : " + new ParseBooleanExpression().parseBoolExpr("|(f,t)"));
        System.out.println("Expression evaluation : " + new ParseBooleanExpression().parseBoolExpr("&(t,f)"));
        System.out.println("Expression evaluation : " + new ParseBooleanExpression().parseBoolExpr("|(&(t,f,t),!(t))"));
    }

    public boolean parseBoolExpr(String expression) {
        return parseBoolExpr(expression.toCharArray());
    }

    private boolean parseBoolExpr(char[] arr) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == ',')
                continue;
            if (c != ')') {
                stack.push(c);
            } else {
                StringBuilder innerExpression = new StringBuilder();
                while (stack.peek() != '(') {
                    char ch = stack.pop();
                    innerExpression.append(ch);
                }

                stack.pop();//Removing the '(' bracket

                char operand = stack.pop();
                char result = evaluateExpression(operand, innerExpression.toString());

                if (stack.isEmpty())
                    return result == 't' ? true : false;
                else
                    stack.push(result);
            }
        }

        return false;
    }

    private char evaluateExpression(char operand, String innerExpression) {
        switch (operand) {
            case '&':
                return innerExpression.contains("f") ? 'f' : 't';
            case '|':
                return innerExpression.contains("t") ? 't' : 'f';
        }

        return innerExpression.charAt(0) == 'f' ? 't' : 'f';
    }
}
