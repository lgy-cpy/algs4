package org.geeksforgeeks.graph;

/**
 * Kahn算法，是基于入度的
 */
import java.util.*;

public class TopologicalSortKahn {

  private static class Graph {
    private final int V;
    private final List<List<Integer>> adj;

    public Graph(int V) {
      this.V = V;
      adj = new ArrayList<>(V);
      for (int i = 0; i < V; i++) {
        adj.add(new ArrayList<>());
      }
    }

    public void addEdge(int u, int v) {
      adj.get(u).add(v);
    }

    public List<Integer> topologicalSort() {
      // 遍历每条边，计算入度
      int[] inDegree = new int[V];
      for (int i = 0; i < V; i++) {
        for (int neighbor : adj.get(i)) {
          inDegree[neighbor]++;
        }
      }

      Queue<Integer> queue = new LinkedList<>(); // 也可以使用ArrayDeque 没有区别，可能性能更好
      for (int i = 0; i < V; i++) {
        if (inDegree[i] == 0) {
          queue.add(i); // 找出所有入度为零的点，入队，开始计算。
        }
      }

      List<Integer> topoOrder = new ArrayList<>(); // 结果存放
      while (!queue.isEmpty()) {
        int u = queue.poll();
        topoOrder.add(u);

        for (int neighbor : adj.get(u)) {
          inDegree[neighbor]--; // 处理之后将入度减一，如果入度为零，入队等待处理。
          if (inDegree[neighbor] == 0) {
            queue.add(neighbor);
          }
        }
      }

      // 检查是否存在环
      if (topoOrder.size() != V) {
        throw new RuntimeException("Graph contains cycle, topological sort not possible");
      }

      return topoOrder;
    }
  }

  public static void main(String[] args) {
    Graph graph = new Graph(6);
    graph.addEdge(5, 2);
    graph.addEdge(5, 0);
    graph.addEdge(4, 0);
    graph.addEdge(4, 1);
    graph.addEdge(2, 3);
    graph.addEdge(3, 1);

    System.out.println("Topological Sorting:");
    try {
      List<Integer> topoOrder = graph.topologicalSort();
      System.out.println(topoOrder);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
  }
}

