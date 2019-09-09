package graphs;

import java.util.ArrayList;
import java.util.Stack;

public class MotherVortex {

    public static void main(String[] args) {

    }

    static int findMother(ArrayList<ArrayList<Integer>> list, int n) {
        // add your code here
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            Stack<Integer> stack = new Stack<>();
            stack.push(i);

            int count = 0;

            visited[i] = true;

            while (!stack.isEmpty()) {
                int currVertex = stack.pop();
                count++;

                for (int j = 0; j < list.get(currVertex).size(); j++) {
                    Integer t = list.get(currVertex).get(j);
                    if (!visited[t]) {
                        visited[t] = true;
                        stack.push(t);
                    }
                }
            }
            if (count == n)
                return i;
        }

        return -1;
    }
}
