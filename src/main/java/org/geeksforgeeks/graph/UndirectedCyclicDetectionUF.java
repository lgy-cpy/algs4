package org.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用并查集的方法来解决问题。
 */
public class UndirectedCyclicDetectionUF
{
  public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
    // Code here
    int[] parent = new int[V];
    for (int i = 0; i < V; i++) {
      parent[i] = i; // 初始化，要把parent设定成自身，或者设定成-1
    }
    int[] rank = new int[V];
    for (int i = 0; i < V; i++) {
      for (int j : adj.get(i)) {
        if (i < j) {
          // 查找一条边的两端，看是否已经属于同一个集合，如果已经属于，此时又要增加一条边，因此有环
          if (find(parent, i) == find(parent, j)) {
            return true;
          }
          // 将两个顶点归并在一起
          union(parent, rank, i, j);
        }
      }
    }
    return false;
  }


  // find旨在找到root，并且把parent的值直接设成root的值，这样可以节省路径
  int find(int[] parent, int vertex) {
    while (parent[vertex] != vertex) {
      parent[vertex] = parent[parent[vertex]];
      vertex = parent[vertex];
    }
    return vertex;
  }

  int findRecursive(int[] parent, int vertex)
  {
    if (parent[vertex] != vertex) {
      parent[vertex] = findRecursive(parent, parent[vertex]);
    }
    return parent[vertex];
  }

  void union(int[] parent, int[] rank, int p, int q)
  {
    int rootP = find(parent, p);
    int rootQ = find(parent, q);
    if (rootP != rootQ) {
      if      (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
      else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
      else {
        parent[rootP] = rootQ;
        rank[rootQ]++;
      }
    }
    int count = 0;
    Map<Integer, Boolean> map = new HashMap<>();
    map.put(1, true);
    for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
      if (entry.getValue()) {
        count++;
      }
    }
  }
}
