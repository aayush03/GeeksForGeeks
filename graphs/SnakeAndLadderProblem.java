package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given a snake and ladder board of order 5x6, find the minimum number of dice throws required to
 * reach the destination or last cell (30th cell) from source (1st cell) .
 */

/**
 * Sample Input :
 * 2
 * 6
 * 11 26 3 22 5 8 20 29 27 1 21 9
 * 1
 * 2 30
 */
public class SnakeAndLadderProblem {

    private static final int ROW = 5;
    private static final int COLUMN = 6;
    private static final int START = 1;
    private static final int DESTINATION = 30;


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine());

            int[] arr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            List<List<Integer>> list = new ArrayList<>((ROW * COLUMN) + 1);

            for (int i = 0; i <= ROW * COLUMN; i++)
                list.add(i, new ArrayList<>());
            int p = 0;

            Map<Integer, Integer> ladders = new HashMap<>();
            Map<Integer, Integer> snakes = new HashMap<>();

            for (int i = 0; i < n; i++) {
                int source = arr[p++];
                int destination = arr[p++];
                if (destination > source)
                    ladders.put(source, destination);
                else
                    snakes.put(source, destination);
            }

            for (int i = 1; i <= ROW * COLUMN; i++) {
                for (int j = 1; j <= 6 && i + j <= ROW * COLUMN; j++) {
                    int dest;

                    int destinationIfLadderExistsFromSource = ladders.containsKey(i + j) ? ladders.get(i + j) : 0;
                    int destinationIfSnakeExistsFromSource = snakes.containsKey(i + j) ? snakes.get(i + j) : 0;

                    if (destinationIfLadderExistsFromSource != 0)
                        dest = destinationIfLadderExistsFromSource;
                    else if (destinationIfSnakeExistsFromSource != 0)
                        dest = destinationIfSnakeExistsFromSource;
                    else
                        dest = i + j;

                    list.get(i).add(dest);
                }
            }
            System.out.println(getNumberOfDiceThrows(list, ROW * COLUMN));

        }
    }

    private static int getNumberOfDiceThrows(List<List<Integer>> list,final int N) {
        class Board {
            int vertex;
            int distance;

            private Board(int vertex, int distance) {
                this.vertex = vertex;
                this.distance = distance;
            }
        }

        boolean[] visited = new boolean[N + 1];

        Queue<Board> queue = new LinkedList<>();

        Board source = new Board(START, 0);
        ((LinkedList<Board>) queue).add(source);
        visited[START] = true;

        while (!queue.isEmpty()) {
            source = queue.poll();

            if (source.vertex == DESTINATION)
                return source.distance;

            Iterator itr = list.get(source.vertex).listIterator();

            while (itr.hasNext()) {
                int curr = (int) itr.next();
                if (!visited[curr]) {
                    visited[curr] = true;

                    ((LinkedList<Board>) queue).add(new Board(curr, source.distance + 1));
                }
            }
        }

        return 0;
    }
}
