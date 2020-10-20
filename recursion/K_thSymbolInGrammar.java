package recursion;

/**
 * @author Aayush Srivastava
 */

/**
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * <p>
 * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 * <p>
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 * <p>
 * Input: N = 2, K = 1
 * Output: 0
 * <p>
 * Input: N = 2, K = 2
 * Output: 1
 * <p>
 * Input: N = 4, K = 5
 * Output: 1
 * <p>
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 * Note:
 * <p>
 * N will be an integer in the range [1, 30].
 * K will be an integer in the range [1, 2^(N-1)]
 */
public class K_thSymbolInGrammar {

    /**
     * if K % 2 == 1, it is the first number in '01' or '10',
     * if Kth number is 0, K+1 th is 1.
     * if Kth number is 1, K+1 th is 0.
     * so it will be different from K + 1.
     * <p>
     * If K % 2 == 0, it is the second number in '01' or '10', generated from K/2 th number.
     * If Kth number is 0, it is generated from 1.
     * If Kth number is 1, it is generated from 0.
     */
    public int kthGrammar(int N, int K) {
        int res = 0;
        while (K > 1) {
            K = K % 2 == 1 ? K + 1 : K / 2;
            res ^= 1;
        }
        return res;
    }
}
