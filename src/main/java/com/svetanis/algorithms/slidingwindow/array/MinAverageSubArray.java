package com.svetanis.algorithms.slidingwindow.array;

import com.svetanis.java.base.utils.Arrays;
import com.svetanis.java.base.utils.Triplet;

public class MinAverageSubArray {

  // sliding window method
  public static Triplet<Integer, Integer, Double> minAverage(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    int n = a.length;
    int right = k - 1;

    int sum = Arrays.sum(a, k);
    int min = sum;

    // compute sum of remaining subarrays
    for (int i = k; i < n; i++) {
      sum = sum + a[i] - a[i - k];

      if (sum < min) {
        min = sum;
        right = i;
      }
    }

    int left = right - k + 1;
    double mean = (double) min / k;
    return Triplet.build(left, right, mean);
  }

  public static void main(String[] args) {
    int[] a = { 3, 7, 90, 20, 10, 50, 40 };
    System.out.println(minAverage(a, 3));
  }
}
