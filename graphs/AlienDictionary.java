package graphs;

/**
 * @author Aayush Srivastava
 */

import java.util.Arrays;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * "wrt",
 * "wrf",
 * "er",
 * "ett",
 * "rftt"
 * ]
 * <p>
 * Output: "wertf"
 * Example 2:
 * <p>
 * Input:
 * [
 * "z",
 * "x"
 * ]
 * <p>
 * Output: "zx"
 * Example 3:
 * <p>
 * Input:
 * [
 * "z",
 * "x",
 * "z"
 * ]
 * <p>
 * Output: ""
 * <p>
 * Explanation: The order is invalid, so return "".
 * Note:
 * <p>
 * You may assume all letters are in lowercase.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDictionary {

    public static void main(String[] args) {
        System.out.println(new AlienDictionary().alienOrder(new String[]{"z", "z"}));
        System.out.println(new AlienDictionary().alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        System.out.println(new AlienDictionary().alienOrder(new String[]{"z", "x", "z"}));
        System.out.println(new AlienDictionary().alienOrder(new String[]{"z", "x"}));
        System.out.println(new AlienDictionary().alienOrder(new String[]{"zy", "zx"}));
        System.out.println(new AlienDictionary().alienOrder(new String[]{"ab", "adc"}));
    }

    private final int N = 26;

    public String alienOrder(String[] words) {
        if (words.length == 2) {
            String w1 = words[0], w2 = words[1];
            int minLenOfComparedWords = Math.min(w1.length(), w2.length());
            if (w1.length() > w2.length() && w1.substring(0, minLenOfComparedWords)
                    .equals(w2.substring(0, minLenOfComparedWords)))
                return "";
        }
        boolean[][] adj = new boolean[N][N];
        int[] visited = new int[N];
        buildGraph(adj, visited, words);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
            if (visited[i] == 0)  //not visited
                if (!dfs(adj, visited, sb, i))
                    return "";

        return sb.reverse().toString();
    }

    private boolean dfs(boolean[][] adjacencyList, int[] visited, StringBuilder sb, int sourceVertex) {
        visited[sourceVertex] = 1; //visiting
        for (int i = 0; i < N; i++) {
            if (adjacencyList[sourceVertex][i]) { //connected
                if (visited[i] == 1) // exists a cycle
                    return false;
                if (visited[i] == 0) // unvisited
                    if (!dfs(adjacencyList, visited, sb, i))
                        return false;
            }
        }
        visited[sourceVertex] = 2; //visited
        sb.append((char) (sourceVertex + 'a'));
        return true;
    }

    private void buildGraph(boolean[][] adjacencyList, int[] visited, String[] words) {
        Arrays.fill(visited, -1); // no existence
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray())
                visited[c - 'a'] = 0;

            if (i > 0) {
                String firstWord = words[i - 1];
                String secondWord = words[i];
                int minLenOfComparedWords = Math.min(firstWord.length(), secondWord.length());
                for (int j = 0; j < minLenOfComparedWords; j++) {
                    char c1 = firstWord.charAt(j), c2 = secondWord.charAt(j);
                    if (c1 != c2) {
                        adjacencyList[c1 - 'a'][c2 - 'a'] = true;
                        break;
                    }
                }
            }
        }
    }
}
