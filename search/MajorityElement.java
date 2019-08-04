package search;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array A of N elements. Find the majority element in the array.
 * A majority element in an array A of size N is an element that appears more than N/2 times in the array.
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 1, 3, 3, 2};
        System.out.println(majorityElement(arr1, arr1.length));
        int[] arr2 = new int[]{1, 2, 3};
        System.out.println(majorityElement(arr2, arr2.length));
    }

    private static int majorityElement(int[] arr, int size) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        int thresholdValue = size / 2;
        for (int i : arr) {
            if (frequencyMap.containsKey(i)) {
                int value = frequencyMap.get(i);
                value++;
                if (value > thresholdValue)
                    return i;
                frequencyMap.put(i, value);
            } else {
                frequencyMap.put(i, 1);
            }
        }
        return -1;
    }
}
