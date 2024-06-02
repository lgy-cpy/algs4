package org.geeksforgeeks;

import java.math.BigInteger;

/**
 * karatsuba 算法，利用
 */
public class Karatsuba
{
  static Long karatsubaAlgo(String A, String B) {
    // code here
    long a = Long.parseLong(A, 2);
    long b = Long.parseLong(B, 2);
    return karatsuba(a, b);
  }

  private static Long karatsuba(long x, long y)
  {
    if (x <= 1024 && y <= 1024) {
      return x * y;
    }
    int N = Math.max(64 - Long.numberOfLeadingZeros(x), 64 - Long.numberOfLeadingZeros(y));
    N = (N + 1) >> 1; // 获取一半或者一半加一，也可以写作(N / 2) + (N % 2); N >>> 1 + N & 1
    long mask = (1L << N) - 1; // N个1，上面的位数全部是0
    long lowX = x & mask; // 通过按位与，只保留后N位
    long highX = x >>> N; // 通过算术右移，获取去掉后N位的剩余部分
    long lowY = y & mask;
    long highY = y >>> N;

    long a = karatsuba(lowX, lowY);
    long b = karatsuba(highX, highY);
    long c = karatsuba(lowX + highX, lowY + highY) - a - b;

    return (b << (2 * N)) + (c << N) + a;
  }

  public static void main(String[] args) {
    long x = 10000L;
    long y = 10000L;
    System.out.println("Product: " + karatsuba(x, y));
  }

}
