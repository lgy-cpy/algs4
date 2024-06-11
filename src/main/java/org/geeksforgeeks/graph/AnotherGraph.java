package org.geeksforgeeks.graph;

/**
 * @author X-XXX
 */
public class AnotherGraph
{
  public int numIslands(char[][] grid) {
    // Code here
    int n = grid.length;
    int m = grid[0].length;
    boolean[][] vis = new boolean[n][m];
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1' && !vis[i][j]) {
          count++;
          dfs(grid, vis, n, m, i, j);
        }
      }
    }
    return count;
  }

  void dfs(char[][] grid, boolean[][] vis,int n, int m, int i, int j)
  {
    vis[i][j] = true;
    for (int k = Math.max(0, i - 1); k <= Math.min(n - 1, i + 1); k++) {
      for (int l = Math.max(0, j - 1); l <= Math.min(m - 1, j + 1); l++) {
        if (!vis[k][l] && grid[k][l] == '1') {
          dfs(grid, vis, n, m, k, l);
        }
      }
    }
  }
}
