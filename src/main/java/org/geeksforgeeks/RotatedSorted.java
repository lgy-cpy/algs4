package org.geeksforgeeks;

import java.util.Arrays;

/**
 * @author X-XXX
 */
public class RotatedSorted
{
  static int Search(int array[], int target)
  {
    // code here
    int rotationPpint = findRotatedIndex(array);
    int found = Arrays.binarySearch(array, 0, rotationPpint, target);
    if (found < 0) {
      found = Arrays.binarySearch(array, rotationPpint, array.length, target);
      return found < 0 ? -1 : found;
    } else {
      return found;
    }
  }

  static int findRotatedIndex(int array[]) {
    int left = 0, right = array.length - 1;
    while (right - left > 1) {
      int mid = (left + right) / 2;
      if (array[mid] < array[left]) {
        right = mid;
      } else {
        left = mid;
      }
    }
    return right;
  }


  static public void main(String[] args) {
    int A[] = {5,6,7,8,9,10,1,2,3};
    System.out.println(RotatedSorted.Search(A, 10));
  }
}
