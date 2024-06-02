package org.geeksforgeeks;

/**
 * @author X-XXX
 */
public class SequencePossibility
{
  static public void main(String[] args)
  {
    System.out.println(numberSequence(5, 2));
  }

  static int numberSequence(int m, int n)
  {
    // code here
    if (n == 1) {
      return m;
    }
    int[] max = new int[n];
    int count = n;
    int cur = m;
    while (count > 0) {
      max[count-1] = cur;
      cur /= 2;
      count--;
    }
    if (max[0] < 1)
      return 0;
    int[][] aux = new int[m][n];
    int sum = 0;
    for (int i = 1; i <= max[0]; i++) {
      sum += count(max, aux, i, 1);
    }
    return sum;

  }

  static int count(int[] max, int[][] aux, int previous, int index) {
    if (aux[previous-1][index] == 0) {
      if (index == max.length - 1) {
        aux[previous-1][index] = max[index] - previous*2 + 1;
        return aux[previous-1][index];
      } else {
        int ct = 0;
        int nextIndex = index+1;
        for (int current = previous*2; current <= max[index]; current++) {
          ct += count(max, aux, current, nextIndex);
        }
        aux[previous-1][index] = ct;
        return ct;
      }
    } else {
      return aux[previous-1][index];
    }
  }
}
