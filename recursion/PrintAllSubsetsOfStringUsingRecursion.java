package recursion;

public class PrintAllSubsetsOfStringUsingRecursion {

    public static void main(String[] args) {
        String s = "ABC";

        printPowerSet(s,-1,"");
        //printSubsets(s,0,0,3);
    }

    static void printPowerSet(String str, int index,
                         String curr)
    {
        int n = str.length();

        // base case
        if (index == n)
        {
            return;
        }

        // First print current subset
        System.out.println(curr);

        // Try appending remaining characters
        // to current subset
        for (int i = index + 1; i < n; i++)
        {
            curr += str.charAt(i);
            printPowerSet(str, i, curr);

            // Once all subsets beginning with
            // initial "curr" are printed, remove
            // last character to consider a different
            // prefix of subsets.
            curr = curr.substring(0, curr.length() - 1);
        }
    }

    static void print(String is, String os, int i){
        if (i==is.length()){
            System.out.println(os);
            return;
        }
        print(is,os,i++);
        print(is,os+is.charAt(i),i++);
    }

    static void printSubsets(String s, int i,int k,int n) {
        String emptyString="";
        while (k<=n&&i<=n) {
            emptyString="";
            emptyString+=s.substring(k,i);
            System.out.println(emptyString);
            i++;

            //

            printSubsets(s,i,k,n);
        }
        k++;
        //i=0;


    }//
}
