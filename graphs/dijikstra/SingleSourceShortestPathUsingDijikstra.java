package graphs.dijikstra;

/**
 * @author Aayush Srivastava
 */
public class SingleSourceShortestPathUsingDijikstra extends WeightedGraph {

    public SingleSourceShortestPathUsingDijikstra(int noOfVertices) {
        super(noOfVertices);
    }

    public static void main(String[] args) {
        SingleSourceShortestPathUsingDijikstra obj1 = new SingleSourceShortestPathUsingDijikstra(5);
        obj1.addUndirectedEdge(0, 1, 9);
        obj1.addUndirectedEdge(0, 2, 6);
        obj1.addUndirectedEdge(0, 3, 5);
        obj1.addUndirectedEdge(0, 4, 3);

        obj1.addUndirectedEdge(2, 1, 2);
        obj1.addUndirectedEdge(2, 3, 4);

        obj1.dijikstra(0);

        System.out.println();

        int adjacencyMatrix[][] = new int[][]
                {
                        {0, 4, 0, 0, 0, 0, 0, 8, 0},
                        {4, 0, 8, 0, 0, 0, 0, 11, 0},
                        {0, 8, 0, 7, 0, 4, 0, 0, 2},
                        {0, 0, 7, 0, 9, 14, 0, 0, 0},
                        {0, 0, 0, 9, 0, 10, 0, 0, 0},
                        {0, 0, 4, 14, 10, 0, 2, 0, 0},
                        {0, 0, 0, 0, 0, 2, 0, 1, 6},
                        {8, 11, 0, 0, 0, 0, 1, 0, 7},
                        {0, 0, 2, 0, 0, 0, 6, 7, 0}
                };

        SingleSourceShortestPathUsingDijikstra obj2 = new SingleSourceShortestPathUsingDijikstra(9);
        obj2.dijikstra(adjacencyMatrix, 0);
    }

    private void dijikstra(int src) {
        minHeap.add(new GraphNode(src, 0));
        distance[src] = 0;

        while (!minHeap.isEmpty()) {
            GraphNode u = minHeap.poll();
            for (GraphNode v : adjacencyList.get(u.node)) {
                relaxEdge(u, v);
            }
        }

        printSolution(src);
    }

    private void dijikstra(int[][] graph, int src) {
        minHeap.add(new GraphNode(src, 0));
        distance[src] = 0;

        while (!minHeap.isEmpty()) {
            GraphNode u = minHeap.poll();
            for (int v = 0; v < graph[u.node].length; v++) {
                if (graph[u.node][v] != 0)
                    relaxEdge(graph, u.node, v);
            }
        }

        printSolution(src);
    }

    private void relaxEdge(int[][] graph, int i, int j) {
        if (distance[j] > distance[i] + graph[i][j]) {
            distance[j] = distance[i] + graph[i][j];
            parents[j] = i;//tracking only needed to retrace the path later
            minHeap.add(new GraphNode(j, distance[j]));
        }
    }

    private void relaxEdge(GraphNode u, GraphNode v) {
        if (distance[v.node] > distance[u.node] + v.weight) {
            distance[v.node] = distance[u.node] + v.weight;
            parents[v.node] = u.node;//tracking only needed to retrace the path later
            minHeap.add(new GraphNode(v.node, distance[v.node]));
        }
    }

    private void printSolution(int startVertex) {
        System.out.print("Vertex\t Distance\tPath");
        for (int vertexIndex = 0; vertexIndex < noOfVertices; vertexIndex++) {
            if (vertexIndex != startVertex) {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distance[vertexIndex] + "\t\t");
                printPath(vertexIndex);
            }
        }
    }

    private void printPath(int currentVertex) {
        if (currentVertex == NO_PARENT)
            return;

        printPath(parents[currentVertex]);
        System.out.print(currentVertex + " ");
    }

}

