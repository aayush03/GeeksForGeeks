package graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnectedComponents_KosarajuAlgorithm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0)
        {
            // arraylist of arraylist to represent graph
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();

            int nov = sc.nextInt();
            int edg = sc.nextInt();

            for(int i =0; i < nov+1; i++)
                list.add(i, new ArrayList<Integer>());

            for(int i = 1; i <= edg; i++)
            {    int u = sc.nextInt();
                int v = sc.nextInt();

                // adding directed edge between
                // vertex 'u' and 'v'
                list.get(u).add(v);
            }
            System.out.println(kosaraju(list, nov+1));
        }
    }

    public static int kosaraju(ArrayList<ArrayList<Integer>> list, int N) {
        // your code here
        boolean[] visited = new boolean[N];
        int count = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                fillOrder(list, visited, i,stack);
                stack.push(i);
            }
        }

        ArrayList<ArrayList<Integer>> reversedList = reverseGraph(list,N);
        visited = new boolean[N];
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!visited[curr]) {
                dfs(reversedList, visited, curr);
                count++;
            }
        }

        return count;
    }

    private static void fillOrder(ArrayList<ArrayList<Integer>> list, boolean[] visited, int src, Stack<Integer> stack) {
        visited[src] = true;

        Iterator itr = list.get(src).listIterator();
        while (itr.hasNext()) {
            int curr = (int) itr.next();
            if (!visited[curr]) {
                fillOrder(list, visited, curr,stack);
            }
        }
        stack.push(src);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> list, boolean[] visited, int src) {
        visited[src] = true;

        Iterator itr = list.get(src).listIterator();
        while (itr.hasNext()) {
            int curr = (int) itr.next();
            if (!visited[curr]) {
                dfs(list, visited, curr);
            }
        }
    }

    private static ArrayList<ArrayList<Integer>> reverseGraph(ArrayList<ArrayList<Integer>> list,int N) {
        ArrayList<ArrayList<Integer>> reversedList = new ArrayList<>();

        int size = N;

        for (int i = 0; i < size; i++)
            reversedList.add(i, new ArrayList<>());

        for (int i = 0; i < size; i++) {
            Iterator itr = list.get(i).listIterator();
            while (itr.hasNext()) {
                reversedList.get((int) itr.next()).add(i);
            }
        }

        return reversedList;
    }
}
