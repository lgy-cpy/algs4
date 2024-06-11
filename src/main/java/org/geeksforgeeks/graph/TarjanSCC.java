package org.geeksforgeeks.graph;

import java.util.*;

public class TarjanSCC {
  private int V; // Number of vertices
  private List<Integer>[] adj; // Adjacency list
  private int time; // Time counter
  private int[] disc, low; // Discovery and low values
  private boolean[] stackMember; // Stack membership marker
  private Stack<Integer> stack; // Stack to store vertices

  public TarjanSCC(int V) {
    this.V = V;
    adj = new ArrayList[V];
    for (int i = 0; i < V; i++) {
      adj[i] = new ArrayList<>();
    }
    disc = new int[V];
    low = new int[V];
    stackMember = new boolean[V];
    stack = new Stack<>();
    Arrays.fill(disc, -1); // Initialize discovery times
  }

  public void addEdge(int v, int w) {
    adj[v].add(w);
  }

  private void SCCUtil(int u) {
    disc[u] = low[u] = ++time;
    stack.push(u);
    stackMember[u] = true;

    System.out.println("Push " + u + " to stack: " + stack);

    for (int v : adj[u]) {
      if (disc[v] == -1) { // If v is not visited
        SCCUtil(v);
        low[u] = Math.min(low[u], low[v]); // 这个步骤很重要，因为low v很可能被更新，我们需要更新low u 因为u->v, low u <= low v
      } else if (stackMember[v]) { // Update low[u] if v is on stack
        low[u] = Math.min(low[u], disc[v]); // 因为在栈内，要用本身的值进行更新
      }
    }

    if (low[u] == disc[u]) { // 相等的时候相当于找到了根节点，因为disc是不变的，这相当于第一个放入的值，这样就找到了一个强连通分量
      System.out.print("SCC: ");
      int w;
      do {
        w = stack.pop();
        System.out.print(w + " ");
        stackMember[w] = false;
        System.out.println("Pop " + w + " from stack: " + stack);
      } while (w != u);
      System.out.println();
    }
  }

  public void SCC() {
    for (int i = 0; i < V; i++) {
      if (disc[i] == -1) {
        SCCUtil(i);
      }
    }
  }

  public static void main(String[] args) {
    TarjanSCC g = new TarjanSCC(5);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(1, 3);
    g.addEdge(3, 4);

    g.SCC();
  }
}

