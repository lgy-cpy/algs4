package org.geeksforgeeks.graph;

import java.util.*;

/**
 * @author X-XXX
 */
public class GoodComponent
{
  // 使用并查集的方法来计算连通集, 先找出每个点属于那个连通集，然后计算每个连通集点的数量，以及其中每个点的边数
  public static int findNumberOfGoodComponent(int e, int v, int[][] edges) {
    // code here
    int[] parent = new int[v+1];
    for (int i = 1; i <= v; i++) {
      parent[i] = i;
    }
    int[] count = new int[v+1];
    int[] rank = new int[v+1];
    for (int[] edge : edges) {
      count[edge[0]]++;
      count[edge[1]]++;
      int p1 = find(parent, edge[0]);
      int p2 = find(parent, edge[1]);
      if (p1 != p2) {
        union(parent, rank, edge[0], edge[1]);
      }
    }

    int ans = 0;
    Map<Integer, Integer> vertexCount = new HashMap<>();
    for (int i = 1; i <= v; i++) {
      int root = find(parent, i);
      if (vertexCount.get(root) == null) {
        vertexCount.put(root, 1);
      } else {
        vertexCount.put(root, vertexCount.get(root) + 1);
      }
    }
    Map<Integer, Boolean> vertexGood = new HashMap<>();
    for (int i = 1; i <= v; i++) {
      int root = find(parent, i);
      if (vertexGood.get(root) == null || vertexGood.get(root)) {
        if (count[i] == vertexCount.get(root) - 1) {
          vertexGood.put(root, true);
        } else {
          vertexGood.put(root, false);
        }
      }
    }
    for (Map.Entry<Integer, Boolean> entry : vertexGood.entrySet()) {
      if (entry.getValue()) {
        ans++;
      }
    }
    return ans;
  }

  static int find(int[] parent, int vertex) {
    while (parent[vertex] != vertex) {
      parent[vertex] = parent[parent[vertex]];
      vertex = parent[vertex];
    }
    return vertex;
  }

  static void union(int[] parent, int[] rank, int p, int q)
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
  }

  // 方法二利用的是dfs，现将edges转换成List<List>的表达。进行dfs
  public int findNumberOfGoodComponent2(int E, int V, int[][] edges) {
    boolean v[] = new boolean[V + 1]; // boolean array to track visited vertices
    int ans = 0; // variable to store the number of good components

    ArrayList<ArrayList<Integer>> adj = new ArrayList<>(); // adjacency list
    for (int i = 0; i <= V; i++) {
      adj.add(new ArrayList<>());
    }

    // Convert edge array to adjacency list
    for (int[] edge : edges) {
      int a = edge[0];
      int b = edge[1];
      adj.get(a).add(b);
      adj.get(b).add(a);
    }

    for (int i = 1; i <= V; i++) {
      if (!v[i]) { // if the current vertex is not visited
        int[] counts =
            dfs(adj, i, v); // perform depth-first search starting from i

        int vertices = counts[0];
        int edgesCount = counts[1];

        edgesCount /= 2; // divide the count of edges by 2 (since each edge is
        // counted twice)

        // check if the count of edges is equal to the maximum number of edges
        // possible in a complete graph
        if (edgesCount == (vertices * (vertices - 1)) / 2) {
          ans++; // increment the count of good components
        }
      }
    }

    return ans; // return the number of good components
  }

  private static int[] dfs(ArrayList<ArrayList<Integer>> A, int i, boolean v[]) {
    v[i] = true;               // mark the current vertex as visited
    int[] counts = new int[2]; // counts[0] stores vertices, counts[1] stores edges
    counts[0] = 1;             // increment the count of vertices
    counts[1] +=
        A.get(i).size(); // add the number of edges connected to the current vertex

    for (int child :
        A.get(i)) {     // iterate over the adjacent vertices of the current vertex
      if (!v[child]) { // if the child vertex is not visited
        int[] childCounts = dfs(
            A, child,
            v); // recursively perform depth-first search on the child vertex
        counts[0] += childCounts[0]; // update vertices count 统计每一个点和从child出发的所有点和边进行求和，进行统计
        counts[1] += childCounts[1]; // update edges count
      }
    }
    return counts;
  }
}
