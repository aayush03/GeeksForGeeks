package graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Graph{

    public static void main(String[] args) {
        BFS bfs = new BFS(4);
        bfs.addDirectedEdge(0, 1);
        bfs.addDirectedEdge(0, 2);
        bfs.addDirectedEdge(1, 2);
        bfs.addDirectedEdge(2, 0);
        bfs.addDirectedEdge(2, 3);
        bfs.addDirectedEdge(3, 3);
        bfs.bfs(2);
        System.out.println();

        BFS bfs1 = new BFS(6);
        bfs1.addUndirectedEdge(0, 1);
        bfs1.addUndirectedEdge(0, 2);
        bfs1.addUndirectedEdge(1, 2);

        bfs1.addUndirectedEdge(4, 5);

        bfs1.printBFSofDisconnectedGraph(2);
    }

    public BFS(int noOfVertices) {
        super(noOfVertices);
    }

    private void bfs(int sourceVertex) {

        Queue<Integer> queue = new LinkedList<>();

        visitedNodes[sourceVertex] = true;

        ((LinkedList<Integer>) queue).add(sourceVertex);

        while (!queue.isEmpty()) {
            sourceVertex = queue.poll();
            System.out.print(sourceVertex + " ");

            Iterator itr = adjacencyList.get(sourceVertex).listIterator();

            while (itr.hasNext()) {
                int currentVertex = (int) itr.next();
                if (!visitedNodes[currentVertex]) {
                    visitedNodes[currentVertex] = true;
                    ((LinkedList<Integer>) queue).add(currentVertex);
                }
            }
        }

    }

    private void printBFSofDisconnectedGraph(int sourceVertex) {
        for (int i = 0; i < noOfVertices; i++) {
            if (!visitedNodes[i]) {
                bfs(i);
            }
        }
    }
}
