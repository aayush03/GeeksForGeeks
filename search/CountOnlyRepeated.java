package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given an array arr[] of N positive integers, where elements are consecutive (sorted).
 * Also, there is a single element which is repeating X (any variable) number of times.
 * Now, the task is to find the element which is repeated and number of times it is repeated.
 */
public class CountOnlyRepeated {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            Long n = Long.valueOf(reader.readLine());

            Long[] arr = Arrays.stream(reader.readLine().split("\\s"))
                    .map(Long::valueOf)
                    .toArray(Long[]::new);
            printRepeated(arr, n);
        }
    }

    private static void printRepeated(Long[] arr, long n) {
        String result = "";
        long count = 0;
        long repeatedNumer = arr[0];
        for (long i=0;i<n;i++) {
            if (i <= n-2 && arr[(int)i].equals(arr[(int)i+1])) {
                repeatedNumer = arr[(int)i];
                count++;
            } else if (count > 0){
                break;
            }
        }

        result = repeatedNumer + " " + (count+1);

        System.out.println(result);
    }
}
