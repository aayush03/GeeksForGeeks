package search;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
 * <p>
 * Sample Input: A = [4,7,9,10], K = 1
 * Output: 5
 * Explanation:
 * The first missing number is 5.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1.) 1 <= A.length <= 50000
 * 2.) 1 <= A[i] <= 1e7
 * 3.) 1 <= K <= 1e8
 */
public class Kth_MissingElementInSortedArray {


    /**
     * We start with searching for target, which is "nums[0] + K", in the array.
     * Then we will need to increase it by the number of elements, which have appeared
     * in the array and are also smaller or equal to target, except for the first element.
     * <p>
     * e.g.
     * A = [1,2,4], K = 3
     * <p>
     * We start with trying to find "target = nums[0] + K = 4". And in the array , except for the
     * first element, we have two elements, 2 and 4 in the array, are smaller or equal to target, so the final answer is 6.
     * <p>
     * With that idea in mind, we can quickly come up with a solution using binary search to find the index of the
     * target in the array, or insertion place for the target, if the target is not in the element. Then we can simply return "target + index".
     * <p>
     * BUT, we will get WRONG answer in below case with that idea. We will still get answer as 6, while 6 is also in the array.
     * e.g.
     * A = [1, 2, 4, 6], K =3
     * <p>
     * At this point, we realized that we cannot finish this problem in one shot, and really hope if we can repeat
     * this process, so we will find 6 in the array, and increate the result by 1 (since we find one more new element
     * in the array we should skip). But it's also a bad idea to simply repeat that process, since extra binary searches would be expensive.
     * <p>
     * Let's take a look back to above exmaple and see how we did the search for target:
     * 1st loop: left = 0, right = 3, mid = 1, nums[mid] = 2, target = 4,
     * 2nd loop: left = 1 + 1, right = 3, mid = 2, nums[mid] = 4, target = 4,
     * stop.
     * <p>
     * We can see from above iterations that we should be there, if we can increase the target by the number of
     * elements we have skipped in current iteration. And this should only happens when increasing the left boundary.
     * <p>
     * In order to achieve that, we use "prevMid" to keep track the mid from previous iteration, and use the
     * difference between current mid and prev mid to increase the target. So it will become:
     * <p>
     * zero: left = 0, right =3, target = 4, prevMid = 0,
     * 1st loop: left = 0, right = 3, mid =1, nums[mid] = 2, target += (mid - prevMid) = 5, prevMid = 1;
     * 2nd loop: left = 2, right = 3, mid = 2, nums[mid] =4, target += (mid - prevMid) = 7, prevMid = 3;
     * 3rd loop: left = 3, right = 3, mid = 3, nums[mid] = 6, target +=(mid - prevMid) = 7, prevMid = 3;
     * stop.
     */
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        if (nums[n - 1] == n) // implies fully sorted array with no missing element
            return n + k;
        int target = nums[0] + k;
        int l = 0, r = n - 1, prevMid = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return target + mid - prevMid;
            else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                target += (mid - prevMid);
                prevMid = mid;
                l = mid + 1;
            }
        }
        return target;
    }

    public static void main(String[] args) {
        System.out.println(new Kth_MissingElementInSortedArray().missingElement(new int[]{1, 2, 3}, 1));
    }
}
