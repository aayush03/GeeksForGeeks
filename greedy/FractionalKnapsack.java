package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given weights and values of N items, we need to put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
 * Note: Unlike 0/1 knapsack, you are allowed to bread the item.
 */

/**
 * Sample Input :
 * 2
 * 3 50
 * 60 10 100 20 120 30
 * 2 50
 * 60 10 100 20
 */
public class FractionalKnapsack {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            int[] arr1 = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            int n = arr1[0];
            int w = arr1[1];

            int[] arr2 = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            ItemValue[] arr = new ItemValue[n];

            int p = 0;
            for (int i = 0; i < n; i++) {
                int value = arr2[p++];
                int weight = arr2[p++];
                arr[i] = new ItemValue(i, value, weight);
            }

            printMaxValue(arr, n, w);
        }
    }

    private static void printMaxValue(ItemValue[] arr, int n, int w) {
        Arrays.sort(arr);
        double totalCost = 0;
        int weightToBeFilled = w;
        for (int i = 0; i < n; i++) {
            if (weightToBeFilled >= arr[i].weight) {
                weightToBeFilled -= arr[i].weight;
                totalCost += arr[i].value;
            } else {
                totalCost += (weightToBeFilled * arr[i].valueToWeightRatio);
                break;
            }
        }

        System.out.println(String.format("%.2f", totalCost));
    }
}

class ItemValue implements Comparable<ItemValue> {
    Double valueToWeightRatio;
    int index;
    int value;
    int weight;

    public ItemValue(int index, int value, int weight) {
        this.index = index;
        this.value = value;
        this.weight = weight;
        this.valueToWeightRatio = (double) ((double) value / (double) weight);
    }

    public int compareTo(ItemValue o) {
        if (this.valueToWeightRatio < o.valueToWeightRatio)
            return 1;
        return -1;
    }
}
