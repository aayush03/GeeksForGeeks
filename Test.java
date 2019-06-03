import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {

        List<A> al = new ArrayList<>();
        List<B> bl = new ArrayList<>();
        /*String s="this is a test string";

        String[] ar = s.split(" ");

        System.out.println(Arrays.toString(ar));

        int maxLength=0;
        String longestEvenString="";

        for (String s1 : ar) {
            if (s1.length()>maxLength && s1.length()%2==0)
                longestEvenString=s1;
        }

        System.out.println("Longest even string::"+longestEvenString);*/

        /*printInReverse(5);
        System.out.println("");
        print(5);

        maxNoOfToys(new int[]{9,7,2,10,15},20);*/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcases = Integer.parseInt(br.readLine());

        // looping through all testcases
        while (testcases-- > 0) {
            int sizeOfArray = Integer.parseInt(br.readLine());

            int arr[] = new int[sizeOfArray];

            String line = br.readLine();
            String[] elements = line.trim().split("\\s+");

            for (int index = 0; index < sizeOfArray; index++) {
                arr[index] = Integer.parseInt(elements[index]);
            }

            Test obj = new Test();
            obj.maximumAdjacent(sizeOfArray, arr);
        }
        //maximumAdjacent(6, new int[]{1,2,2,3,4,5});
    }


    private static void printInReverse(int n) {
        if (n >= 1) {
            System.out.println(n);
            printInReverse(n - 1);
        }
    }

    private static void print(int n) {
        if (n > 1) {
            print(n - 1);
        }
        System.out.println(n);
    }

    private static void maxNoOfToys(int[] ar, int totalAmountAvailable) {
        int count = 0;
        int sum = 0;
        Arrays.sort(ar);

        for (int i : ar) {
            sum += i;
            if (sum > totalAmountAvailable)
                break;
            count++;
        }

        System.out.println("Max No of Toys that can be bought : " + count);
    }

    static void maximumAdjacent(int sizeOfArray, int arr[]) {

        /*********************************
         * Your code here
         * Function should print max adjacents for all pairs
         * Use string buffer for fast output
         * ***********************************/
        for (int i = 0; i <= sizeOfArray - 2; ++i) {
            System.out.print(Math.max(arr[i], arr[i + 1]) + " ");
        }

    }

    static void findFirstNonRepatingChar(String str) {
        int[] indexArray = new int[256];
        for (int i = 0; i < indexArray.length; i++) {
            indexArray[i] = -1;
        }

        int minIndex = str.length();
        for (int i = 0; i < str.length(); i++) {
            if (indexArray[(int) str.charAt(i)] == -1) {
                indexArray[(int) str.charAt(i)] = i;
            } else if (indexArray[(int) str.charAt(i)] >= 0) {
                indexArray[(int) str.charAt(i)] = -2;
            }
        }

        for (int i = 0; i < indexArray.length; i++) {
            if (indexArray[i] >= 0) {
                minIndex = Math.min(minIndex, indexArray[i]);
            }
        }
        if (minIndex == str.length())
            minIndex = -1;
        System.out.println("first non repeating character" + minIndex);
    }
}

class A {

    void m(String s) {

    }

    void m(Object s){

    }

    void m(int s){

    }

    void m(long s){

    }

}

class B extends A {

}