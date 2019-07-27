package heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class K_LargestElementsInUnsortedArray {

    public static void main(String[] args) {
        /*BufferedReader read =
                new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String[] s = read.readLine().split(" ");
            //int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            int[] arr = Arrays.stream(read.readLine().split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            K_LargestElements(arr, k);
        }*/

        int[] arr1 = new int[]{12, 5, 787, 1, 23};
        int k1 = 2;
        K_LargestElements(arr1, k1);
        System.out.println();
        int[] arr2 = new int[]{1, 23, 12, 9, 30, 2, 50};
        int k2 = 3;
        K_LargestElements(arr2, k2);
    }

    private static void K_LargestElements(int[] arr, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : arr) {
            q.add(i);
        }

        while (k > 0) {
            System.out.print(q.poll() + " ");
            k--;
        }

    }
}
