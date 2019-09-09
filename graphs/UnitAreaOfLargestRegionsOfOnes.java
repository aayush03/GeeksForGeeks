package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UnitAreaOfLargestRegionsOfOnes {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            String[] st = reader.readLine().split(" ");
            int n = Integer.parseInt(st[0]);
            int m = Integer.parseInt(st[1]);

            List<List<Integer>> list = new ArrayList<>();

            for (int i = 0; i < n + 1; i++)
                list.add(i, new ArrayList<>(m));

            String[] s = reader.readLine().split(" ");
            int k = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    list.get(i).add(Integer.parseInt(s[k++]));
                }
            }

            System.out.println(getLargestArea(list, n, m));


        }
    }

    private static int getLargestArea(List<List<Integer>> list, int ROW, int COL) {
        boolean[][] visited = new boolean[ROW][COL];

        int maxArea = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (list.get(i).get(j) == 1 && !visited[i][j]) {
                    AreaWrapper areaWrapper = new AreaWrapper();
                    getLargestAreaUtil(list, visited, i, j, ROW, COL, areaWrapper);
                    int area = areaWrapper.area;
                    maxArea = maxArea < area ? area : maxArea;
                }
            }
        }

        return maxArea;
    }

    private static void getLargestAreaUtil(List<List<Integer>> list, boolean[][] visited, int x, int y, int ROW, int COL, AreaWrapper areaWrapper) {
        int[] xCordinate = {-1, 1, 0, 0, 1, -1, 1, -1};
        int[] yCordinate = {0, 0, -1, 1, 1, -1, -1, 1};
        visited[x][y] = true;
        for (int i = 0; i < 8; i++) {
            if (isSafe(list, visited, x + xCordinate[i], y + yCordinate[i], ROW, COL)) {
                areaWrapper.area++;
                getLargestAreaUtil(list, visited, x + xCordinate[i], y + yCordinate[i], ROW, COL, areaWrapper);
            }
        }
    }

    private static boolean isSafe(List<List<Integer>> list, boolean[][] visited, int x, int y, int ROW, int COL) {
        return (x >= 0 && x < ROW) &&
                (y >= 0 && y < COL) &&
                (list.get(x).get(y) == 1) &&
                (!visited[x][y]);
    }


}

class AreaWrapper {
    int area = 1;
}