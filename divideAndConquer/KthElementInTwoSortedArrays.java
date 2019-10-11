package divideAndConquer;

public class KthElementInTwoSortedArrays {

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 3, 6, 7, 9};
        int[] arr2 = new int[]{1, 4, 8, 10};
        int n = arr1.length;
        int m = arr2.length;

        int k = 5;
        System.out.println(kthElement(arr1, arr2, 0, 0, n, m, k - 1));
    }

    private static int kthElement(int[] arr1, int[] arr2, int left1, int left2, int right1, int right2, int k) {
        if (left1 == right1)
            return arr2[k];
        if (left2 == right2)
            return arr1[k];

        int mid1 = (right1 - left1) / 2;
        int mid2 = (right2 - left2) / 2;

        if ((mid1 + mid2) < k) {
            if (arr1[mid1] > arr2[mid2])
                return kthElement(arr1, arr2, left1, left2 + mid2 + 1, right1, right2, k - mid2 - 1);

            return kthElement(arr1, arr2, left1 + mid1 + 1, left2, right1, right2, k - mid1 - 1);
        } else {
            if (arr1[mid1] > arr2[mid2])
                return kthElement(arr1, arr2, left1, left2, mid1, right2, k);

            return kthElement(arr1, arr2, left1, left2, right1, mid2, k);
        }
    }
}
