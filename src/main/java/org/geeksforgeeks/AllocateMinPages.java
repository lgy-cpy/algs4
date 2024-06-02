package org.geeksforgeeks;

import java.util.stream.IntStream;

/**
 * @author X-XXX
 */
public class AllocateMinPages
{
  public static void main(String[] args)
  {
    int[]A = {12, 34, 67, 90};
    findPages(A, A.length, 2);
  }

  public static int findPages(int[]A,int N,int M)
  {
    //Your code here
    if (M > N)
      return -1;
    int max = 0;
    int min = 0;
    for (int a : A) {
      max += a;
      if (a > min)
        min = a;
    }
    while (max > min) {
      int median = (max + min) / 2;
      if (isPossible(A, M, median)) {
        max = median;
      } else {
        min = median + 1;
      }
    }
    return max;
  }

  private static boolean isPossible(int[]A, int count, int max)
  {
    int sum = A[0];
    int remain = count - 1;
    for (int i = 1; i < A.length; i++) {
      if (sum + A[i] > max) {
        if (--remain < 0)
          return false;
        sum = A[i];
      } else {
        sum += A[i];
      }
    }
    return true;
  }
}
