package com.svetanis.algorithms.slidingwindow.array;

import static com.svetanis.algorithms.slidingwindow.array.CountSubArrsProductLessThanK.count;

public final class CountSubArrsSumInGivenRange {

  public static int countInRange(int[] a, int start, int end) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int right = count(a, end);
    int left = count(a, start - 1);
    return right - left;
  }

  public static void main(String[] args) {

    int[] a1 = { 1, 4, 6 };
    System.out.println(countInRange(a1, 3, 8));

    int[] a2 = { 2, 3, 5, 8 };
    System.out.println(countInRange(a2, 4, 13));

  }
}
