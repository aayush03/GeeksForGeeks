package design;

/**
 * @author Aayush Srivastava
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 500
 * word in addWord consists lower-case English letters.
 * word in search consist of  '.' or lower-case English letters.
 * At most 50000 calls will be made to addWord and search.
 */
public class DesignAddAndSearchWordsDataStructure {

    private Map<Integer,List<String>> map;

    /**
     * Initialize your data structure here.
     */
    public DesignAddAndSearchWordsDataStructure() {
        map = new HashMap<>();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        map.computeIfAbsent(word.length(), k-> new ArrayList<>()).add(word);
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        if (map.containsKey(word.length())) {

            List<String> tempList = map.get(word.length());

            for (String str : tempList) {
                if (compareWords(word, str)) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean compareWords(String word, String dictWord) {
        for (int c = 0; c < word.length(); c++) {
            if (word.charAt(c) == '.') {
                continue;
            }

            if (word.charAt(c) != dictWord.charAt(c)) {
                return false;
            }
        }

        return true;
    }
}
