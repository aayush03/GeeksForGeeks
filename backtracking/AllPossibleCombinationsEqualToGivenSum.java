package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 */
public class AllPossibleCombinationsEqualToGivenSum {

    public static void main(String[] args) {
        List<List<Integer>> result = new AllPossibleCombinationsEqualToGivenSum().combinationSum(new int[]{2, 3, 5}, 8);
        System.out.println(result);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    public void dfs(int[] candidates, int offSet, int target, ArrayList<Integer> combination, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }
        for (int i = offSet; i < candidates.length; i++) {
            if (target >= candidates[i]) {
                combination.add(candidates[i]);
                dfs(candidates, i, target - candidates[i], combination, result);
                combination.remove(combination.size() - 1);
            }
        }
    }
}
