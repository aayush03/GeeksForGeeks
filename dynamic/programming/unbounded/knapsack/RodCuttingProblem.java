package dynamic.programming.unbounded.knapsack;

import java.util.ArrayList;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces
 */
public class RodCuttingProblem {

    public static void main(String[] args) {
        int arr[] = new int[]{1, 5, 8, 9, 10, 17, 17, 20};
        getMaxValueFromCuttingRod(arr, arr.length);
    }

    private static void getMaxValueFromCuttingRod(int[] arr, int n) {
        int[] len = new int[n];

        for (int i = 0; i < n; i++)
            len[i] = i + 1;

        getMaxValueFromCuttingRod(len, arr, n);
    }

    private static void getMaxValueFromCuttingRod(int[] len, int[] val, int rodLength) {
        int n = val.length;
        int[][] t = new int[n + 1][rodLength + 1];

        // filling up the matrix
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= rodLength; j++) {
                if (len[i - 1] <= j)
                    t[i][j] = Math.max(t[i - 1][j], val[i - 1] + t[i][j - len[i - 1]]);
                else
                    t[i][j] = t[i - 1][j];
            }
        }

        System.out.println("Maximum Obtainable Value is : " + t[n][rodLength]);
        // Getting the rods
        int x = n, y = rodLength;
        ArrayList<Integer> rods = new ArrayList<>();
        while (x > 0 && y > 0) {
            if (t[x - 1][y] == t[x][y])
                x--;
            else if (t[x - 1][y] >= val[x - 1] + t[x][y - len[x - 1]])
                x--;
            else {
                System.out.println("Including item " + x + " with value = " + val[x - 1] + " and length = " + len[x - 1]);
                rods.add(len[x - 1]);
                y -= len[x - 1];
            }
        }

        // Marking points at which cut has to be made.
        int cut = 0;
        for (int a : rods) {
            cut += a;
            System.out.println("Cut the rod at x = " + cut);
        }
    }
}
