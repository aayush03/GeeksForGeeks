package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllUniqueSubsetsOfArrayOfNonDistinctElements {

    public static void main(String[] args) {
        System.out.println(new AllUniqueSubsetsOfArrayOfNonDistinctElements().subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println(new AllUniqueSubsetsOfArrayOfNonDistinctElements().subsetsWithDup(new int[]{4, 1, 4, 4, 4}));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), res);

        return res;
    }

    private void dfs(int[] nums, int offSet, List<Integer> subset, List<List<Integer>> res) {
        res.add(new ArrayList<>(subset));

        for (int i = offSet; i < nums.length; i++) {
            subset.add(nums[i]);
            dfs(nums, i + 1, subset, res);
            subset.remove(subset.size() - 1);
            while (i + 1 < nums.length && nums[i] == nums[i + 1])
                i++;
        }
    }
}
