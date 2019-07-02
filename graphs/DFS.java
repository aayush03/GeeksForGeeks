package graphs;

import java.util.Iterator;
import java.util.Stack;

public class DFS extends Graph {

    public DFS(int noOfVertices) {
        super(noOfVertices);
    }

    public static void main(String[] args) {
        DFS dfs = getGraphForDFS();
        dfs.dfsUsingRecursion(2);
        System.out.println();
        dfs = getGraphForDFS();
        dfs.printDFSOfDisconnectedGraph();
    }

    private static DFS getGraphForDFS() {
        DFS dfs = new DFS(4);
        dfs.addDirectedEdge(0, 1);
        dfs.addDirectedEdge(0, 2);
        dfs.addDirectedEdge(1, 2);
        dfs.addDirectedEdge(2, 0);
        dfs.addDirectedEdge(2, 3);
        dfs.addDirectedEdge(3, 3);
        return dfs;
    }


    private void dfsUsingRecursion(int sourceIndex){
        visitedNodes[sourceIndex] = true;
        System.out.print(sourceIndex+" ");

        Iterator itr = adjacencyList.get(sourceIndex).listIterator();
        while (itr.hasNext()){
            int currIndex = (int) itr.next();
            if (!visitedNodes[currIndex])
                dfsUsingRecursion(currIndex);
        }
    }

    private void printDFSOfDisconnectedGraph(){
        visitedNodes = new boolean[noOfVertices];
        for (int i =0;i<noOfVertices;i++)
            if (!visitedNodes[i])
                dfsUsingRecursion(i);
    }
}
