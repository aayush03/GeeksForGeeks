package arrays.basics;

public class AddOneToArray {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 9, 9, 9};
        addOneToArray(arr, arr.length - 1);

        arr = new int[]{1, 2, 3, 4};
        addOneToArray(arr, arr.length - 1);
    }

    private static void addOneToArray(int[] arr, int n) {
        if (n <= 0)
            return;
        int i = n;

        if (arr[i] < 9) {
            arr[i] += 1;
            printArray(arr);
            return;
        }

        arr[i] = 0;
        i--;
        if (i == 0) {
            arr[0] += 1;
            printArray(arr);
            return;
        }
        addOneToArray(arr, i);


    }

    private static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        if (arr[0] > 9) {
            sb.append(1).append(" ");
            sb.append(arr[0] % 10).append(" ");
        } else {
            sb.append(arr[0]).append(" ");
        }

        for (int i = 1; i < arr.length; i++)
            sb.append(arr[i]).append(" ");

        System.out.println(sb.toString());
    }
}
