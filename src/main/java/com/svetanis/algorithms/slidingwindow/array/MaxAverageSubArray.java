package com.svetanis.algorithms.slidingwindow.array;

import com.svetanis.java.base.utils.Arrays;
import com.svetanis.java.base.utils.Triplet;

public final class MaxAverageSubArray {

  public static Triplet<Integer, Integer, Double> maxAverage(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int n = a.length;

    int sum = Arrays.sum(a, k);
    int max = sum;
    int right = k - 1;

    // compute sum of remaining subarrays
    for (int i = k; i < n; i++) {
      sum = sum + a[i] - a[i - k];

      if (sum > max) {
        max = sum;
        right = i;
      }
    }

    int left = right - k + 1;
    double mean = (double) max/k;
    return Triplet.build(left, right, mean);
  }

  public static void main(String[] args) {
    int[] a = { 1, 12, -5, -6, 50, 3 };
    System.out.println(maxAverage(a, 4));
  }
}
