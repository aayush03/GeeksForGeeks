package amazon.online_assessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Aayush Srivastava
 */
public class LargestItemAssociation {

    class PairString {
        String first;
        String second;

        public PairString(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     * item3 item4 item5
     *  item1 item2 item3 item7
     *  item1 item2 item3 item7
     */
    public List<String> largestItemAssociation(List<PairString> itemAssociation) {
        Map<String, List<String>> assocmap = new HashMap<>();
        //Map with all items and child association of every item 1->2, 2-> , 3->4, 4->5 ,5->
        for (PairString p : itemAssociation) {
            if (!assocmap.containsKey(p.first)) {
                assocmap.put(p.first, new ArrayList<>());
            }
            assocmap.get(p.first).add(p.second);
            if (!assocmap.containsKey(p.second)) {
                assocmap.put(p.second, new ArrayList<>());
            }
        }
        //System.out.println("assocMap::" + assocmap);
        //DFS for every item: Resultant map 1->{5},{2} 2->{1,2},{4,5} 3->{3,4,5}
        Map<Integer, List<List<String>>> sizemap = new HashMap<>();
        int maxassoc = Integer.MIN_VALUE;
        for (String key : assocmap.keySet()) {
            Queue<String> q = new LinkedList<>();
            TreeSet<String> temp = new TreeSet<>();
            q.offer(key);
            Set<String> visited = new HashSet<>();
            visited.add(key);
            while (!q.isEmpty()) {
                String head = q.poll();
                temp.add(head);
                List<String> tail = assocmap.get(head);
                for (String t : tail) {
                    if (!visited.contains(t)) {
                        q.offer(t);
                        //visited.add(t);
                    }
                }
            }
            int size = temp.size();
            maxassoc = Math.max(maxassoc, size);
            if (!sizemap.containsKey(size)) {
                sizemap.put(size, new ArrayList<>());
            }
            sizemap.get(size).add(new ArrayList<>(temp));
        }

        // Retrieve the maximum size lists and sort them lexiographically
        List<List<String>> maxassoclist = sizemap.get(maxassoc);

        Collections.sort(maxassoclist, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                int result = 0;
                for (int i = 0; i < o1.size() && result == 0; i++) {
                    result = o1.get(i).compareTo(o2.get(i));
                }
                return result;
            }
        });

        return maxassoclist.get(0);
    }

    public static void main(String[] args) {
        LargestItemAssociation s = new LargestItemAssociation();
        /**
         * Example 1
         */
        List<PairString> input = Arrays.asList(
                new PairString[]{
                        s.new PairString("item1", "item2"),
                        s.new PairString("item3", "item4"),
                        s.new PairString("item4", "item5")
                }
        );

        List<PairString> input2 =  Arrays.asList(
                new PairString[] {
                        s.new PairString("item1","item2"),
                        s.new PairString("item2","item3"),
                        s.new PairString("item4","item5"),
                        s.new PairString("item6","item7"),
                        s.new PairString("item5","item6"),
                        s.new PairString("item3","item7")
                }
        );
        List<String> lst = s.largestItemAssociation(input);
        for (String sa : lst) System.out.print(" " + sa);
        System.out.println();
        List<String> lst2 = s.largestItemAssociation(input2);
        for (String sa : lst2) System.out.print(" " + sa);
        System.out.println();

        List<PairString> input3 =  Arrays.asList(
                new PairString[] {
                        s.new PairString("item1","item2"),
                        s.new PairString("item1","item3"),
                        s.new PairString("item2","item7"),
                        s.new PairString("item3","item7"),
                        s.new PairString("item5","item6"),
                        s.new PairString("item3","item7")
                }
        );

        List<String> lst3 = s.largestItemAssociation(input3);
        for (String sa : lst3) System.out.print(" " + sa);
        System.out.println();

        List<PairString> input4 =  Arrays.asList(
                new PairString[] {
                        s.new PairString("item1","item2"),
                        s.new PairString("item2","item1"),
                        s.new PairString("item4","item5")
                }
        );

        List<String> lst4 = s.largestItemAssociation(input4);
        for (String sa : lst4) System.out.print(" " + sa);
    }
}
