package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RottenOranges {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            String[] st = reader.readLine().split("\\s");

            int r = Integer.parseInt(st[0]);
            int c = Integer.parseInt(st[1]);

            List<List<Integer>> list = new ArrayList<>();

            for (int i = 0; i < r ; i++)
                list.add(i, new ArrayList<>(c));

            String[] s = reader.readLine().split("\\s");

            int p = 0;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    list.get(i).add(j, Integer.parseInt(s[p++]));
                }
            }

            System.out.println(getTimeForAllOrangesToBecomeRotten(list, r, c));
        }
    }


    private static int getTimeForAllOrangesToBecomeRotten(List<List<Integer>> list, int ROW, int COL) {

        class Triplet {
            int x;
            int y;
            int time;

            public Triplet(int x, int y, int time) {
                this.x = x;
                this.y = y;
                this.time = time;
            }
        }

        boolean[][] visited = new boolean[ROW][COL];

        Queue<Triplet> queue = new LinkedList<>();
        int totalOranges = 0;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (list.get(i).get(j) == 2) {
                    ((LinkedList<Triplet>) queue).add(new Triplet(i, j, 0));
                    visited[i][j] = true;
                }
                if (list.get(i).get(j) == 1 || list.get(i).get(j) == 2)
                    totalOranges++;
            }
        }

        int[] xCordinate = {-1, 1, 0, 0};
        int[] yCordinate = {0, 0, -1, 1};

        //int time = 0;
        int tt = 0;
        int orangesFound = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            orangesFound += size;
            for (int k = 0; k < size; k++) {
                Triplet curr = queue.poll();
                int x = curr.x;
                int y = curr.y;
                int currTime = curr.time;
                //time = Math.max(currTime, time);

                for (int j = 0; j < 4; j++) {
                    int x1 = x + xCordinate[j];
                    int y1 = y + yCordinate[j];
                    if (isSafe(list, x1, y1, ROW, COL)) {
                        ((LinkedList<Triplet>) queue).add(new Triplet(x1, y1, currTime + 1));
                        list.get(x1).set(y1, 2);
                    }
                }

            }
            ++tt;
        }

        /*for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (list.get(i).get(j) == 1) {
                    return -1;
                }
            }
        }*/

        return totalOranges == orangesFound ? tt - 1 : -1;
    }

    private static boolean isSafe(List<List<Integer>> list, int x, int y, int ROW, int COL) {
        return (x >= 0 && x < ROW) &&
                (y >= 0 && y < COL) &&
                (list.get(x).get(y) == 1);
    }
}
