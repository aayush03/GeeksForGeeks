package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * Given a binary matrix of size N x M.
 * The task is to find the distance of nearest 1 in the matrix for each cell.
 * The distance is calculated as |i1 – i2| + |j1 – j2|, where i1, j1 are the
 * row number and column number of the current cell and i2, j2 are the row number
 * and column number of the nearest cell having value 1.
 *
 *
 * Sample Input :
 * 1
 * 4 7
 * 1 1 1 1 0 0 1 1 0 1 0 1 1 0 0 0 0 0 1 0 1 1 0 0 0 1 1 1
 */
public class DistanceOfNearestCellHaving_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            String[] st = read.readLine().split(" ");
            int N = Integer.parseInt(st[0]);
            int M = Integer.parseInt(st[1]);

            List<List<Integer>> list = new ArrayList<>();

            for (int i = 0; i < N + 1; i++)
                list.add(i, new ArrayList<>(M));

            String[] s = read.readLine().split(" ");

            int p = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int u = Integer.parseInt(s[p++]);
                    list.get(i).add(j, u);
                }
            }

            printNearestDistanceOfOne(list, N, M);
        }
    }

    private static void printNearestDistanceOfOne(List<List<Integer>> list, int N, int M) {
        boolean[][] visited = new boolean[N][M];

        class Triplet {
            int x;
            int y;
            int lvl;

            Triplet(int ix, int iy, int il) {
                this.x = ix;
                this.y = iy;
                this.lvl = il;
            }
        }

        Queue<Triplet> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (list.get(i).get(j) == 1) {
                    ((LinkedList<Triplet>) queue).add(new Triplet(i, j, 0));
                    list.get(i).set(j, -1);
                }
            }
        }

        int[] xCordinate = {-1, 1, 0, 0};
        int[] yCordinate = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Triplet triplet = queue.poll();
            int x = triplet.x;
            int y = triplet.y;
            int lvl = triplet.lvl;

            if (visited[x][y])
                continue;
            else
                visited[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int x1 = x + xCordinate[i];
                int y1 = y + yCordinate[i];
                if (isSafe(x1, y1, N, M, list, visited)) {
                    list.get(x1).set(y1, lvl + 1);
                    ((LinkedList<Triplet>) queue).add(new Triplet(x1, y1, lvl + 1));
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int level = list.get(i).get(j);
                if (list.get(i).get(j) == -1) {
                    level = 0;
                }
                sb.append(level + " ");
            }
        }
        System.out.println(sb.toString());


    }

    private static boolean isSafe(int x, int y, int N, int M, List<List<Integer>> list, boolean[][] visited) {
        return (x >= 0 && x < N) &&
                (y >= 0 && y < M) &&
                (list.get(x).get(y) == 0) &&
                (!visited[x][y]);
    }
}
