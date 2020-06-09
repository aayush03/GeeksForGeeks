package graphs.bellman.ford;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author Aayush Srivastava
 */
public class FloatingPointWeightedGraph {

    protected int noOfVertices;
    protected PriorityQueue<Edge>[] adjacencyGraph;

    public int getNoOfVertices() {
        return noOfVertices;
    }

    public FloatingPointWeightedGraph(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        adjacencyGraph = new PriorityQueue[noOfVertices];
        for (int i = 0; i < noOfVertices; i++) {
            adjacencyGraph[i] = new PriorityQueue<Edge>();
        }
    }

    public void addEdge(int i, int j, float weight) {
        adjacencyGraph[i].add(new Edge(i, j, weight));
    }

    public void addEdge(Edge e) {
        adjacencyGraph[e.getStartPoint()].add(e);
    }

    public PriorityQueue<Edge> neighbours(int vertex) {
        return adjacencyGraph[vertex];
    }

    protected void printGraph() {
        for (int i = 0; i < noOfVertices; i++) {
            PriorityQueue<Edge> edges = neighbours(i);
            Iterator<Edge> it = edges.iterator();
            System.out.print(i + ": ");
            for (int j = 0; j < edges.size(); j++) {
                System.out.print(it.next() + " \t\t ");
            }
            System.out.println();
        }
    }
}

class Edge implements Comparable<Edge> {
    private int startPoint;
    private int endPoint;
    private float weight;


    public Edge(int startPoint, int endPoint, float weight) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.weight = weight;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public float getWeight() {
        return weight;
    }

    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return startPoint + " -> " + endPoint + " (" + weight + ")";
    }
}