package com.svetanis.algorithms.slidingwindow.fixed;

// Minimum average subarray of length k -- no LeetCode number; source unknown
//
// Given an array of integers (positive and negative) and a length k, 1 <= k <= a.length,
// find the subarray of length k with the smallest average. Returns the first such window:
// its first index, its last index, and the average.
//
// Subarray of given length has min average if it has min sum: k is the same for every
// window, so compare sums and divide once, at the end. One step of the window changes two
// numbers -- a[i] enters on the right, a[i - k] leaves k places behind it -- so the sum is
// edited, not rebuilt. The strict < keeps the first of several windows with the same sum.
// A sum past the int range wraps.
//
// Time: O(n) -- k additions build the first window, then one edit per remaining index
// Space: O(1) -- the running sum, the smallest sum and the index where it ends
//
// Sibling: MaxAverageSubArray is this method turned around, keeping the largest sum.

public final class MinAverageSubArray {

  // left and right are the window's first and last index; mean is its average
  public record Window(int left, int right, double mean) {
    @Override
    public String toString() {
      return "%d,%d,%s".formatted(left, right, mean);
    }
  }

  // sliding window method
  public static Window minAverage(int[] a, int k) {
    int n = a.length;
    int right = k - 1;

    int sum = 0;
    for (int i = 0; i < k; i++) { // BUILD: the first window, a[0..k-1]
      sum += a[i];
    }
    int min = sum;

    // compute sum of remaining subarrays
    for (int i = k; i < n; i++) {
      sum = sum + a[i] - a[i - k]; // IN a[i], OUT a[i - k]

      if (sum < min) { // RECORD: strictly smaller, so the first of equal sums stays
        min = sum;
        right = i;
      }
    }

    int left = right - k + 1;
    double mean = (double) min / k; // DIVIDE: once, in double
    return new Window(left, right, mean);
  }

  public static void main(String[] args) {
    int[] a = { 3, 7, 90, 20, 10, 50, 40 };
    System.out.println(minAverage(a, 3));
  }
}
