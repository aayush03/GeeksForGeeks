package graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given N X N matrix filled with 1 , 0 , 2 , 3 . Find whether there is a path possible from source to destination, traversing through blank cells only. You can traverse up, down, right and left.
 * <p>
 * A value of cell 1 means Source.
 * A value of cell 2 means Destination.
 * A value of cell 3 means Blank cell.
 * A value of cell 0 means Blank Wall.
 * Note : there is only single source and single destination(sink).
 */
public class CheckIfPathExistsBetweenTwoCells extends Graph {


    private static int ROWS;
    private static int COLUMNS;
    private static boolean visitedNodesInMatrix[][];
    private static int matrix[][];

    public CheckIfPathExistsBetweenTwoCells(int noOfVertices) {
        super(noOfVertices);
    }

    public static void main(String[] args) {
        int M[][] = {{3, 0, 3, 3, 3, 0, 3},
                {3, 3, 3, 3, 3, 0, 3},
                {2, 0, 0, 3, 3, 1, 0},
                {3, 3, 0, 3, 3, 3, 0},
                {0, 3, 0, 0, 0, 0, 0},
                {3, 3, 0, 3, 3, 0, 3},
                {0, 0, 3, 3, 3, 0, 3}
        };
        matrix = M;

        ROWS = 7;
        COLUMNS = 7;
        visitedNodesInMatrix = new boolean[ROWS][COLUMNS];
        CheckIfPathExistsBetweenTwoCells graph = new CheckIfPathExistsBetweenTwoCells(ROWS * COLUMNS + 2);
        System.out.println(graph.createGraphFromMatrixAndCheckIfPathExists() ? 1 : 0);
    }

    private boolean createGraphFromMatrixAndCheckIfPathExists() {
        int k = 1;
        int sourceVertex = 0, destinationVertex = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (matrix[i][j] != 0) {
                    if (isSafe(i, j + 1))
                        addUndirectedEdge(k, k + 1);
                    if (isSafe(i, j - 1))
                        addUndirectedEdge(k, k - 1);
                    if (j < COLUMNS - 1 && isSafe(i + 1, j))
                        addUndirectedEdge(k, k + COLUMNS);
                    if (i > 0 && isSafe(i - 1, j))
                        addUndirectedEdge(k, k - COLUMNS);
                }

                if (matrix[i][j] == 1)
                    sourceVertex = k;

                if (matrix[i][j] == 2)
                    destinationVertex = k;

                k++;
            }
        }

        return bfs(sourceVertex, destinationVertex);
    }

    private boolean isSafe(int x, int y) {
        return (x >= 0) && (x < ROWS) &&
                (y >= 0) && (y < COLUMNS) &&
                (matrix[x][y] != 0 && !visitedNodesInMatrix[x][y]);
    }

    private boolean bfs(int sourceVertex, int destinationVertex) {

        if (sourceVertex == destinationVertex)
            return true;

        Queue<Integer> queue = new LinkedList<>();

        visitedNodes[sourceVertex] = true;

        ((LinkedList<Integer>) queue).add(sourceVertex);

        while (!queue.isEmpty()) {
            sourceVertex = queue.poll();

            Iterator itr = adjacencyList.get(sourceVertex).listIterator();

            while (itr.hasNext()) {
                int currentVertex = (int) itr.next();
                if (currentVertex == destinationVertex)
                    return true;
                if (!visitedNodes[currentVertex]) {
                    visitedNodes[currentVertex] = true;
                    ((LinkedList<Integer>) queue).add(currentVertex);
                }
            }
        }

        return false;
    }
}
