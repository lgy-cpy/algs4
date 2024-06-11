package org.geeksforgeeks.graph;

import java.util.ArrayList;

/**
 * @author X-XXX
 */
public class DirectedCyclicDetection
{
  public static void main(String[] args)
  {
    int V = 7;
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
    /*
    * 6 5
      5 3
      3 1
      1 2
      2 4
      4 0
    * */
    // adj.get(0).add();
    adj.get(1).add(2);
    adj.get(2).add(4);
    adj.get(3).add(1);
    adj.get(4).add(0);
    adj.get(5).add(3);
    adj.get(6).add(5);
    new DirectedCyclicDetection().isCyclic(7, adj);
  }

  // Function to detect cycle in a directed graph.
  public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
    // code here
    // 和无向图有所不同，
    int[] visited = new int[V];
    for (int i = 0; i < V; i++) {
      if (visited[i] == 0) {
        if (isCycleUtil(adj, i, visited)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isCycleUtil(ArrayList<ArrayList<Integer>> adj, int u, int[] visited) {
    visited[u] = 1;
    for (int v : adj.get(u)) {
      if (visited[v] == 1) {
        return true;
      } else if (visited[v] == 0 && isCycleUtil(adj, v, visited)) {
        return true;
      }
    }
    visited[u] = 2;
    return false;
  }
}
