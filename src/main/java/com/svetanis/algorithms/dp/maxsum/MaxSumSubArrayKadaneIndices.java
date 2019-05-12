package com.svetanis.algorithms.dp.maxsum;

import com.svetanis.java.base.utils.Triplet;

public final class MaxSumSubArrayKadaneIndices {

  public static Triplet<Integer, Integer, Integer> kadane(int[] a) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    int n = a.length;
    int index = -1;
    int left = -1;
    int right = -1;
    int max = 0;
    int sum = 0;

    for (int i = 0; i < n; ++i) {
      sum += a[i];
      if (sum < 0) {
        sum = 0;
        index = i;
      } else if (max < sum) {
        max = sum;
        left = index + 1;
        right = i;
      }
    }
    return Triplet.build(left, right, max);
  }

  public static void main(String[] args) {
    int[] array2 = { 904, 40, 523, 12, -335, -385, -124, 481, -31 };
    System.out.println(kadane(array2));

    int[] array4 = { -2, -3, 4, -1, -2, 1, 5, -3 };
    System.out.println(kadane(array4));

    int[] array6 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
    System.out.println(kadane(array6));

    int[] array5 = { -1, 4, -2, 5, -5, 2, -20, 6 };
    System.out.println(kadane(array5));
  }
}