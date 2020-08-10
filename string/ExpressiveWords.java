package string;

/**
 * @author Aayush Srivastava
 */

/**
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".
 * In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 * <p>
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of
 * the following extension operation: choose a group consisting of characters c, and add some number of characters c to
 * the group so that the size of the group is 3 or more.
 * <p>
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo"
 * since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".
 * If S = "helllllooo", then the query word "hello" would be stretchy because of
 * these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
 * <p>
 * Given a list of query words, return the number of words that are stretchy.
 * <p>
 * <p>
 * <p>
 * Sample Input:
 * S = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * Output: 1
 * Explanation:
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1.) 0 <= len(S) <= 100.
 * 2.) 0 <= len(words) <= 100.
 * 3.) 0 <= len(words[i]) <= 100.
 * 4.) S and all words in words consist only of lowercase letters
 */
public class ExpressiveWords {

    public static void main(String[] args) {
        System.out.println("No of stretchy words : " + new ExpressiveWords().expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}));
        System.out.println("No of stretchy words : " + new ExpressiveWords().expressiveWords("zzzzzyyyyy", new String[]{"zzyy", "zy", "zyy"}));
    }

    public int expressiveWords(String S, String[] words) {
        int count = 0;

        for (String word : words) {
            if (isStretchy(S, word))
                count++;
        }

        return count;
    }

    private boolean isStretchy(String S, String word) {
        int m = S.length();
        int n = word.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (S.charAt(i) == word.charAt(j)) {
                int len1 = getNumberOfRepeatedCharacters(S, i);
                int len2 = getNumberOfRepeatedCharacters(word, j);
                if (len1 == len2) {
                    i += len1;
                    j += len2;
                } else if (len1 >= 3 && len1 > len2) {
                    i += len1;
                    j += len2;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return i == m && j == n;
    }

    private int getNumberOfRepeatedCharacters(String s, int i) {
        int count = 1;
        char c = s.charAt(i);
        i++;
        while (i < s.length()) {
            if (s.charAt(i) == c) {
                count++;
            } else {
                break;
            }
            i++;
        }
        return count;
    }
}