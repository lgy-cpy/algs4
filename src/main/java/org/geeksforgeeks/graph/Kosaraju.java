package org.geeksforgeeks.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * @author X-XXX
 */
public class Kosaraju
{
  public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj)
  {
    //code here
    Deque<Integer> stack = new ArrayDeque<>(); // stack to keep
    boolean[] visited = new boolean[V];
    for (int i = 0; i < V; i++) {
      if (!visited[i]) {
        fillOrder(i, adj, visited, stack);
      }
    }
    visited = new boolean[V];
    ArrayList<ArrayList<Integer>> trans = getTranspose(V, adj);
    int count = 0;
    while (!stack.isEmpty()) {
      int v = stack.pop();
      if (!visited[v]) {
        count++;
        dfsUtil(v, trans, visited);
      }
    }
    return count;
  }

  private void dfsUtil(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
    visited[v] = true;
    for (int n : adj.get(v)) {
      if (!visited[n]) {
        dfsUtil(n, adj, visited);
      }
    }
  }

  private void fillOrder(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Deque<Integer> stack) {
    visited[v] = true;
    for (int n : adj.get(v)) {
      if (!visited[n]) {
        fillOrder(n, adj, visited, stack);
      }
    }
    stack.push(v);
  }

  private ArrayList<ArrayList<Integer>> getTranspose(int V, ArrayList<ArrayList<Integer>> adj) {
    ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      transpose.add(new ArrayList<>());
    }
    for (int i = 0; i < V; i++) {
      for (int n : adj.get(i)) {
        transpose.get(n).add(i);
      }
    }
    return transpose;
  }
}
