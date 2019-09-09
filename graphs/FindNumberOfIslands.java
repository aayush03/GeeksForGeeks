package graphs;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A group of connected 1's forms an island.
 * The task is to complete the method findIslands() which returns the number of islands present.
 *
 * Input:
 * The first line of input will be the number of test cases T, then T test cases follow.
 * The first line of each testcase contains two space separated integers N and M.
 * Then in the next line are the NxM inputs of the matrix A separated by space .
 *
 * Sample Input:
 * 2
 * 3 3
 * 1 1 0 0 0 1 1 0 1
 * 4 4
 * 1 1 0 0 0 0 1 0 0 0 0 1 0 1 0 0
 */
public class FindNumberOfIslands {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            ArrayList<ArrayList<Integer>> list = new ArrayList<>(N);

            // creating arraylist of arraylist
            for (int i = 0; i < N; i++) {
                ArrayList<Integer> temp = new ArrayList<>(M);
                list.add(i, temp);
            }

            // adding elements to the arraylist of arraylist
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int val = sc.nextInt();
                    list.get(i).add(j, val);
                }
            }
            System.out.println("No of Islands : "+findIslands(list, N, M));
        }
    }

    private static void dfs(ArrayList<ArrayList<Integer>> list, int x, int y, boolean visited[][], int ROW, int COLUMN) {
        int rowNbr[] = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int colNbr[] = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

        visited[x][y] = true;

        for (int k = 0; k < 8; k++)
            if (isSafe(list, x + rowNbr[k], y + colNbr[k], visited, ROW, COLUMN))
                dfs(list, x + rowNbr[k], y + colNbr[k], visited, ROW, COLUMN);
    }

    private static boolean isSafe(ArrayList<ArrayList<Integer>> list, int x, int y, boolean visited[][], int ROW, int COLUMN) {
        return ((x >= 0) && (x < ROW)) &&
                ((y >= 0) && (y < COLUMN)) &&
                (list.get(x).get(y) == 1) &&
                (!visited[x][y]);
    }

    private static int findIslands(ArrayList<ArrayList<Integer>> list, int ROW, int COLUMN) {

        boolean visited[][] = new boolean[ROW + 1][COLUMN + 1];

        int count = 0;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (list.get(i).get(j) == 1 && !visited[i][j]) {
                    dfs(list, i, j, visited, ROW, COLUMN);
                    count++;
                }
            }
        }

        return count;
    }
}
