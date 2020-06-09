package graphs.bellman.ford;

import java.util.Iterator;

/**
 * @author Aayush Srivastava
 */
public class SingleSourceShortestPathUsingBellmanFord extends FloatingPointWeightedGraph {

    public SingleSourceShortestPathUsingBellmanFord(int noOfVertices) {
        super(noOfVertices);
    }

    public static void main(String[] args) {
        SingleSourceShortestPathUsingBellmanFord g = new SingleSourceShortestPathUsingBellmanFord(5);

        System.out.println("Graph:");
        // add Edges
        g.addEdge(0, 1, 5.2f);
        g.addEdge(0, 3, 12.8f);
        g.addEdge(0, 2, 10.3f);
        g.addEdge(1, 3, 5.9f);
        g.addEdge(1, 4, 15.2f);
        g.addEdge(2, 1, 1.5f);
        g.addEdge(2, 3, 2.3f);
        g.addEdge(3, 4, 8.5f);
        g.addEdge(4, 2, 2.7f);

        // print Graph
        g.printGraph();

        // Bellman-Ford Shortest Path Algorithm
        System.out.println("Bellman-Ford Shortest Path:");
        BellmanFord(g, 0);

        //------------------------------------------------------------------------------
        System.out.println("\nNew graph with negative weight circle!");

        SingleSourceShortestPathUsingBellmanFord g2 = new SingleSourceShortestPathUsingBellmanFord(8);

        System.out.println("Graph:");
        // add Edges
        g2.addEdge(0, 1, 4);
        g2.addEdge(0, 2, 4);
        // 1 nowhere
        g2.addEdge(2, 4, 4);
        g2.addEdge(2, 5, -2);
        g2.addEdge(3, 0, 3);
        g2.addEdge(3, 2, 2);
        g2.addEdge(4, 3, 1);
        g2.addEdge(4, 6, -2); // *
        g2.addEdge(5, 1, 3);
        g2.addEdge(5, 4, -3);
        g2.addEdge(6, 5, 2);
        g2.addEdge(6, 7, 2); // *
        g2.addEdge(7, 4, -2); //*
        // *part of a negative-weight circle -2 + 2 - 2 = -2 < 0

        // print Graph
        g2.printGraph();

        // Bellman-Ford Shortest Path Algorithm
        System.out.println("Bellman-Ford Shortest Path:");
        BellmanFord(g2, 0);

    }

    public static void BellmanFord(FloatingPointWeightedGraph g, int startVertex) {
        // for storing distances
        float[] distances = new float[g.getNoOfVertices()];
        // for storing predecessors
        int[] predecessors = new int[g.getNoOfVertices()];

        // initialize arrays
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Float.MAX_VALUE;
            predecessors[i] = -1;
        }
        distances[startVertex] = 0;

        // relax all edges v - 1 times repeatedly
        for (int i = 1; i < g.getNoOfVertices() - 1; i++) {
            for (int j = 0; j < g.getNoOfVertices() - 1; j++) {
                Iterator<Edge> it = g.neighbours(j).iterator();
                while (it.hasNext()) {
                    Edge e = it.next(); // edge (u, v)
                    // if dist(u) + w(u, v) < dist(v) then
                    // dist(v) = dist(u) + w(u, v)
                    // pred(v) = u
                    if (distances[e.getStartPoint()] + e.getWeight() < distances[e.getEndPoint()]) {
                        distances[e.getEndPoint()] = distances[e.getStartPoint()] + e.getWeight();
                        predecessors[e.getEndPoint()] = e.getStartPoint();
                    }
                }
            }
        }

        // check for negative-weight circles
        for (int i = 0; i < g.getNoOfVertices() - 1; i++) {
            Iterator<Edge> it = g.neighbours(i).iterator();
            while (it.hasNext()) {
                Edge e = it.next(); // edge (u, v)
                // if dist(u) + w(u, v) < dist(v) then
                // graph contains negative-weight circle
                if (distances[e.getStartPoint()] + e.getWeight() < distances[e.getEndPoint()]) {
                    System.out.println("Graph contains negative-weight circle!");
                    return;
                }
            }
        }

        // print final shortest paths
        System.out.println("Vertex\tDistance\tPredecessor");
        for (int i = 0; i < g.getNoOfVertices(); i++) {
            System.out.println(i + "\t" + distances[i] + "\t\t" + predecessors[i]);
        }

    }
}
