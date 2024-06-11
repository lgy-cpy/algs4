package org.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author X-XXX
 */
public class StringCircle
{
  static int isCircle(int N, String A[])
  {
    // code here
    int a = 'a';
    int[] from  = new int[26]; // 入度
    int[] to = new int[26]; // 出度
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < 26; i++) {
      graph.add(new ArrayList<>());
    }
    for (String str : A) {
      int begin = str.charAt(0) - 'a';
      int end = str.charAt(str.length() - 1) - 'a';
      from[begin]++;
      to[end]++;
      graph.get(begin).add(end);
    }
    for (int i = 0; i < 26; i++) {
      if (from[i] != to[i]) { // 入度不等于出度的点，没有circle
        return 0;
      }
    }
    if (isConnected(from, to, graph) && isConnected(to, from, toTranspose(graph))) {
      return 1;
    }
    return 0;
  }

  static boolean isConnected(int[] from, int[] to, List<List<Integer>> graph) {
    boolean[] visited = new boolean[graph.size()];
    int count = 0;
    for (int i = 0; i < graph.size(); i++) {
      if (from[i] > 0 || to[i] > 0) { // letter appears in words
        if (!visited[i]) {
          if (count > 0) { // 不是第一个component, 有不连通的部分
            return false;
          } else {
            dfs(i, visited, graph);
          }
          count++;
        }
      }
    }
    return true;
  }

  static void dfs(int v, boolean[] visited, List<List<Integer>> graph) {
    visited[v] = true;
    for (int i : graph.get(v)) {
      if (!visited[i]) {
        dfs(i, visited, graph);
      }
    }
  }

  static List<List<Integer>> toTranspose(List<List<Integer>> graph) {
    List<List<Integer>> tr = new ArrayList<>();
    for (int i = 0; i < graph.size(); i++) {
      tr.add(new ArrayList<>());
    }
    for (int i = 0; i < graph.size(); i++) {
      for (int j : graph.get(i)) {
        tr.get(j).add(i);
      }
    }
    return tr;
  }

  public static void main(String[] args) {
    String A[] = {"addb", "aeeb", "ba", "buia"};
    System.out.println(isCircle(5, A));
  }
}
