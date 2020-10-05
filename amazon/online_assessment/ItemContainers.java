package amazon.online_assessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aayush Srivastava
 */
public class ItemContainers {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("|**|*|*", "*|*|", "*|*|*|", "|**|***|*|****|", "|**|*|***");

    }
    public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
        int n = startIndices.size();
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> sum = new HashMap<>();
        int[] lr = new int[s.length()];
        int curBuffer = 0;
        int curSum = 0;
        int marker = 0;
        boolean open = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '|') {
                if(open){
                    curSum += curBuffer;
                }
                else{
                    open = true;
                }
                marker = i;
                sum.put(i, curSum);
                curBuffer = 0;
            }
            else {
                curBuffer++;
            }
            lr[i] = marker;
        }

        open = false;
        marker = s.length() - 1;
        int[] rl = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '|') {
                marker = i;
            }
            rl[i] = marker;
        }

        for (int i = 0; i < n; i++) {
            int start = startIndices.get(i) - 1;
            int end = endIndices.get(i) - 1;
            start = rl[start];
            end = lr[end];

            if(start >= end) {
                result.add(0);
            }
            else {
                result.add(sum.get(end) - sum.get(start));
            }
        }

        return result;
    }
}
