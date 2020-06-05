package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Sample Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class AllPossiblePermutationsOfDistinctElements {

    public static void main(String[] args) {
        System.out.println(new AllPossiblePermutationsOfDistinctElements().permute(new int[]{1, 2, 3}));
        System.out.println(new AllPossiblePermutationsOfDistinctElements().permute(new int[]{1, 1, 2}));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }

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

        }
    }
}
