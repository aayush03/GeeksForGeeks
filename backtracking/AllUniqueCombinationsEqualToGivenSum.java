package backtracking;

import java.util.*;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 */
public class AllUniqueCombinationsEqualToGivenSum {

    public static void main(String[] args) {
        List<List<Integer>> res = new AllUniqueCombinationsEqualToGivenSum().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println(res);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        if (candidates == null || candidates.length == 0)
            return res;

        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<>(), res);

        return res;
    }

    private void dfs(int[] candidates, int offSet, int target, List<Integer> combination, List<List<Integer>> res) {
        if (target < 0)
            return;
        if (target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }


        for (int i = offSet; i < candidates.length; i++) {
            // Do not continue iteration of the remaining elements if the target - current element is less than 0. This works because the array is sorted and it is guaranteed to not find an element which will satisfy this condition for the remaining elements
            if (target - candidates[i] < 0)
                return;

            combination.add(candidates[i]); //choose
            dfs(candidates, i + 1, target - candidates[i], combination, res);
            combination.remove(combination.size() - 1); //unchoose

            while ((i + 1 < candidates.length && candidates[i] == candidates[i + 1])) //this is done because if you have already chosen a number once and generated its combinations, then you don"t need to choose it again as only unique combinations are needed and since the array is sorted, this will work
                i++;
        }
    }
}
