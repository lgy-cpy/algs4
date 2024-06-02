package org.geeksforgeeks;


/**
 * @author X-XXX
 */
public class SquareSum
{
  static public void main(String[] args)
  {
    SquareSum squareSum = new SquareSum();
    squareSum.killinSpree(10);
  }


  long killinSpree(long n)
  {
    // Code Here
    long max = (long) Math.ceil(Math.sqrt(n));
    long min = 1;
    while (min < max) {
      if (max - min == 1) {
        break;
      }
      long mid = (max + min) / 2;
      long sum = squareSum(mid);
      if (sum > n) {
        max = mid;
      } else if (sum < n) {
        min = mid;
      } else {
        return mid;
      }
    }
    return min;
  }

  long squareSum(long n) {
    return n * (n + 1) * (2 * n + 1) / 6;
  }
}
