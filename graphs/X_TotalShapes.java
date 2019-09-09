package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class X_TotalShapes {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            String[] st = read.readLine().split(" ");
            int N = Integer.parseInt(st[0]);
            int M = Integer.parseInt(st[1]);

            List<List<Character>> list = new ArrayList<>();

            for (int i = 0; i < N + 1; i++)
                list.add(i, new ArrayList<>(M));


            String[] strings = read.readLine().split(" ");

            for (int i = 0; i <N; i++) {
                char[] k = strings[i].toCharArray();
                for (int j = 0; j < k.length; j++) {
                    list.get(i).add(k[j]);
                }
            }

            System.out.println(countShapes(list, N, M));
        }
    }

    private static int countShapes(List<List<Character>> list, int ROW, int COL) {
        boolean[][] visited = new boolean[ROW][COL];

        int count = 0;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (list.get(i).get(j) == 'X' && !visited[i][j]) {
                    countShapes(list, visited, i, j, ROW, COL);
                    count++;
                }
            }
        }

        return count;
    }

    private static void countShapes(List<List<Character>> list, boolean[][] visited, int x, int y, int N, int M) {

        int[] xCordinate = {-1, 1, 0, 0};
        int[] yCordinate = {0, 0, -1, 1};

        visited[x][y] = true;

        for (int i = 0; i < 4; i++)
            if (isSafe(list, visited, x + xCordinate[i], y + yCordinate[i], N, M))
                countShapes(list, visited, x + xCordinate[i], y + yCordinate[i], N, M);
    }

    private static boolean isSafe(List<List<Character>> list, boolean[][] visited, int x, int y, int N, int M) {
        return (x >= 0 && x < N) &&
                (y >= 0 && y < M) &&
                (list.get(x).get(y) == 'X') &&
                (!visited[x][y]);
    }
}
