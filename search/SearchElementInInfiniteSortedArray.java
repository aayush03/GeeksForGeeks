package search;

public class SearchElementInInfiniteSortedArray {

    /**
     * Time Complexity = O(log(i)), where i is Index of element to be searched
     * @param args
     */
    public static void main(String[] args) {

        int[] arr = new int[]{1,2,4,20,44,50,60,70,80,90,100,120,130,150,170};

        int result=search(arr,  44);

        if (result>-1) {
            System.out.println("Element found at index::"+result);
        } else {
            System.out.println("Element not found");
        }
    }

    static int search(int[] ar,int val) {

        if (ar[0]==val)
            return 0;
        int i,startIndex=1,lastIndex=1;
        for (i = 1;ar[i]<=val;i*=2) {
            lastIndex=i;
        }
        startIndex=lastIndex/2;

        return new BinarySearch().binarySearch(ar,startIndex,lastIndex,val);
    }
}
