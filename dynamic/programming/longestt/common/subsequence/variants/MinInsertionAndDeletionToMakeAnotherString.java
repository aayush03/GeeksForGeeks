package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Given two strings ‘str1’ and ‘str2’ of size m and n respectively.
 * The task is to remove/delete and insert minimum number of characters from/in str1 so as to transform it into str2.
 * It could be possible that the same character needs to be removed/deleted from one point of str1 and inserted to some another point.
 * <p>
 * Sample Input : str1 = "heap", str2 = "pea"
 * Output : Minimum Deletion = 2 and
 * Minimum Insertion = 1
 * p and h deleted from heap
 * Then, p is inserted at the beginning
 * One thing to note, though p was required yet
 * it was removed/deleted first from its position and
 * then it is inserted to some other position.
 * Thus, p contributes one to the deletion_count
 * and one to the insertion_count.
 */
public class MinInsertionAndDeletionToMakeAnotherString {

    public static void main(String[] args) {
        System.out.println("Minimum no of Deletions & Insertions to convert first string to second string : " + new MinInsertionAndDeletionToMakeAnotherString().minOperations("heap", "pea"));
    }

    private int minOperations(String x, String y) {
        int lcs = new LongestCommonSubsequence().lcsTabulation(x, y, x.length(), y.length());

        int noOfDeletions = x.length() - lcs;
        int noOfInsertions = y.length() - lcs;

        return noOfDeletions + noOfInsertions;
    }
}
