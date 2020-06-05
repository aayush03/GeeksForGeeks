package backtracking;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * <p>
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * <p>
 * Note:
 * <p>
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 */
public class KthPermutationSequence {

    boolean flag = false;
    String result = "";

    public static void main(String[] args) {
        System.out.println(new KthPermutationSequence().getPermutation1(3, 3));
    }

    /*public String getPermutation(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, new boolean[n + 1], new ArrayList<>(), res, k);
        return result;
    }


    private void dfs(int n, boolean[] visited, List<Integer> permutation, List<List<Integer>> res, int k) {
        if (!flag) {
            if (permutation.size() == n) {
                res.add(new ArrayList<>(permutation));
                if (res.size() == k) {
                    flag = true;
                    StringBuilder builder = new StringBuilder();
                    permutation.forEach(s -> builder.append(s));
                    result = builder.toString();
                }
                return;
            }

            for (int i = 1; i <= n; i++) {
                if (visited[i])
                    continue;
                visited[i] = true;
                permutation.add(i);
                dfs(n, visited, permutation, res, k);
                permutation.remove(permutation.size() - 1);
                visited[i] = false;
            }
        }
    }*/

    /**
     * This uses the order within the groupings of permutations to directly get the answer. The permutations here are ordered in "lexicographic order".
     * So for n=4, the first permutation is "1234" and the last is "4321". There are a total of n! (n factorial) permutations. They look like this:
     *
     * "1234"
     * "1243"
     * ...
     * "1432" (last permutation starting with "1")
     * "2134" (first permutation starting with "2")
     * ...
     * "4321" (last permutation)
     * So one-fourth of all the permutations start with "1", which all come at the start. After this comes another one-fourth of all permutations starting with "2".
     * And so on. This means that we can look at the count (k/4) to determine what the first digit is.
     *
     * Furthermore, within each grouping (like "1234" to "1432"), the same property is true of the second digit. Among permutations starting with "1",
     * the first one-third of all permutations start with "12", then one-third starting with "13", and the last one-third start with "14".
     *
     * So for n = 4, we can get the answer by the following:
     *
     * Break all the permutations (4!) into fourths, and based on which fourth, set the first digit.
     * Within that fourth (using remainder), break up into thirds to determine the second digit.
     * Within that third (using remainder), break up into halves to determine the third digit.
     * The last digit is the one that remains.
     *
     * To use integer division to correctly get the fraction and remainder, I define ipermutation as (k-1). For n =4, ipermutation goes from 0 to 23 (4! - 1).
     * Then ipermutation / 6 evenly breaks from 0 to 3 (quarters). This is called "ichoice" to determine the digit. Take the remainder and divide by 2 to get a number
     * from 0 to 2 (thirds). Take the remainder and divide by 1 to get a number from 0 to 1 (halfs). The last number is always 0.
     */
    public String getPermutation1(int n, int k) {
        // The permutation number will be based on sequence
        // within n factorial. fact starts at n factorial.
        int fact = 1;
        for (int inum = 1; inum <= n; inum++) fact *= inum;
        // Count permutations from 0 instead of from 1
        int ipermutation = k - 1;
        // Keep track of the numbers chosen so far
        boolean[] chosen = new boolean[n];
        // Now assemble the string from the start
        StringBuilder sb = new StringBuilder();
        for (int inum = n; inum >= 1; inum--) {
            fact /= inum;
            int ichoice = ipermutation / fact;
            appendKthPermutation(sb, chosen, ichoice);
            ipermutation = ipermutation % fact;
        }
        return sb.toString();
    }

    private void appendKthPermutation(StringBuilder sb, boolean[] chosen, int ichoice) {
        // Choose the ichoice-th false spot within chosen.
        // Then append that (number + 1) to the string,
        // and fill in the spot as true.
        int count = 0;
        for (int i = 0; i < chosen.length; i++) {
            if (!chosen[i]) {
                if (count == ichoice) {
                    sb.append(i + 1);
                    chosen[i] = true;
                    return;
                } else {
                    count++;
                }
            }
        }
    }
}
