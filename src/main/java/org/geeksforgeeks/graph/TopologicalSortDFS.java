package org.geeksforgeeks.graph;

import java.util.*;

/**
 * 基于DFS的拓扑排序，同时对于环进行处理
 */
public class TopologicalSortDFS {

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
      int[] color = new int[V]; // 0: 未访问, 1: 正在访问, 2: 已访问
      Deque<Integer> stack = new ArrayDeque<>();

      for (int i = 0; i < V; i++) {
        if (color[i] == 0) {
          if (!topologicalSortUtil(i, color, stack)) {
            throw new RuntimeException("Graph contains cycle, topological sort not possible");
          }
        }
      }

      List<Integer> topoOrder = new ArrayList<>();
      while (!stack.isEmpty()) {
        topoOrder.add(stack.pollFirst());
      }

      return topoOrder;
    }

    // false 代表没有正常结束，因为检测到了环
    private boolean topologicalSortUtil(int v, int[] color, Deque<Integer> stack) {
      color[v] = 1; // 标记当前节点为正在访问

      for (int neighbor : adj.get(v)) {
        if (color[neighbor] == 1) {
          return false; // 发现一个回边，存在环
        }
        if (color[neighbor] == 0 && !topologicalSortUtil(neighbor, color, stack)) {
          return false;
        }
      }

      color[v] = 2; // 标记当前节点为已访问
      stack.offerFirst(v); // 将已访问的节点压入栈，到时候出栈的时候会先出，保证顺序是反过来
      return true;
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

    try {
      List<Integer> topoOrder = graph.topologicalSort();
      System.out.println("Topological Sorting:");
      System.out.println(topoOrder);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }

    // 测试含有环的图
    Graph cyclicGraph = new Graph(3);
    cyclicGraph.addEdge(0, 1);
    cyclicGraph.addEdge(1, 2);
    cyclicGraph.addEdge(2, 0);

    try {
      List<Integer> topoOrder = cyclicGraph.topologicalSort();
      System.out.println("Topological Sorting:");
      System.out.println(topoOrder);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage()); // 应该输出“Graph contains cycle, topological sort not possible”
    }
  }
}

