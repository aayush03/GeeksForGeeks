package sliding.window;

/**
 * @author Aayush Srivastava
 */

/**
 * There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * Your score is the sum of the points of the cards you have taken.
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 * <p>
 * Sample Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 */
public class MaximumPointsYouCanObtainFromCards {

    public static void main(String[] args) {
        System.out.println("Maximum Points You Can Obtain from Cards : " + new MaximumPointsYouCanObtainFromCards().maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
        System.out.println("Maximum Points You Can Obtain from Cards : " + new MaximumPointsYouCanObtainFromCards().maxScore(new int[]{2, 2, 2}, 2));
        System.out.println("Maximum Points You Can Obtain from Cards : " + new MaximumPointsYouCanObtainFromCards().maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));
        System.out.println("Maximum Points You Can Obtain from Cards : " + new MaximumPointsYouCanObtainFromCards().maxScore(new int[]{1, 1000, 1}, 1));
        System.out.println("Maximum Points You Can Obtain from Cards : " + new MaximumPointsYouCanObtainFromCards().maxScore(new int[]{96, 90, 41, 82, 39, 74, 64, 50, 30}, 8));
    }

    /**
     * to get the max out of the k points, we essentially want to remove
     * len - k points from the array. In other words, if we have a sliding
     * window with size len - k, we want to remove this window somewhere
     * along the path to make sure the sum of the k points remaining is max.
     */
    public int maxScore(int[] cardPoints, int k) {
        if (cardPoints == null || cardPoints.length == 0) return 0;
        int totalSum = 0;
        for (int c : cardPoints)
            totalSum += c;

        int windowSize = cardPoints.length - k;
        int max = 0;
        int windowSum = 0;
        for (int i = 0; i < cardPoints.length; i++) {
            if (i < windowSize) {
                windowSum += cardPoints[i];
            } else {
                // upadte the max
                max = Math.max(max, totalSum - windowSum);
                // slide the window by subtract the first element in the window
                // and plus the current point cardPoints[i]
                windowSum = windowSum - cardPoints[i - windowSize] + cardPoints[i];
            }
        }

        // dont forget the case where we need to remove the last element
        // see: [96,90,41,82,39,74,64,50,30]
        //      8
        // here we need to remove 30 to get max points
        // Hence we need to compare one more time
        max = Math.max(max, totalSum - windowSum);

        return max;
    }
}
