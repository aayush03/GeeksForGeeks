package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Sample Input: nums = [1,2,3]
 * Output:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class AllUniqueSubsetsOfArrayOfDistinctElements {

    public static void main(String[] args) {
        System.out.println(new AllUniqueSubsetsOfArrayOfDistinctElements().subsets(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }

        dfs(nums, 0, new ArrayList<>(), res);

        return new ArrayList<>(res);
    }

    private void dfs(int[] nums, int offSet, List<Integer> subset, List<List<Integer>> res) {
        res.add(new ArrayList<>(subset));

        for (int i = offSet; i < nums.length; i++) {
            subset.add(nums[i]);
            dfs(nums, i + 1, subset, res);
            subset.remove(subset.size() - 1);
        }
    }
}
