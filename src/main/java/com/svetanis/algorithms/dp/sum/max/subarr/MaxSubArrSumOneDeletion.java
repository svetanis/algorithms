package com.svetanis.algorithms.dp.sum.max.subarr;

// 1186. Maximum Subarray Sum with One Deletion

public final class MaxSubArrSumOneDeletion {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static int maxSum(int[] a) {
    int n = a.length;
    int maxSum = a[0];

    int[] left = new int[n];
    left[0] = Math.max(a[0], 0);
    for (int i = 1; i < n; i++) {
      left[i] = Math.max(left[i - 1], 0) + a[i];
      maxSum = Math.max(maxSum, left[i]);
    }
    int[] right = new int[n];
    right[n - 1] = Math.max(a[n - 1], 0);
    for (int i = n - 2; i >= 0; i--) {
      right[i] = Math.max(right[i + 1], 0) + a[i];
    }
    for (int i = 1; i < n - 1; i++) {
      int maxOneDel = left[i - 1] + right[i + 1];
      maxSum = Math.max(maxSum, maxOneDel);
    }
    return maxSum;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, -2, 0, 3 };
    System.out.println(maxSum(a1)); // 4

    int[] a2 = { 1, -2, -2, 3 };
    System.out.println(maxSum(a2)); // 3

    int[] a3 = { -1, -1, -1, -1 };
    System.out.println(maxSum(a3)); // -1
  }
}