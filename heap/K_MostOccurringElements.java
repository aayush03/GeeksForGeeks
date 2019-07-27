package heap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class K_MostOccurringElements {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 4, 4, 5, 2, 6, 1};
        int k = 2;
        kMostOccurring(arr, k);
    }

    private static void kMostOccurring(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : arr) {
            if (map.containsKey(i)) {
                int count = map.get(i);
                map.put(i, ++count);
            } else {
                map.put(i, 1);
            }
        }

        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        Iterator<Integer> itr = map.keySet().iterator();
        while (itr.hasNext()) {
            int key = itr.next();
            q.add(map.get(key));
        }
        int sum = 0;
        while (k > 0) {
            sum += q.poll();
            k--;
        }
        System.out.println(sum);
    }
}
