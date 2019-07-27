package heap;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class KthLargestElementInStream {

    public static void main(String[] args) {
        //code
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int k = sc.nextInt();
            long n = sc.nextInt();

            Queue<Integer> q = new PriorityQueue<>(k);
            for (long i = 1; i <= n; i++) {
                int x = sc.nextInt();
                if (i < k) {
                    q.add(x);
                    System.out.print(-1 + " ");
                    continue;
                }
                if (i == k) {
                    q.add(x);
                }
                else if (x > q.peek()) {
                    q.poll();
                    q.add(x);
                }

                System.out.print(q.peek() + " ");
            }

            System.out.println();
        }
    }
}
