package graphs;

import java.util.List;

/**
 * This is an application of DFS. In case of a disconnected graph,
 * we can check for a cycle in individual trees by checking back edges.
 */
public class CheckIfDirectedGraphHasCycle extends Graph {

    public CheckIfDirectedGraphHasCycle(int noOfVertices) {
        super(noOfVertices);
    }

    /**
     * Uncommenting the lines of code will return value true
     *
     * @param args
     */
    public static void main(String[] args) {
        CheckIfDirectedGraphHasCycle graph = new CheckIfDirectedGraphHasCycle(4);
        graph.addDirectedEdge(0, 1);
        //graph.addDirectedEdge(0, 2);
        graph.addDirectedEdge(1, 2);
        //graph.addDirectedEdge(2, 0);
        graph.addDirectedEdge(2, 3);
        //graph.addDirectedEdge(3, 3);

        System.out.println(graph.isCyclic());

        System.out.println("Is Directed graph Cyclic using colors : " + isCyclic(graph));
    }

    /**
     * WHITE : Vertex is not processed yet. Initially, all vertices are WHITE.
     * <p>
     * GRAY: Vertex is being processed (DFS for this vertex has started, but not finished which means that
     * all descendants (in DFS tree) of this vertex are not processed yet (or this vertex is in the function call stack)
     * <p>
     * BLACK : Vertex and all its descendants are processed. While doing DFS, if an edge is encountered
     * from current vertex to a GRAY vertex, then this edge is back edge and hence there is a cycle.
     */
    static int WHITE = 0, GRAY = 1, BLACK = 2;


    /**
     * Algorithm:
     * <p>
     * 1.) Create a recursive function that takes the edge and color array (this can be also kept as a global variable)
     * 2.) Mark the current node as GREY.
     * 3.) Traverse all the adjacent nodes and if any node is marked GREY then return true as a loop is bound to exist.
     * 4.) If any adjacent vertex is WHITE then call the recursive function for that node. If the function returns true. Return true.
     * 5.) If no adjacent node is grey or has not returned true then mark the current Node as BLACK and return false.
     */
    static boolean isCyclic(Graph g) {
        // Initialize color of all vertices as WHITE
        int[] color = new int[g.noOfVertices];
        for (int i = 0; i < g.noOfVertices; i++) {
            color[i] = WHITE;
        }

        // Do a DFS traversal beginning with all
        // vertices
        for (int i = 0; i < g.noOfVertices; i++) {
            if (color[i] == WHITE) {
                if (DFSUtil(g, i, color) == true)
                    return true;
            }
        }
        return false;

    }

    static boolean DFSUtil(Graph g, int u, int[] color) {
        // GRAY : This vertex is being processed (DFS
        // for this vertex has started, but not
        // ended (or this vertex is in function
        // call stack)
        color[u] = GRAY;

        // Iterate through all adjacent vertices
        for (Integer in : g.adjacencyList.get(u)) {
            // If there is
            if (color[in] == GRAY)
                return true;

            // If v is not processed and there is a back
            // edge in subtree rooted with v
            if (color[in] == WHITE && DFSUtil(g, in, color) == true)
                return true;
        }

        // Mark this vertex as processed
        color[u] = BLACK;
        return false;
    }

    private boolean isCyclic() {
        boolean[] recursionStack = new boolean[noOfVertices];
        for (int i = 0; i < noOfVertices; i++)
            if (isCyclic(i, recursionStack))
                return true;
        return false;
    }

    private boolean isCyclic(int currIndex, boolean[] recursionStack) {
        if (recursionStack[currIndex])
            return true;

        if (visitedNodes[currIndex])
            return false;

        visitedNodes[currIndex] = true;
        recursionStack[currIndex] = true;

        List<Integer> children = adjacencyList.get(currIndex);

        for (int c : children)
            if (isCyclic(c, recursionStack))
                return true;

        recursionStack[currIndex] = false;

        return false;
    }
}
