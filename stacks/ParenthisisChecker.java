package stacks;

import java.util.Stack;

public class ParenthisisChecker {

    public static void main(String[] args) {
        isBalancedExpression("{([])}");
        isBalancedExpression("()");
        isBalancedExpression("([]");
        isBalancedExpression("[()]{}{[()()]()}");
        isBalancedExpression("[(])");
    }

    private static void isBalancedExpression(String str) {
        if(isBalanced(str))
            System.out.println("balanced");
        else
            System.out.println("not balanced");
    }

    private static boolean isBalanced(String str) {
        if(str != null)
        {
            Stack<Character> st = new Stack<>();
            for(int i=0;i<str.length();i++) {
                char c = str.charAt(i);
                if(!isClosingBracket(c))
                    st.push(c);
                else {
                    if(st.isEmpty())
                        return false;
                    char x = st.pop();
                    if(!isValidPair(x,c))
                        return false;
                }
            }
            if (!st.isEmpty())
                return false;
        }
        return true;
    }

    private static boolean isClosingBracket(char c) {
        if(c == ')' || c== '}' || c==']')
            return true;
        return false;
    }

    private static boolean isValidPair(char open, char close) {
        if(open =='(' && close !=')')
            return false;
        else if(open =='{' && close !='}')
            return false;
        else if(open =='[' && close !=']')
            return false;

        return true;
    }
}
