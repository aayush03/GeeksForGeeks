package union.find;

/**
 * @author Aayush Srivastava
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
 * and the rest of the elements are emails representing emails of the account.
 * <p>
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email
 * that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people
 * as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 * <p>
 * After merging the accounts, return the accounts in the following format: the first element of each account
 * is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 * <p>
 * Sample Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * <p>
 * <p>
 * Note:
 * <p>
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 */
public class AccountsMerge {

    public static void main(String[] args) {

    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emails = new HashMap<>();
        Map<Integer, String> names = new HashMap<>();
        List<String> reverseLookup = new ArrayList<>();
        for (List<String> account : accounts) {
            int size = account.size();
            String name = account.get(0);
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emails.containsKey(email)) {
                    int index = reverseLookup.size();
                    emails.put(email, index);
                    names.put(index, name);
                    reverseLookup.add(email);
                }
            }
        }

        int n = emails.size();
        UnionFind unionFind = new UnionFind(n);

        for (List<String> account : accounts) {
            int size = account.size();
            Integer group = null;
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                int j = emails.get(email);

                if (group == null) {
                    group = j;
                }

                unionFind.union(group, j);
            }
        }

        Map<Integer, List<Integer>> groupIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String email = reverseLookup.get(i);

            int j = emails.get(email);
            int group = unionFind.find(j);

            groupIndexMap
                    .computeIfAbsent(group, x -> new ArrayList<>())
                    .add(j);
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> e : groupIndexMap.entrySet()) {
            int group = e.getKey();
            List<Integer> indices = e.getValue();
            String name = names.get(group);

            List<String> list = new ArrayList<>();
            for (int i : indices) {
                String email = reverseLookup.get(i);
                list.add(email);
            }
            Collections.sort(list);

            list.add(0, name);
            result.add(list);
        }
        return result;
    }

    private class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }

            return i;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return;
            // union by size
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
        }
    }
}