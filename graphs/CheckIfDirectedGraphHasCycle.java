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
    }

    private boolean isCyclic() {
        boolean[] recStack = new boolean[noOfVertices];
        for (int i = 0; i < noOfVertices; i++)
            if (isCyclic(i, recStack))
                return true;
        return false;
    }

    private boolean isCyclic(int currIndex, boolean[] recStack) {
        if (recStack[currIndex])
            return true;

        if (visitedNodes[currIndex])
            return false;

        visitedNodes[currIndex] = true;
        recStack[currIndex] = true;

        List<Integer> children = adjacencyList.get(currIndex);

        for (int c : children)
            if (isCyclic(c, recStack))
                return true;

        recStack[currIndex] = false;

        return false;
    }
}
