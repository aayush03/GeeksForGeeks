package greedy;

/**
 * @author Aayush Srivastava
 */

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.youtube.com/watch?v=uJ8BAQ8lASE
 */

/**
 * Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more
 * subsequences such that each subsequence consists of consecutive integers and has length at least 3.
 * <p>
 * Sample Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 */
public class SplitArrayIntoConsecutiveSubsequences {

    public boolean isPossible(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return false;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        Map<Integer, Integer> hypotheticalMap = new HashMap<>();

        for (int num : nums)
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);

        for (int i : nums) {
            if (frequencyMap.get(i) == 0)
                continue;
            if (hypotheticalMap.getOrDefault(i, 0) > 0) {
                hypotheticalMap.put(i, hypotheticalMap.get(i) - 1);
                hypotheticalMap.put(i + 1, hypotheticalMap.getOrDefault(i + 1, 0) + 1);
                frequencyMap.put(i, frequencyMap.get(i) - 1);
            } else if (frequencyMap.getOrDefault(i, 0) > 0 && frequencyMap.getOrDefault(i + 1, 0) > 0 &&
                    frequencyMap.getOrDefault(i + 2, 0) > 0) {
                frequencyMap.put(i, frequencyMap.get(i) - 1);
                frequencyMap.put(i + 1, frequencyMap.get(i + 1) - 1);
                frequencyMap.put(i + 2, frequencyMap.get(i + 2) - 1);

                hypotheticalMap.put(i + 3, hypotheticalMap.getOrDefault(i + 3, 0) + 1);
            } else
                return false;
        }

        return true;
    }
}
