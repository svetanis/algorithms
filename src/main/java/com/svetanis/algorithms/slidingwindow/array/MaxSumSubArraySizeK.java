package com.svetanis.algorithms.slidingwindow.array;

import static com.svetanis.java.base.utils.Arrays.sum;
import static java.lang.Math.max;

// Given an array of positive numbers and a positive number ‘k’, 
// find the maximum sum of any contiguous subarray of size ‘k’.

public final class MaxSumSubArraySizeK {

  public static int maxSum(int[] a, int k) {
    // Time Complexity: O(n)
    // Aux Space: O(1)
    
    int n = a.length;
    if (n < k) {
      return -1;
    }

    int max = sum(a, k);

    int sum = max;
    for (int i = k; i < n; i++) {
      sum += a[i] - a[i - k];
      max = max(max, sum);
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a = { 1, 4, 2, 10, 2, 3, 1, 0, 20 };
    System.out.println(maxSum(a, 4));
  }
}

