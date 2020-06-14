package dynamic.programming.partition.subset;

/**
 * @author Aayush Srivastava
 */
public class CountNumberOfSubsetsWithGivenDifference {

    public static void main(String[] args) {
        System.out.println("No of subsets with given difference : " + new CountNumberOfSubsetsWithGivenDifference().countSubsetsWithGivenDifference(new int[]{1, 2, 1, 3}, 1));
    }

    public int countSubsetsWithGivenDifference(int[] arr, int diff) {
        int totalSum = 0;
        for (int i = 0; i < arr.length; i++)
            totalSum += arr[i];

        //Edge case
        if ((totalSum % 2 != 0 && diff % 2 == 0) || (totalSum % 2 == 0 && diff % 2 != 0))
            return 0;

        int targetSum = (totalSum - diff) / 2; //(Will also work with (totalSum + diff) / 2 but will result in a higher targetSum and hence reduce the performance

        return new CountOfSubsetsWithGivenSum().countSubsetsWithGivenSum(arr, targetSum);
    }
}
