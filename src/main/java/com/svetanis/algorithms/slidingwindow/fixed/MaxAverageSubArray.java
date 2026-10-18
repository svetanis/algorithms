package com.svetanis.algorithms.slidingwindow.fixed;

// 643. Maximum Average Subarray I -- a version that also returns where the window is
//
// Takes an array of integers (positive and negative) and a length k, 1 <= k <= a.length.
// Returns the first window with the largest average: its first index, its last index, and
// the average.
//
// k is the same for every window, so the largest average belongs to the largest sum: compare
// sums and divide once, at the end. One step of the window changes two numbers -- a[i] enters
// on the right, a[i - k] leaves k places behind it -- so the sum is edited, not rebuilt. The
// strict > keeps the first of several windows with the same sum. A sum past 2^31 - 1 wraps;
// 643's bounds (n <= 10^5, |a[i]| <= 10^4) keep every sum within 10^9.
//
// Time: O(n) -- k additions build the first window, then one edit per remaining index
// Space: O(1) -- the running sum, the best sum and the index where it ends
//
// Siblings: MaxAverageSubArraySubmit is 643 as LeetCode asks it, returning the average only.
// MaxSumSubArraySizeK is the same loop over a List, returning the largest sum.
// MinAverageSubArray is this method turned around, keeping the smallest sum.

public final class MaxAverageSubArray {

  // left and right are the window's first and last index; mean is its average
  public record Window(int left, int right, double mean) {
    @Override
    public String toString() {
      return "%d,%d,%s".formatted(left, right, mean);
    }
  }

  public static Window maxAverage(int[] a, int k) {
    int n = a.length;

    int sum = 0;
    for (int i = 0; i < k; i++) { // BUILD: the first window, a[0..k-1]
      sum += a[i];
    }
    int max = sum;
    int right = k - 1;

    // compute sum of remaining subarrays
    for (int i = k; i < n; i++) {
      sum = sum + a[i] - a[i - k]; // IN a[i], OUT a[i - k]

      if (sum > max) { // RECORD: strictly larger, so the first of equal sums stays
        max = sum;
        right = i;
      }
    }

    int left = right - k + 1;
    double mean = (double) max/k; // DIVIDE: once, in double
    return new Window(left, right, mean);
  }

  public static void main(String[] args) {
    int[] a = { 1, 12, -5, -6, 50, 3 };
    System.out.println(maxAverage(a, 4));
  }
}
