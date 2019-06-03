package stacks;

import java.util.Stack;

public class BalancedParenthisis {

    public static void main(String[] args) {
        String s = "[()]";
        int n = s.length();
        System.out.println("result==>" + checkParenthisis(s, n - 1));

        s = "(((([]))";
        n = s.length();
        System.out.println("result==>" + checkParenthisis(s, n - 1));

        s = "([)]";
        n = s.length();
        System.out.println("result==>" + checkParenthisis(s, n - 1));

        s = "{([])}";
        n = s.length();
        System.out.println("result==>" + checkParenthisis(s, n - 1));
    }

    private static boolean checkParenthisis(String str, int n) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <= n; i++) {
            char c = str.charAt(i);
            if (checkIfOpenBracket(c))
                stack.push(str.charAt(i));

            else if ((stack.size() == 0) || (stack.pop() != getReverseBracket(str.charAt(i))))
                return false;
        }

        if (stack.size() == 0)
            return true;
        return false;
    }

    private static boolean checkIfOpenBracket(char c) {
        if (c == '(' || c == '{' || c == '[')
            return true;
        return false;
    }

    private static char getReverseBracket(char c) {
        switch (c) {
            case '(':
                return ')';
            case '{':
                return '}';
            case '[':
                return ']';
            case ')':
                return '(';
            case '}':
                return '{';
            case ']':
                return '[';
            default:
                throw new IllegalArgumentException("Illegal character : " + c);
        }
    }
}
