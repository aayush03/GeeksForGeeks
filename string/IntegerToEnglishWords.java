package string;

/**
 * @author Aayush Srivastava
 */

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 *
 * Example 1:
 *
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 *
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 *
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 *
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class IntegerToEnglishWords {

    String[] words1 = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] words2 = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] words2Teens = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] words4 = new String[]{"", "Thousand", "Million", "Billion"};

    /** Basic Concept:
     1. Divide the number into parts of 3 digits each, and evaluate the string for that part.
     {Thousand, Million, Billion} represent each part (starting from second part from least significant bit), in increasing order of significance.

     2. For example, given number => ZZZ,YYY,XXX where it is a 9 digit number divided into 3 parts.
     The words representing this number would be something like "ZZZ Million YYY Thousand XXX".

     3. Now, all we have to do is evaluate each part (i.e XXX, YYY, ZZZ) as if they're an individual number consisting of upto 3 digits.
     */
    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        int i = 0;
        StringBuffer result = new StringBuffer();
        while (num > 0) {
            int part = num % 1000; // Split the number in parts with 3 digits each
            num /= 1000;

            // Evaluate the amount of current three digit part
            StringBuilder partWord = new StringBuilder(getWordsFor3DigitNumber(part));
            if (partWord.length() > 0) {
                partWord.append(" ");
                partWord.append(words4[i++]);
                result.insert(0, partWord);
                result.insert(0, " ");
            } else i++; // If number is 1000000 (One Million), then we want to skip adding 'Thousand' to the result
        }
        return result.toString().trim();
    }

    private String getWordsFor3DigitNumber(int num) {
        StringBuilder str = new StringBuilder();

        int d1 = num % 10; // One's place digit
        num /= 10;
        int d2 = num % 10; // Ten's place digit
        num /= 10;
        // Parsing the digit in One's place & Ten's Place
        if (d2 == 1)
            str.insert(0, words2Teens[d1]);
        else if (d2 >= 2) {
            str.append(words2[d2]);
            if (words1[d1].length() > 0) {
                str.append(" ");
                str.append(words1[d1]);
            }
        } else str.append(words1[d1]);

        int d3 = num % 10; // Hundredth's place digit
        num /= 10;
        if (d3 >= 1) {
            str.insert(0, " ");
            str.insert(0, "Hundred");
            str.insert(0, " ");
            str.insert(0, words1[d3]);
        }
        return str.toString().trim();
    }
}
