package heap;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

    private static class HeapNode {
        public int arrayNum;
        public int index;
        public int value;

        public HeapNode(int arrayNum, int index, int value) {
            this.arrayNum = arrayNum;
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int arr[][] = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println(mergeKArrays(arr));
    }

    public static ArrayList<Integer> mergeKArrays(int[][] arrays) {
        //add code here.
        if (arrays == null) return null;

        PriorityQueue<HeapNode> minHeap =
                new PriorityQueue<>(arrays.length,
                        (HeapNode a, HeapNode b) -> a.value - b.value);

        int size = 0;
        for (int i = 0; i < arrays.length; i++) {
            size += arrays[i].length;
        }
        int[] result = new int[size]; // k * n

        //add first elements in the array to this heap
        for (int i = 0; i < arrays.length; i++) {
            minHeap.add(new HeapNode(i, 0, arrays[i][0]));
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            //Take the minimum value and put into result
            HeapNode node = minHeap.poll();

            if (node != null) {
                result[i] = node.value;
                list.add(i, node.value);
                if (node.index + 1 < arrays[node.arrayNum].length) {
                    //Complexity of O(log k)
                    minHeap.add(new HeapNode(node.arrayNum,
                            node.index + 1,
                            arrays[node.arrayNum][node.index + 1]));
                }
            }
        }

        return list;

    }
}
