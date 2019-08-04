package search;

/**
 * You are given heights of consecutive buildings.
 * You can move from the roof of a building to the
 * roof of next adjacent building. You need to find
 * the maximum number of consecutive steps you can
 * put forward such that you gain an increase in altitude.
 */
public class RoofTop {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 3, 2};
        System.out.println(maxStep(arr, arr.length));
    }

    private static int maxStep(int arr[], int n) {
        int maxSteps = 0;
        int stepsSoFar = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1])
                stepsSoFar++;
            else
                stepsSoFar = 0;

            maxSteps = maxSteps > stepsSoFar ? maxSteps : stepsSoFar;
        }
        return maxSteps;
    }
}
