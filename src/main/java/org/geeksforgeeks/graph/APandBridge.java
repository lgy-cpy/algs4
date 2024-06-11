package org.geeksforgeeks.graph;

/**
 * @author X-XXX
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APandBridge
{
  private int V; // Number of vertices
  private List<List<Integer>> adj; // Adjacency list

  // Constructor
  public APandBridge(int V) {
    this.V = V;
    adj = new ArrayList<>(V);
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
  }

  // Add an edge to the graph
  public void addEdge(int v, int w) {
    adj.get(v).add(w);
    adj.get(w).add(v); // Since the graph is undirected
  }

  // A recursive function that finds articulation points and bridges using DFS
  private void APAndBridgeUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent, boolean[] ap, List<int[]> bridges) {
    int children = 0;
    visited[u] = true;
    disc[u] = low[u] = ++time;

    for (int v : adj.get(u)) {
      if (!visited[v]) {
        children++;
        parent[v] = u;
        APAndBridgeUtil(v, visited, disc, low, parent, ap, bridges);

        low[u] = Math.min(low[u], low[v]);

        // Check if the subtree rooted at v has a connection back to one of the ancestors of u
        if (parent[u] == -1 && children > 1) {
          ap[u] = true;
        }

        // low[v] 表示从 v 或其子孙节点可以到达的最早的发现时间。
        //如果 low[v] >= disc[u]，说明 v 及其子树中没有能够回到 u 或 u 祖先的路径，即 u 是分离 v 子树与其他部分的关键节点。
        //移除 u 将使得 v 子树与其他部分分离，因此 u 是割点。
        if (parent[u] != -1 && low[v] >= disc[u]) {
          ap[u] = true;
        }

        // If the lowest vertex reachable from subtree under v is below u in DFS tree, then u-v is a bridge
        if (low[v] > disc[u]) {
          bridges.add(new int[]{u, v});
        }
      } else if (v != parent[u]) { // 必须遵循顺序才可以返回
        low[u] = Math.min(low[u], disc[v]);
      }
    }
  }

  // The function to find and print all articulation points and bridges
  public void findAPAndBridges() {
    boolean[] visited = new boolean[V];
    int[] disc = new int[V];
    int[] low = new int[V];
    int[] parent = new int[V];
    boolean[] ap = new boolean[V];
    List<int[]> bridges = new ArrayList<>();

    Arrays.fill(parent, -1);
    Arrays.fill(visited, false);
    Arrays.fill(ap, false);
    Arrays.fill(disc, Integer.MAX_VALUE);
    Arrays.fill(low, Integer.MAX_VALUE);

    // Perform DFS from each vertex to find all articulation points and bridges
    for (int i = 0; i < V; i++) {
      if (!visited[i]) {
        APAndBridgeUtil(i, visited, disc, low, parent, ap, bridges);
      }
    }

    // Output the articulation points
    System.out.println("Articulation points:");
    for (int i = 0; i < V; i++) {
      if (ap[i]) {
        System.out.print(i + " ");
      }
    }
    System.out.println();

    // Output the bridges
    System.out.println("Bridges:");
    for (int[] bridge : bridges) {
      System.out.println(bridge[0] + " - " + bridge[1]);
    }
  }

  private int time = 0;

  public static void main(String[] args) {
    APandBridge g1 = new APandBridge(5);
    g1.addEdge(0, 1);
    g1.addEdge(0, 2);
    g1.addEdge(1, 2);
    g1.addEdge(1, 3);
    g1.addEdge(3, 4);

    g1.findAPAndBridges();
    // Output:
    // Articulation points:
    // 1 3
    // Bridges:
    // 3 - 4
    // 1 - 3

    APandBridge g2 = new APandBridge(4);
    g2.addEdge(0, 1);
    g2.addEdge(1, 2);
    g2.addEdge(2, 3);
    g2.addEdge(3, 0);

    g2.findAPAndBridges();
    // Output:
    // Articulation points:
    // (No output, as there are no articulation points in this graph)
    // Bridges:
    // (No output, as there are no bridges in this graph)

    APandBridge g3 = new APandBridge(4);
    g3.addEdge(0, 1);
    g3.addEdge(1, 2);
    g3.addEdge(2, 3);
    g3.addEdge(3, 1);

    g3.findAPAndBridges();

    APandBridge g4 = new APandBridge(5);
    g4.addEdge(1, 0);
    g4.addEdge(0, 2);
    g4.addEdge(2, 1);
    g4.addEdge(0, 3);
    g4.addEdge(3, 4);
    g4.addEdge(2, 4);

    g4.findAPAndBridges();
  }
}

