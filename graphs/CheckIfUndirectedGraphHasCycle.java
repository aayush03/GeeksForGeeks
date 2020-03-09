package graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Check Code
 */
public class CheckIfUndirectedGraphHasCycle extends Graph {

    public CheckIfUndirectedGraphHasCycle(int noOfVertices) {
        super(noOfVertices);
    }

    public static void main(String[] args) {
        CheckIfUndirectedGraphHasCycle graph = new CheckIfUndirectedGraphHasCycle(3);

        graph.addUndirectedEdge(1, 0);
        graph.addUndirectedEdge(1, 2);
        //graph.addUndirectedEdge(2, 0);
        //graph.addUndirectedEdge(0, 3);
        //graph.addUndirectedEdge(3, 4);

        System.out.println("Does undirected Graph has cycle ? ");
        System.out.println("Answer : " + graph.isCyclic());
    }

    private boolean isCyclic() {
        for (int i = 0; i < noOfVertices; i++)
            visitedNodes[i] = false;

        for (int i = 0; i < noOfVertices; i++)
            if (!visitedNodes[i])
                if (isCyclic(i, -1))
                    return true;
        return false;
    }

    private boolean isCyclic(int currIndex, int parent) {
        visitedNodes[currIndex] = true;

        Integer temp;

        Iterator itr = adjacencyList.get(currIndex).listIterator();

        while (itr.hasNext()) {
            temp = (int) itr.next();

            if (!visitedNodes[temp]) {
                if (isCyclic(temp, currIndex))
                    return true;
            } else if (temp != parent)
                return true;

        }
        return false;
    }

}
