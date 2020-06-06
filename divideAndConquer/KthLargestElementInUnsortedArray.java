package divideAndConquer;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Sample Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 */
public class KthLargestElementInUnsortedArray {

    public static void main(String[] args) {
        System.out.println(new KthLargestElementInUnsortedArray().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(new KthLargestElementInUnsortedArray().findKthLargestUsingHeap(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    private int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return kthSmallest(nums, 0, nums.length - 1, nums.length - k + 1);
    }

    private int kthSmallest(int[] arr, int left, int right, int k) {
        while (left <= right) {

            // Partition arr[left..right] around a pivot
            // and find the position of the pivot
            int pivotIndex = partition(arr, left, right);

            // If pivot itself is the k-th smallest element
            if (pivotIndex == k - 1)
                return arr[pivotIndex];

                // If there are more than k-1 elements on
                // left of pivot, then k-th smallest must be
                // on left side.
            else if (pivotIndex > k - 1)
                right = pivotIndex - 1;

                // Else k-th smallest is on right side.
            else
                left = pivotIndex + 1;
        }
        return -1;
    }

    int partition(int[] arr, int low, int high) {
        int temp;
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return (i + 1);
    }

    public int findKthLargestUsingHeap(int[] nums, int k) {
        Queue<Integer> pq = new PriorityQueue<>();

        for(int i = 0 ; i < k; i++) {
            pq.add(nums[i]);
        }

        for(int i = k; i < nums.length; i++) {
            if (nums[i] > pq.peek()) {
                pq.poll();
                pq.add(nums[i]);
            }
        }

        return pq.peek();
    }

}
