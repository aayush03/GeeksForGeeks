package graphs.dijikstra;

import java.util.*;

/**
 * @author Aayush Srivastava
 */
public class WeightedGraph {

    protected final int NO_PARENT = -1;
    protected List<List<GraphNode>> adjacencyList;
    protected int noOfVertices;
    protected int[] distance;
    protected Queue<GraphNode> minHeap;
    protected int[] parents;

    public WeightedGraph(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        this.adjacencyList = new ArrayList<>(noOfVertices);
        for (int i = 0; i < noOfVertices; i++)
            adjacencyList.add(new ArrayList<>());

        this.distance = new int[noOfVertices];
        for (int i = 0; i < noOfVertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        this.minHeap = new PriorityQueue<>(noOfVertices, (a, b) -> a.weight - b.weight);
        this.parents = new int[noOfVertices];
        Arrays.fill(parents, NO_PARENT);
    }

    protected void addDirectedEdge(int sourceVertex, int destinationVertex, int costToReachDestinationVertex) {
        adjacencyList.get(sourceVertex).add(new GraphNode(destinationVertex, costToReachDestinationVertex));
    }

    protected void addUndirectedEdge(int sourceVertex, int destinationVertex, int costToReachDestinationVertex) {
        adjacencyList.get(sourceVertex).add(new GraphNode(destinationVertex, costToReachDestinationVertex));
        adjacencyList.get(destinationVertex).add(new GraphNode(sourceVertex, costToReachDestinationVertex));
    }
}

class GraphNode {
    public int node;
    public int weight;

    public GraphNode(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

}
