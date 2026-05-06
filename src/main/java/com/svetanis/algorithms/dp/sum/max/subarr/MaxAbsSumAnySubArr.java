package com.svetanis.algorithms.dp.sum.max.subarr;

// 1749. Maximum Absolute Sum of Any Subarray

public final class MaxAbsSumAnySubArr {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int maxAbsSumPrefix(int[] a) {
    int n = a.length;
    int minSum = 0, maxSum = 0, sum = 0;
    for (int i = 0; i < n; i++) {
      sum += a[i];
      minSum = Math.min(minSum, sum);
      maxSum = Math.max(maxSum, sum);
    }
    return maxSum - minSum;
  }

  public static int maxAbsSum(int[] a) {
    int n = a.length;
    int maxSum = 0;
    int minSum = 0;
    int maxAbsSum = 0;
    for (int i = 0; i < n; i++) {
      int curr = a[i];
      maxSum = Math.max(maxSum, 0) + curr;
      minSum = Math.min(minSum, 0) + curr;
      int currMax = Math.max(maxSum, Math.abs(minSum));
      maxAbsSum = Math.max(maxAbsSum, currMax);
    }
    return maxAbsSum;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, -3, 2, 3, -4 };
    System.out.println(maxAbsSum(a1)); // 5

    int[] a2 = { 2, -5, 1, -4, 3, -2 };
    System.out.println(maxAbsSum(a2)); // 8
  }
}