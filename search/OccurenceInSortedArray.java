package search;

public class OccurenceInSortedArray {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 6, 6, 6, 8, 10, 15, 20, 44, 67};

        OccurenceInSortedArray search = new OccurenceInSortedArray();
        int firstOccurence = search.getFirstOccurence(arr, 0, arr.length - 1, 6);
        int lastOccurence = 0;

        if (firstOccurence > -1) {
            System.out.println("First Element found at index::" + firstOccurence);
            lastOccurence = search.getLastOccurence(arr, 0, arr.length, 6);
            System.out.println("Last Element found at index::" + lastOccurence);
        } else {
            System.out.println("Element not found");
        }

        int result = lastOccurence - firstOccurence + 1;
        if (firstOccurence > -1) {
            System.out.println("Count of element::" + result);
        }
    }

    public int getFirstOccurence(int[] arr, int left, int right, int x) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == x) {
                if (mid == 0 || arr[mid - 1] != x)
                    return mid;
            }

            if (x <= arr[mid])
                return getFirstOccurence(arr, left, mid - 1, x);

            return getFirstOccurence(arr, mid + 1, right, x);
        }
        return -1;
    }

    int getLastOccurence(int[] arr, int left, int right, int x) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == x) {
                if (mid == 0 || arr[mid + 1] != x)
                    return mid;
            }

            if (x < arr[mid])
                return getLastOccurence(arr, left, mid - 1, x);

            return getLastOccurence(arr, mid + 1, right, x);
        }
        return -1;
    }
}
