package stacks;

import java.util.Stack;

public class InfixToPostFix {

    public static void main(String[] args) {
        infixToPostFix("a+b*(c^d-e)^(f+g*h)-i");
    }

    private static void infixToPostFix(String str) {
        Stack<Character> opStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (Character.isLetter(c))
                sb.append(c);
            else if (c == '(')
                opStack.push(c);

            else if (c == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(')
                    sb.append(opStack.pop());
                if (!opStack.isEmpty() && opStack.peek() != '(') {
                    System.out.println("Invalid Expression");
                    return;
                } else
                    opStack.pop();
            } else {
                while (!opStack.isEmpty() && precedence(c) <= precedence(opStack.peek())) {
                    if (opStack.peek() == '(') {
                        System.out.println("Invalid Expression");
                        return;
                    }
                    sb.append(opStack.pop());
                }
                opStack.push(c);
            }

        }
        while (!opStack.isEmpty()) {
            if (opStack.peek() == '(')
                System.out.println("Invalid Expression");
            sb.append(opStack.pop());
        }
        System.out.println(sb.toString());
    }

    private static int precedence(char ch) {
        if (ch == '^') return 3;
        else if (ch == '*' || ch == '/') return 2;
        else if (ch == '+' || ch == '-') return 1;
        else return -1;
    }

}
