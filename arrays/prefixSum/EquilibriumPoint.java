package arrays.prefixSum;

public class EquilibriumPoint {

    public static void main(String[] args) {
        int[] arr = new int[]{2,8,7,4,12,5};
    }

    private static int equilibriumPoint(int[] arr) {
        int[] prefixArray = prefixSumArray(arr);
        int[] suffixArray = suffixSumArray(arr);

        return -1;
    }

    private static int[] prefixSumArray(int[] arr) {
        int[] prefixArray = new int[arr.length];

        prefixArray[0] = arr[0];
        for (int i=1;i<arr.length;i++){
            prefixArray[i] = prefixArray[i-1]+arr[i];
        }

        return prefixArray;
    }

    private static int[] suffixSumArray(int[] arr) {
        int[] suffixArray = new int[arr.length];

        suffixArray[0] = arr[arr.length-1];
        for (int i=arr.length-1;i>=0;i--){
            suffixArray[i] = suffixArray[i-1]+arr[i];
        }

        return suffixArray;
    }
    public static int getFirstSetBitPos(int n){

        // Your code here
        int k=0;
        while(n>0){
            if (((n>>1)&1)!=0){
                k++;
                break;
            }
            n=n/2;
        }
        return k;

    }
}
