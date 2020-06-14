package dynamic.programming.partition.subset;

/**
 * @author Aayush Srivastava
 */

/**
 * We have a collection of rocks, each rock has a positive integer weight.
 * <p>
 * Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 * <p>
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)
 * <p>
 * Note:
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 * <p>
 * Sample Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 */
public class MinimumSubsetDifference {

    public static void main(String[] args) {
        System.out.println("Minimum difference of subset in array : " + new MinimumSubsetDifference().minDifference(new int[]{2, 1, 7}));
        System.out.println("Minimum difference of subset in array : " + new MinimumSubsetDifference().minDifference(new int[]{1, 11, 5, 6}));
        System.out.println("Minimum difference of subset in array : " + new MinimumSubsetDifference().minDifference(new int[]{3}));
    }

    private int minDifference(int[] arr) {
        int n = arr.length;

        int maxRangeSum = 0;
        for (int i = 0; i < n; i++)
            maxRangeSum += arr[i];

        /**
         * Say there are two partitions(subsets) P1 & P2 which have sum S1 and S2.
         *
         * The max difference between S1 & S2 can be when P1 equals the whole array and P2 is an empty subset, i.e
         * the max difference can be maxRangeSum - 0
         *
         * If S1 & S2 were equal then the difference would be minimum : 0
         * Considering this and equaling both the sum(s) as S1, we can say that 2 * S1 = maxRangeSum (if S1 == S2)
         * So, we can conclude that for the absolute difference to be positive between the two sum(s),
         * the lower amongst S1 & S2 can only lie between 0 & maxRangeSum / 2, i.e finalRangeUpperBound
         *
         */
        int finalRangeUpperBound = maxRangeSum / 2;

        boolean[][] t = new boolean[n + 1][finalRangeUpperBound + 1];

        for (int i = 0; i <= n; i++)
            t[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= finalRangeUpperBound; j++) {
                if (arr[i - 1] <= j)
                    t[i][j] = t[i - 1][j] || t[i - 1][j - arr[i - 1]];
                else
                    t[i][j] = t[i - 1][j];
            }
        }

        /**
         * After completely evaluating the table "t", the value in the last row corresponds to "the sum of subsets which can be achieved for array size of n (the true values in the table)"
         *
         * The last value of sum "K" for which the the value was true will be twice the value of the possible minimum difference as its compliment (maxRangeSum - K)
         * will also be possible as a subset sum; if we build the table for rest of the half as well
         */
        for (int j = finalRangeUpperBound; j >= 0; j--) {
            if (t[n][j])
                return maxRangeSum - 2 * j;
        }
        /*List<Integer> validRangeList = new ArrayList<>();

        for (int j = 0; j <= finalRangeUpperBound; j++) {
            if (t[n][j])
                validRangeList.add(j);
        }

        int min = Integer.MAX_VALUE;
        for (Integer i : validRangeList)
            min = Math.min(min, maxRangeSum - 2 * i);

        return min;
         */

        return 0;
    }
}
