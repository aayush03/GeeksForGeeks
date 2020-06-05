package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a matrix of dimension r*c where each cell in the matrix can have values 0, 1 or 2 which has the following meaning:
 * 0 : Empty cell
 * 1 : Cells have fresh oranges
 * 2 : Cells have rotten oranges
 *
 * So, we have to determine what is the minimum time required to rot all oranges. A rotten orange at index [i,j] can rot other fresh
 * orange at indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1] (up, down, left and right) in unit time. If it is impossible to rot every orange then simply return -1.
 */

/**
 * Sample Input
 * 2
 * 3 5
 * 2 1 0 2 1 1 0 1 2 1 1 0 0 2 1
 * 3 5
 * 2 1 0 2 1 0 0 1 2 1 1 0 0 2 1
 */
public class ServersUpdate {

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
        final int totalServers = ROW * COL;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (list.get(i).get(j) == 1) {
                    ((LinkedList<Triplet>) queue).add(new Triplet(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int[] xCordinate = {-1, 1, 0, 0};
        int[] yCordinate = {0, 0, -1, 1};

        //int time = 0;
        int tt = 0;
        int serversFound = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            serversFound += size;
            for (int k = 0; k < size; k++) {
                Triplet curr = queue.poll();
                int x = curr.x;
                int y = curr.y;
                int currTime = curr.time;
                //time = Math.max(currTime, time);

                for (int j = 0; j < 4; j++) {
                    int x1 = x + xCordinate[j];
                    int y1 = y + yCordinate[j];
                    if (isSafe(list, x1, y1, ROW, COL, visited)) {
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

        return totalServers == serversFound ? tt - 1 : -1;
    }

    private static boolean isSafe(List<List<Integer>> list, int x, int y, int ROW, int COL, boolean[][] visited) {
        return (x >= 0 && x < ROW) &&
                (y >= 0 && y < COL) &&
                !visited[x][y] &&
                (list.get(x).get(y) == 0);
    }
}
