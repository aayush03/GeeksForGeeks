package backtracking;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        System.out.println(new LetterCombinationsOfPhoneNumber().letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        char[][] numPad = {{}, {'a', 'b', 'c'}, {'d', 'e', 'f'},
                {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return result;
        digits2Letter(numPad, result, new StringBuilder(), digits, 0);
        return result;
    }

    private void digits2Letter(char[][] numPad, List<String> result, StringBuilder currentBuffer, String digits, int currentDigit) {
        //Check if all of the digits are processed
        if (currentDigit == digits.length()) {
            result.add(currentBuffer.toString());
            return;
        }

        //Get the current digit from string and indexing the numbers from 0
        int currentNum = digits.charAt(currentDigit) - '0' - 1;
        //traverse through the list of letters for that digit
        for (int i = 0; i < numPad[currentNum].length; i++) {
            currentBuffer.append(numPad[currentNum][i]);
            digits2Letter(numPad, result, currentBuffer, digits, currentDigit + 1);
            currentBuffer.deleteCharAt(currentBuffer.length() - 1);
        }
    }
}
