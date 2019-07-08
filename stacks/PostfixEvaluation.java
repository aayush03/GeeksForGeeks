package stacks;

import java.util.Stack;

public class PostfixEvaluation {

    private static final String ILLEGAL_OPERAND_TYPE = "Illegal Operand Type";

    enum ArithmeticOperationType {
        ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/');

        char operatorTpe;

        ArithmeticOperationType(char c) {
            this.operatorTpe = c;
        }

        public static ArithmeticOperationType valueOf(Character ch) {
            switch (ch) {
                case '+':
                    return ADD;
                case '-':
                    return SUBTRACT;
                case '*':
                    return MULTIPLY;
                case '/':
                    return DIVIDE;
                default:
                    throw new IllegalArgumentException(ILLEGAL_OPERAND_TYPE);
            }
        }

    }

    public static void main(String[] args) {
        postfixEvaluator("78+32+/");
    }

    private static void postfixEvaluator(String str) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (Character.isLetterOrDigit(c))
                stack.push(Integer.parseInt(String.valueOf(c)));
            else if (!stack.isEmpty()) {
                int secondOperand = stack.pop();
                int firstOperand = 0;
                if (!stack.isEmpty())
                    firstOperand = stack.pop();
                else {
                    System.out.println("Invalid Expression");
                    return;
                }

                int arithmeticResult = performArithmeticOperation(firstOperand, secondOperand, ArithmeticOperationType.valueOf(c));
                stack.push(arithmeticResult);
            }
        }

        System.out.println("Result : " + stack.pop());
    }

    private static int performArithmeticOperation(int firstOperand, int secondOperand, ArithmeticOperationType operationType) {
        switch (operationType) {
            case ADD:
                return firstOperand + secondOperand;
            case SUBTRACT:
                return firstOperand - secondOperand;
            case MULTIPLY:
                return firstOperand * secondOperand;
            case DIVIDE:
                return firstOperand / secondOperand;
        }

        throw new IllegalArgumentException(ILLEGAL_OPERAND_TYPE);
    }
}

