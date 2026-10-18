package com.svetanis.algorithms.slidingwindow.fixed;

// 643. Maximum Average Subarray I
//
// Takes nums as a and a length k, 1 <= k <= n (LeetCode guarantees it; a larger k throws
// ArrayIndexOutOfBoundsException). Returns the largest average of k consecutive numbers.
//
// One step of a fixed window changes two numbers: one enters on the right, one leaves k
// behind it, so the sum is edited, not rebuilt. k is the same for every window, so the
// largest average belongs to the largest sum: compare sums, divide once at the end, in
// double. 643's bounds (n <= 10^5, |a[i]| <= 10^4) keep every sum within 10^9, inside int.
//
// Time: O(n) -- k additions build the first window, then one edit per remaining index
// Space: O(1) -- the running sum and the best sum
//
// Siblings: MaxAverageSubArray also returns where the best window is. MaxSumSubArraySizeK
// is the same loop over a List, returning the sum, and returns -1 when k > n.

public final class MaxAverageSubArraySubmit {

  public static double maxAverage(int[] a, int k) {
    int sum = sum(a, 0, k); // BUILD: the first window, a[0..k-1]
    int max = sum;
    for (int i = k; i < a.length; i++) {
      sum = sum + a[i] - a[i - k]; // IN a[i], OUT a[i - k]
      max = Math.max(max, sum); // RECORD
    }
    return max * 1.0 / k; // DIVIDE: once, in double
  }

  // sum of a[start..end-1]
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
