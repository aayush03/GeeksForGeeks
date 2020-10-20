package hash.table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aayush Srivastava
 */

/**
 * In a list of songs, the i-th song has a duration of time[i] seconds.
 * <p>
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 * Example 2:
 * <p>
 * Input: [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 */
public class PairsOfSongsWithTotalDurationsDivisibleBy60 {

    public int numPairsDivisibleBy60(int[] time) {
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int t : time) {
            int reducedTime = t % 60;
            int other = (reducedTime == 0) ? 0 : 60 - reducedTime;
            ans += count.getOrDefault(other, 0);
            count.put(reducedTime, count.getOrDefault(reducedTime, 0) + 1);
        }
        return ans;
    }
}
