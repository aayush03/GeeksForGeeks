package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Aayush Srivastava
 */

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 *
 * Note:
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class K_ClosestPointsToOrigin {

    /**
     * This solution is based on quick sort, we can also call it quick select.
     * In the quick sort, we will always choose a pivot to compare with other elements.
     * After one iteration, we will get an array that all elements smaller than the pivot are on the left side
     * of the pivot and all elements greater than the pivot are on the right side of the pivot (assuming we sort the array in ascending order).
     * So, inspired from this, each iteration, we choose a pivot and then find the position p the pivot should be.
     * Then we compare p with the K, if the p is smaller than the K, meaning the all element on the left of the pivot are
     * all proper candidates but it is not adequate, we have to do the same thing on right side, and vice versa.
     * If the p is exactly equal to the K, meaning that we've found the K-th position.
     * Therefore, we just return the first K elements, since they are not greater than the pivot.
     *
     *
     *
     * Theoretically, the average time complexity is O(N) , but just like quick sort, in the worst case, this solution would
     * be degenerated to O(N^2)
     *
     * The advantage of this solution is it is very efficient.
     * The disadvantage of this solution is that the K elements closest are not sorted in ascending order.
     */
    public int[][] kClosestWithoutHeap(int[][] points, int K) {
        int len = points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((left, right) -> getDistance(right) - getDistance(left));

        for (int[] point: points) {
            heap.add(point);
            if (heap.size() > K)
                heap.poll();
        }

        int[][] result = new int[K][2];
        while (K > 0)
            result[--K] = heap.poll();

        return result;
    }

    private int getDistance(int [] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
