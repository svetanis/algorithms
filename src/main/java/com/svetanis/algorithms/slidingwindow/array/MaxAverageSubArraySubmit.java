package com.svetanis.algorithms.slidingwindow.array;

// 643. Maximum Average Subarray I

public final class MaxAverageSubArraySubmit {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static double maxAverage(int[] a, int k) {
    int sum = sum(a, 0, k);
    int max = sum;
    for (int i = k; i < a.length; i++) {
      sum = sum + a[i] - a[i - k];
      max = Math.max(max, sum);
    }
    return max * 1.0 / k;
  }

  public static int sum(int[] a, int start, int end) {
    int sum = 0;
    for (int i = start; i < end; i++) {
      sum += a[i];
    }
    return sum;
  }

  public static void main(String[] args) {
    int[] a = { 1, 12, -5, -6, 50, 3 };
    System.out.println(maxAverage(a, 4)); // 12.75

    int[] a1 = { 5 };
    System.out.println(maxAverage(a1, 1)); // 5.0
  }
}
