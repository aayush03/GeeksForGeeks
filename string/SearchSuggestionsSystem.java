package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Aayush Srivastava
 */

/**
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 * <p>
 * Return list of lists of the suggested products after each character of searchWord is typed.
 * <p>
 * <p>
 * Sample Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 */
public class SearchSuggestionsSystem {

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();

        Arrays.sort(products);
        int n = products.length;
        String word = "";

        for (char c : searchWord.toCharArray()) {
            word += c;
            int k = 3;
            List<String> list = new ArrayList<>();
            int i = 0;
            while (k > 0 && i < n) {
                if (products[i].startsWith(word)) {
                    list.add(products[i]);
                    k--;
                }
                i++;
            }

            res.add(list);
        }

        return res;
    }
}
