package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PrintAdjacencyList {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String s1 = reader.readLine().trim();
        int t = Integer.parseInt(s1);

        while (t-- > 0) {
            String[] st = reader.readLine().split(" ");

            int V = Integer.parseInt(st[0]);
            int E = Integer.parseInt(st[1]);

            List<List<Integer>> list = new ArrayList<>(V);

            for (int i =0; i < V+1; i++)
                list.add(new ArrayList<>(E));

            int k = E;

            while (k-- >0) {
                String[] s = reader.readLine().split(" ");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);

                list.get(u).add(v);
                list.get(v).add(u);
            }
            StringBuilder sb = new StringBuilder();
            for (int i=0;i < V;i++) {
                sb.append(i);
                int size = list.get(i).size();
                sb.append(size!=0 ? "-> " : "\n");
                for (int j =0;j<size;j++) {
                    sb.append(list.get(i).get(j));
                            sb.append(j!= size - 1 ? "-> " : "\n");
                }
            }

            System.out.print(sb.toString());
        }
    }
}
