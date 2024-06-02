package org.geeksforgeeks;

/**
 * @author X-XXX
 */
public class GraphMinWalk
{
  static public void main(String[] args)
  {
    GraphMinWalk graphMinWalk = new GraphMinWalk();
    int[][] graph = {{0,1,1,1},{0,0,0,1},
        {0,0,0,1}, {0,0,0,0}};
    int u = 0;
    int v = 3;
    int k = 2;
    System.out.println(graphMinWalk.MinimumWalk(graph, u, v, k));
  }


  public int MinimumWalk(int[][] graph, int u, int v, int k)
  {
    // Code here
    int n = graph.length;
    int[][] aux = new int[n][n];
    return count(graph, aux, u, v, k);
  }

  private int count(int[][] graph, int[][] aux, int u, int v, int k) {
    if (k == 1) {
      return graph[u][v];
    }
    if (aux[u][k-1] != 0) {
      return aux[u][k-1];
    }
    int count = 0;
    for (int i = 0; i < graph[u].length; i++) {
      if (graph[u][i] == 1) {
        count += count(graph, aux, i, v, k-1);
      }
    }
    aux[u][k-1] = count;
    return count;
  }
}
