
package org.geeksforgeeks.graph;
import java.util.*;

/**
 * @author X-XXX
 */
public class Solution
{
  static int biGraph(int[] arr, int n, int e) {
    // code here
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < e*2; i++) {
      int begin = arr[i];
      int end = arr[++i];
      graph.get(begin).add(end);
      graph.get(end).add(begin);
    }
    boolean[] visited = new boolean[n];
    int[] disc = new int[n];
    int[] low = new int[n];
    int[] parent = new int[n];
    boolean ap = false;
    Arrays.fill(parent, -1);
    Arrays.fill(visited, false);
    Arrays.fill(disc, Integer.MAX_VALUE);
    Arrays.fill(low, Integer.MAX_VALUE);
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        if (i > 0) {
          return 0;
        }
        ap = APAndBridgeUtil(0, i, graph, visited, disc, low, parent);
        if (ap) {
          return 0;
        }
      }
    }
    return 1;
  }

  static boolean APAndBridgeUtil(int time, int u, List<List<Integer>> graph, boolean[] visited, int[] disc, int[] low, int[] parent) {
    int children = 0;
    visited[u] = true;
    disc[u] = low[u] = ++time;
    for (int v : graph.get(u)) {
      if (!visited[v]) {
        children++;
        parent[v] = u;
        boolean ap = APAndBridgeUtil(time, v, graph, visited, disc, low, parent);
        if (ap)
          return true;
        if (parent[u] == -1 && children > 1) {
          return true;
        }
        low[u] = Math.min(low[u], low[v]);
        if (parent[u] != -1 && low[v] >= disc[u]) {
          return true;
        }
      } else if (v != parent[u]) {
        low[u] = Math.min(low[u], disc[v]);
      }
    }
    return false;
  }

  public static void main(String[] args)
  {
    int arr[] = {1, 0, 0, 2, 2, 1, 0, 3, 3, 4, 2, 4};
    System.out.println(Solution.biGraph(arr, 5, 6));
  }
}
