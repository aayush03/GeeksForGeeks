package stacks;

import java.util.Stack;

public class StrangerCelebrityProblem {

    private int MAT[][];

    public static void main(String[] args) {
        int MATRIX[][] = {{0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 0}};

        StrangerCelebrityProblem problem = new StrangerCelebrityProblem();

        System.out.println("Celebrity ID : " + problem.getId(MATRIX, 4));
    }

    int getId(int M[][], int n) {
        MAT = M;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();

            if (knows(a, b))
                stack.push(b);
            else
                stack.push(a);
        }

        int c = stack.pop();

        for (int i = 0; i < n; i++) {
            if (i != c && (knows(c, i) || !knows(i, c)))
                return -1;
        }

        return c;
    }

    boolean knows(int a, int b) {
        return MAT[a][b] == 1;
    }
}
