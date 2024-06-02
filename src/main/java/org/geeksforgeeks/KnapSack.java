package org.geeksforgeeks;

/**
 * 经典的背包问题，使用动态规划来解答。
 * 动态规划的实质在于找出初始状态和结束状态以及相关的变量，然后状态之间的变化。
 *
 */
public class KnapSack
{
  //Function to return max value that can be put in knapsack of capacity W.
  static int knapSack(int W, int wt[], int val[], int n)
  {
    //背包问题中状态的变化主要是两个变量(i=从前i个元素中进行选择) (j=包最大的容积)
    // A[i][j] = if 不选第i个元素，A[i-1][j]
    // A[i][j] = if 选了第i个元素(前提是可以选择wt[i] <= j) A[i-1][j-wt[i]] + val[i]
    int[][] dp = new int[n + 1][W + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= W; j++) {
        if (wt[i-1] <= j) { // 第i个元素存在数组里是wt[i-1]
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wt[i]] + val[i]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[n][W];
  }
}
