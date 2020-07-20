package breadth.first.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Aayush Srivastava
 */

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * <p>
 * ## Only one letter can be changed at a time.
 * ## Each transformed word must exist in the word list.
 * <p>
 * Note:
 * <p>
 * 1.) Return 0 if there is no such transformation sequence.
 * 2.) All words have the same length.
 * 3.) All words contain only lowercase alphabetic characters.
 * 4.) You may assume no duplicates in the word list.
 * 5.) You may assume beginWord and endWord are non-empty and are not the same.
 * <p>
 * Sample Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * Output: 5
 * <p>
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList);

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int q = 0; q < size; q++) {
                char[] cur = queue.poll().toCharArray();
                for (int i = 0; i < cur.length; i++) {
                    char temp = cur[i];
                    for (char chr = 'a'; chr <= 'z'; chr++) {
                        cur[i] = chr;
                        String dest = new String(cur);
                        if (dictionary.contains(dest)) {
                            if (dest.equals(endWord)) return level + 1;
                            queue.add(dest);
                            dictionary.remove(dest);//removing already visited word from the dictionary
                        }
                    }
                    cur[i] = temp;//recreating the original char array for next iteration
                }
            }
            level++;
        }
        return 0;
    }
}
