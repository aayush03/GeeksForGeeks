package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class TopologicalSort {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            String st[] = read.readLine().trim().split("\\s+");
            int edg = Integer.parseInt(st[0]);
            int nov = Integer.parseInt(st[1]);
            for (int i = 0; i < nov + 1; i++)
                list.add(i, new ArrayList<Integer>());

            String s[] = read.readLine().trim().split("\\s+");
            int p = 0;
            for (int i = 1; i <= edg; i++) {
                int u = Integer.parseInt(s[p++]);
                int v = Integer.parseInt(s[p++]);
                list.get(u).add(v);

            }


            int res[] = new int[10001];
            res = new TopologicalSort().topoSort(list, nov);
            boolean valid = true;

            for (int i = 0; i < nov; i++) {
                int n = list.get(res[i]).size();
                for (int j = 0; j < list.get(res[i]).size(); j++) {
                    for (int k = i + 1; k < nov; k++) {
                        if (res[k] == list.get(res[i]).get(j))
                            n--;
                    }
                }
                if (n != 0) {
                    valid = false;
                    break;
                }
            }
            if (valid == true)
                System.out.println("0");
            else
                System.out.println("1");
        }
    }

    static int[] topoSort(ArrayList<ArrayList<Integer>> list, int N) {
        boolean[] visited = new boolean[N];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++)
            if (!visited[i])
                dfs(list, visited, i, stack);

        int[] topologicalSortedOrder = new int[N];
        int i = 0;
        while (!stack.isEmpty()) {
            topologicalSortedOrder[i++] = stack.pop();
        }

        return topologicalSortedOrder;
    }

    private static void dfs(ArrayList<ArrayList<Integer>> list, boolean[] visited, int sourceIndex, Stack<Integer> stack) {
        visited[sourceIndex] = true;


        Iterator itr = list.get(sourceIndex).listIterator();

        while (itr.hasNext()) {
            int currIndex = (int) itr.next();
            if (!visited[currIndex])
                dfs(list, visited, currIndex, stack);
        }

        stack.push(sourceIndex);
    }
}
