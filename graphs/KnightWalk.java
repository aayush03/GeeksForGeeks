package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class KnightWalk {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            String[] st = reader.readLine().split(" ");

            int N = Integer.parseInt(st[0]);
            int M = Integer.parseInt(st[1]);

            String[] s = reader.readLine().split(" ");

            int s1 = Integer.parseInt(s[0]);
            int s2 = Integer.parseInt(s[1]);

            int d1 = Integer.parseInt(s[2]);
            int d2 = Integer.parseInt(s[3]);

            System.out.println(noOfMoves(N, M, s1, s2, d1, d2));
        }
    }

    private static int noOfMoves(int ROW, int COL, int s1, int s2, int d1, int d2) {
        boolean[][] visited = new boolean[ROW + 1][COL + 1];

        class Triplet {
            int x;
            int y;
            int dist;

            public Triplet(int x, int y, int dist) {
                this.x = x;
                this.y = y;
                this.dist = dist;
            }
        }

        Queue<Triplet> queue = new LinkedList<>();
        visited[s1][s2] = true;

        ((LinkedList<Triplet>) queue).add(new Triplet(s1, s2, 0));
        int[] xCordinate = {-2, -2, 2, 2, 1, 1, -1, -1};
        int[] yCordinate = {1, -1, 1, -1, -2, 2, -2, 2};

        while (!queue.isEmpty()) {
            Triplet curr = queue.poll();

            int noOfMoves = curr.dist;
            int x = curr.x;
            int y = curr.y;
            if (x == d1 && y == d2) {
                return noOfMoves;
            }

            for (int i = 0; i < 8; i++) {
                int x1 = x + xCordinate[i];
                int y1 = y + yCordinate[i];
                if (isSafe(visited, ROW, COL, x1, y1)) {
                    visited[x1][y1] = true;
                    ((LinkedList<Triplet>) queue).add(new Triplet(x1, y1, noOfMoves + 1));
                }
            }
        }

        return -1;
    }

    private static boolean isSafe(boolean[][] visited, int ROW, int COL, int x, int y) {
        return (x >= 1 && x <= ROW) &&
                (y >= 1 && y <= COL) &&
                (!visited[x][y]);
    }
}