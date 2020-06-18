package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */
public class SequencePatternMatching {

    public static void main(String[] args) {
        System.out.println("Is Subsequence present in string : " + new SequencePatternMatching().isSequencePatternMatching("AXY", "ADXCPY"));
        System.out.println("Is Subsequence present in string : " + new SequencePatternMatching().isSequencePatternMatchingOptimized("AXY", "ADXCPY"));
    }

    private boolean isSequencePatternMatching(String x, String y) {
        int lcs = new LongestCommonSubsequence().lcsTabulation(x, y, x.length(), y.length());

        return x.length() == lcs;
    }

    private boolean isSequencePatternMatchingOptimized(String x, String y) {
        int start = 0;

        for (int i = 0; i < y.length(); i++)
            if (y.charAt(i) == x.charAt(start))
                start++;

        return start == x.length();
    }
}
