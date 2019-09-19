package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a encoded string, your task is to print the decoded String.
 */

/**
 * Sample Input :
 * 2
 * abc
 * geeksforgeeks
 */
public class HuffmanDecoding {

    static PriorityQueue<HuffmanNode> heapq;
    static HashMap<Character, Integer> freqMap;
    static HashMap<Character, String> codes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String str = br.readLine().trim();
            heapq = new PriorityQueue<>(HuffmanDecoding::compare);
            codes = new HashMap<>();
            freqMap = new HashMap<>();
            String encodedString = "";
            calcFreq(str);
            encodeString();
            for (int i = 0; i < str.length(); i++) {
                encodedString += codes.get(str.charAt(i));
            }
            System.out.println(decodeString(heapq.peek(), encodedString));
        }
    }

    static String decodeString(HuffmanNode root, String encodedStr) {
        // Code here
        HuffmanNode curr = root;
        int length = encodedStr.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (curr == null)
                continue;
            if (curr.left == null && curr.right == null) {
                sb.append(curr.data);
                curr = root;
            }
            if (encodedStr.charAt(i) == '0') {
                if (curr.left != null)
                    curr = curr.left;
            } else {
                if (curr.right != null)
                    curr = curr.right;
            }
        }
        if (length > 1)
            sb.append(curr.data);
        return sb.toString();
    }

    private static void storeCodes(HuffmanNode root, String str) {
        if (root == null) return;
        if (root.data != '$') codes.put(root.data, str);
        storeCodes(root.left, str + "0");
        storeCodes(root.right, str + "1");
    }

    private static void encodeString() {
        HuffmanNode left, right, top;
        for (Map.Entry<Character, Integer> val : freqMap.entrySet()) {
            heapq.add(new HuffmanNode(val.getKey(), val.getValue()));
        }
        while (heapq.size() != 1) {
            left = heapq.poll();
            right = heapq.poll();
            top = new HuffmanNode('$', left.freq + right.freq);
            top.left = left;
            top.right = right;
            heapq.add(top);
        }
        storeCodes(heapq.peek(), "");
    }

    private static void calcFreq(String str) {
        for (int i = 0; i < str.length(); i++) {
            Integer j = freqMap.get(str.charAt(i));
            freqMap.put(str.charAt(i), (j == null) ? 1 : j + 1);
        }
    }

    private static int compare(HuffmanNode h1, HuffmanNode h2) {
        if (h1.freq < h2.freq) {
            return 1;
        } else if (h1.freq > h2.freq) {
            return -1;
        }
        return 0;
    }
}

class HuffmanNode {
    char data;
    int freq;
    HuffmanNode left, right;

    HuffmanNode(char data, int freq) {
        left = right = null;
        this.data = data;
        this.freq = freq;
    }
}