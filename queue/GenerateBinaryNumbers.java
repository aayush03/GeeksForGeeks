package queue;

import java.util.LinkedList;
import java.util.Queue;

public class GenerateBinaryNumbers {

    public static void main(String[] args) {

        generateBinaryNumbers(2);
        System.out.println();
        generateBinaryNumbers(5);
    }

    private static void generateBinaryNumbers(int n) {
        Queue<String> queue = new LinkedList();

        queue.add("1");
        int count = 1;
        while (!queue.isEmpty()) {
            String s = queue.remove();
            System.out.print(s + " ");

            if (count++ < n)
                queue.add(s + "0");
            if (count++ < n)
                queue.add(s + "1");
        }
    }
}
