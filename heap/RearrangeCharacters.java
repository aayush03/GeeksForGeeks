package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class RearrangeCharacters {

    private static final int MAX_CHAR = 26;

    public static void main(String[] args) {
        String str = "bbbaa";
        reArrangeCharacters(str);
    }

    private static void reArrangeCharacters(String str) {
        int n = str.length();

        int[] count = new int[MAX_CHAR];

        for (int i = 0; i < n; i++)
            count[str.charAt(i) - 'a']++;

        Queue<CharacterFrequencyPair> pq =
                new PriorityQueue<>(new CharacterFrequencyPairComparator());
        for (char c = 'a'; c <= 'z'; c++) {
            int val = c - 'a';
            if (count[val] > 0)
                pq.add(new CharacterFrequencyPair(c, count[val]));
        }

        str = "";

        CharacterFrequencyPair prev = new CharacterFrequencyPair('#', -1);

        while (pq.size() != 0) {

            CharacterFrequencyPair k = pq.peek();
            pq.poll();
            str = str + k.c;

            if (prev.frequency > 0)
                pq.add(prev);

            (k.frequency)--;
            prev = k;
        }

        if (str.length() != n)
            System.out.println(0);
        else
            System.out.println(1);

    }
}

class CharacterFrequencyPair {

    char c;
    int frequency;

    public CharacterFrequencyPair(char c, int frequency) {
        this.c = c;
        this.frequency = frequency;
    }
}

class CharacterFrequencyPairComparator implements Comparator<CharacterFrequencyPair> {
    @Override
    public int compare(CharacterFrequencyPair k1, CharacterFrequencyPair k2) {
        if (k1.frequency < k2.frequency)
            return 1;
        else if (k1.frequency > k2.frequency)
            return -1;
        return 0;
    }
}