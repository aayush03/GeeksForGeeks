package sliding.window;

/**
 * @author Aayush Srivastava
 */
public class MaxConsecutiveOnes_III {

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        System.out.println(longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    public static int longestOnes(int[] A, int K) {
        int zeroCount = 0;
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < A.length; right++) {
            if (A[right] == 0)
                zeroCount++;

            while (zeroCount > K) {
                if (A[left++] == 0)
                    zeroCount--;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
