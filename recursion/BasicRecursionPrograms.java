package recursion;

import java.util.Arrays;

public class BasicRecursionPrograms {

    /**
     * if a number 'n', then no of digits in it = log(n)
     * Time Complexity = O(logn) <O(no_of_digits)>
     *
     * @param args
     */
    public static void main(String[] args) {
        /*System.out.println(sumOfDigits(5013));
        System.out.println(noOfDigits(5013, 0));
        System.out.println(noOfOnes(5013));
        int[] ar = new int[]{2, 4, 3, 5};
        System.out.println(sumOfElementsOfArray(ar, ar.length, 0));
        System.out.println(numberOfOddNumbersInArray(ar, ar.length));

        System.out.println(areStringsEqual("ABC", "ABC", 3));*/
//        System.out.println("digits in factorial of 42::"+digitsInFactorial(5));
        //System.out.println(termOfGP(84,87,3));
        int[] A = new int[]{1, 2, 19, 28, 5, 4};
        int N = A.length;
        Arrays.sort(A);

        System.out.println("Mod Inverse::"+modInverse(10,17));


    }

    public static int modInverse(int a, int m)
    {
        //Your code here
        int i=1;
        while(i<m) {
            if ((a*i)%m==1)
                break;

            i++;
        }
        return i;
    }

    public static int Median(int A[],int N)
    {
        //Your code here
        Arrays.sort(A);
        if (N%2!=0)
            return A[(N)/2];
        return Mean(new int[]{A[N/2],A[(N/2)+1]},2);
    }

    public static int Mean(int A[],int N)
    {
        //Your code here
        return (Arrays.stream(A).sum())/N;
    }
    public static int termOfGP(int A,int B,int N)
    {
        //Your code here
        double r = (double) B/(double) A;
        return (int)(Math.floor(A*(Math.pow(r,N-1))));
    }

    public static int digitsInFactorial(int N)
    {
        //Your code here
        return (int)(Math.floor(sumOfLogs(N)));

    }

    private static double sumOfLogs(int N) {
        if(N>1)
            return Math.log10(N)+sumOfLogs(--N);

        return N==0 ? 1 : N;
    }

    /**
     * f a number 'n', then no of digits in it = log(n)
     * Time Complexity = O(logn) <O(no_of_digits)>
     *
     * @param n
     * @return
     */
    private static int sumOfDigits(int n) {
        if (n == 0)
            return 0;
        return sumOfDigits(n / 10) + (n % 10);
    }

    private static int noOfDigits(int n, int count) {
        if (n == 0)
            return count;
        return noOfDigits(n / 10, ++count);
    }

    private static int noOfOnes(int n) {
        if (n == 0)
            return 0;
        if (n % 10 == 1)
            return 1 + noOfOnes(n / 10);
        else return noOfOnes(n / 10);
    }

    private static int sumOfElementsOfArray(int[] ar, int n, int i) {
        if (i == n)
            return 0;
        return ar[i] + sumOfElementsOfArray(ar, n, ++i);
    }

    private static int numberOfOddNumbersInArray(int[] ar, int n) {
        if (0 == n)
            return 0;
        if (ar[n - 1] % 2 == 1)
            return 1 + numberOfOddNumbersInArray(ar, n - 1);

        return numberOfOddNumbersInArray(ar, n - 1);
    }

    private static boolean areStringsEqual(String s1, String s2, int n) {
        if (n == 0)
            return true;
        if (s1.charAt(n - 1) != s2.charAt(n - 1))
            return false;
        return areStringsEqual(s1, s2, --n);
    }
}
