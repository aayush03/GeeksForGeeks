package hash.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Aayush Srivastava
 */

/**
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
 *
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)
 *
 * Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"]
 * Explanation:
 * The tuples in this example are:
 * ["joe", 1, "home"]
 * ["joe", 2, "about"]
 * ["joe", 3, "career"]
 * ["james", 4, "home"]
 * ["james", 5, "cart"]
 * ["james", 6, "maps"]
 * ["james", 7, "home"]
 * ["mary", 8, "home"]
 * ["mary", 9, "about"]
 * ["mary", 10, "career"]
 * The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
 * The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
 * The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
 * The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
 * The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 *
 *
 * Note:
 *
 * 3 <= N = username.length = timestamp.length = website.length <= 50
 * 1 <= username[i].length <= 10
 * 0 <= timestamp[i] <= 10^9
 * 1 <= website[i].length <= 10
 * Both username[i] and website[i] contain only lowercase characters.
 * It is guaranteed that there is at least one user who visited at least 3 websites.
 * No user visits two websites at the same time.
 */
public class AnalyzeUserWebsiteVisitPattern {

    class Pair {
        String web;
        int time;

        Pair(String web, int time) {
            this.web = web;
            this.time = time;
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<String> result = new ArrayList<>();
        int n = username.length;
        if (n == 0)
            return result;
        Map<String, List<Pair>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(username[i], k -> new ArrayList<>()).add(new Pair(website[i], timestamp[i]));
        }

        Map<String, Integer> count = new HashMap<>();
        for (List<Pair> list : map.values()) {
            generate3SequncesCountMap(list, count);
        }
        String websites = null;
        int max = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() > max) {
                websites = entry.getKey();
                max = entry.getValue();
            } else if (entry.getValue() == max) {
                if (websites.compareTo(entry.getKey()) > 0)
                    websites = entry.getKey();
            }
        }

        for (String web : websites.split(" ")) {
            result.add(web);
        }

        return result;
    }

    private void generate3SequncesCountMap(List<Pair> list, Map<String, Integer> count) {
        Collections.sort(list, (a, b) -> a.time - b.time);

        int n = list.size();
        //This set is to avoid counting visits of the same 3-seq by one user
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(list.get(i).web)
                            .append(" ")
                            .append(list.get(j).web)
                            .append(" ")
                            .append(list.get(k).web);
                    String websites = sb.toString();
                    if (!set.contains(websites)) {
                        count.put(websites, count.getOrDefault(websites, 0) + 1);
                        set.add(websites);
                    }
                }
            }
        }
    }
}
