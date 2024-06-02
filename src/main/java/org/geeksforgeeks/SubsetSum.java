package org.geeksforgeeks;

/**
 * @author X-XXX
 */
public class SubsetSum
{
  static public void main(String[] args)
  {
    int arr[] = {3, 34, 4, 12, 5, 2};
    SubsetSum.isSubsetSum(6, arr, 9);
  }

  static Boolean isSubsetSum(int N, int arr[], int sum){
    // code here
    boolean[][] aux = new boolean[sum+1][N+1];
    for (int i = 1; i <= sum; i++) {
      int j = 1;
      for (; j <= N; j++) {
        if (arr[j-1] == i) {
          break;
        } else if (arr[j-1] < i) {
          if (aux[i-arr[j-1]][j-1]) {
            break;
          }
        }
      }
      for (int k = j; k <= N; k++) {
        aux[i][k] = true;
      }
    }
    return aux[sum][N];
  }
}
