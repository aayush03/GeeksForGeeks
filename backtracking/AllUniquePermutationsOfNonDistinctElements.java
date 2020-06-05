package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Sample Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class AllUniquePermutationsOfNonDistinctElements {

    public static void main(String[] args) {
        System.out.println(new AllUniquePermutationsOfNonDistinctElements().permute(new int[]{1, 1, 2}));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        Arrays.sort(nums);
        dfs(nums, new ArrayList<>(), res, new boolean[nums.length]);

        return res;
    }

    private void dfs(int[] nums, List<Integer> permutation, List<List<Integer>> res, boolean[] visited) {
        if (permutation.size() == nums.length) {
            res.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            permutation.add(nums[i]);
            dfs(nums, permutation, res, visited);
            permutation.remove(permutation.size() - 1);
            visited[i] = false;

            while(i + 1 < nums.length && nums[i] == nums[i + 1])
                i++;
        }
    }
}
