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
