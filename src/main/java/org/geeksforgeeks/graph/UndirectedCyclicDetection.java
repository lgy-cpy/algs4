package org.geeksforgeeks.graph;

import java.util.ArrayList;

/**
 * @author X-XXX
 */
public class UndirectedCyclicDetection
{
  // Function to detect cycle in an undirected graph.
  public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
    // Code here
    boolean[] visited = new boolean[V];
    for (int i = 0; i < V; i++) {
      if (!visited[i]) {
        if (isCycleUtil(adj, i, visited, -1))
          return true;
      }
    }
    return false;
  }

  private boolean isCycleUtil(ArrayList<ArrayList<Integer>> adj, int u, boolean[] visited, int parent) {
    visited[u] = true;
    for (int v : adj.get(u)) {
      if (!visited[v]) {
        if (isCycleUtil(adj, v, visited, u)) {
          return true;
        }
      } else if (v != parent) {
        return true;
      }
    }
    return false;
  }
}
