package test;

import java.util.Arrays;

/**
 * @author X-XXX
 */
public class BitOperation
{
  public static void main(String[] args)
  {
    int a = -16;
    System.out.println(Integer.toBinaryString(a));
    System.out.println(a >>> 1);
    System.out.println(Integer.toBinaryString(a >>> 1));


    int value = 0xF1234567; // Example value
    System.out.println(value);
    int extractedBits = (value >>> 16) & 0xFF;

    System.out.println("Extracted bits: " + extractedBits); // 输出 0xF1
    int[][] Arr = new int[][] {
        {1, 2, 8},
        {7, 5},
        {6, 7, 2, 6}
    };
    int[][][] Arr2 = new int[3][][];
  }
}
