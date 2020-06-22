package dynamic.programming.matrix.chain.multiplication.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a sequence of matrices, find the most efficient way to multiply these matrices together.
 * The problem is not actually to perform the multiplications, but merely to decide in which order to perform the multiplications.
 *
 * We have many options to multiply a chain of matrices because matrix multiplication is associative.
 * In other words, no matter how we parenthesize the product, the result will be the same.
 *
 * For example, if we had four matrices A, B, C, and D, we would have:
 *
 *     (ABC)D = (AB)(CD) = A(BCD) = ....
 * However, the order in which we parenthesize the product affects the number of simple arithmetic operations needed to compute the product, or the efficiency. For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,
 *
 *     (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
 *     A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
 * Clearly the first parenthesization requires less number of operations.
 */

/**
 * Given an array p[] which represents the chain of matrices such that the ith matrix Ai is of dimension p[i-1] x p[i].
 * We need to write a function MatrixChainOrder() that should return the minimum number of multiplications needed to multiply the chain.
 *
 * Sample Input: p[] = {10, 20, 30, 40, 30}
 *   Output: 30000
 *   There are 4 matrices of dimensions 10x20, 20x30, 30x40 and 40x30.
 *   Let the input 4 matrices be A, B, C and D.  The minimum number of
 *   multiplications are obtained by putting parenthesis in following way
 *   ((AB)C)D --> 10*20*30 + 10*30*40 + 10*40*30
 */
public class MatrixChainMultiplication {

    public static void main(String[] args) {
        System.out.println("Minimum number of multiplications required using recursion is : "+ new MatrixChainMultiplication().minCostOfMultiplyingMatricesRecursive(new int[]{10, 20, 30, 40, 30}));
        System.out.println("Minimum number of multiplications required using memoization is : "+ new MatrixChainMultiplication().minCostOfMultiplyingMatricesMemoization(new int[]{10, 20, 30, 40, 30}));
    }

    private int minCostOfMultiplyingMatricesRecursive(int[] arr) {
        return minCostOfMultiplyingMatricesRecursive(arr, 1, arr.length - 1);
    }

    private int minCostOfMultiplyingMatricesRecursive(int[] arr, int i, int j) {
        if (i >= j)
            return 0;
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int tempResult = minCostOfMultiplyingMatricesRecursive(arr, i, k) +
                    minCostOfMultiplyingMatricesRecursive(arr, k + 1, j) +
                    (arr[i - 1] * arr[k] * arr[j]);
            min = Math.min(min, tempResult);
        }

        return min;
    }

    /**
     * Time Complexity: O(n^3)
     * Auxiliary Space: O(n^2)
     */
    int[][] memo;
    private int minCostOfMultiplyingMatricesMemoization(int[] arr) {
        initializeMemoTable(arr.length);
        return minCostOfMultiplyingMatricesMemoization(arr, 1, arr.length - 1);
    }

    private int minCostOfMultiplyingMatricesMemoization(int[] arr, int i, int j) {
        if (i >= j)
            return 0;
        if (memo[i][j] != -1)
            return memo[i][j];

        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int tempResult = minCostOfMultiplyingMatricesMemoization(arr, i, k) +
                    minCostOfMultiplyingMatricesMemoization(arr, k + 1, j) +
                    (arr[i - 1] * arr[k] * arr[j]);
            min = Math.min(min, tempResult);
        }

        return memo[i][j] = min;
    }

    private void initializeMemoTable(int n) {
        memo = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++)
                memo[i][j] = -1;
    }
}
