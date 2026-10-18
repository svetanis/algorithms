package com.svetanis.algorithms.slidingwindow.fixed;

import static java.lang.Math.max;

// Maximum product of a subarray of size k -- no LeetCode number; source unknown
//
// Takes an array a and a window size k, 1 <= k <= a.length. Returns the largest product of
// k consecutive elements.
//
// The window's product is edited, not rebuilt: divide out the number leaving, multiply in
// the number entering. The division is exact, because the number leaving is one of the
// factors of the product. Assumed: no element is 0 (the examples are all positive). A zero
// that has to leave the window is divided by and throws ArithmeticException. A product past
// 2^31 - 1 wraps, and after that the division is no longer exact.
//
// Time: O(n) -- k multiplications build the first window, then one division and one
//   multiplication per step
// Space: O(1) -- the product and the best product
//
// Related: ProductSumSubArrSizeK slides the same product and adds up every window's
// product instead of keeping the largest.

public final class MaxProductSubarraySizeK {

  public static int maxProduct(int[] a, int k) {
    int n = a.length;
    int max = 1;
    for (int i = 0; i < k; i++) {
      max *= a[i]; // BUILD: the first window's product, a[0..k-1]
    }
    int prev = max;

    for (int i = 0; i < n - k; i++) { // i is the index leaving
      int current = (prev / a[i]) * a[i + k]; // OUT a[i] by division, IN a[i + k]
      max = max(max, current); // RECORD
      prev = current;
    }
    return max;
  }

  public static void main(String[] args) {
    int k = 6;
    int[] a1 = { 1, 5, 9, 8, 2, 4, 1, 8, 1, 2 };
    System.out.println(maxProduct(a1, k));

    k = 4;
    int[] a2 = { 1, 5, 9, 8, 2, 4, 1, 8, 1, 2 };
    System.out.println(maxProduct(a2, k));

    k = 3;
    int[] a3 = { 2, 5, 8, 1, 1, 3 };
    System.out.println(maxProduct(a3, k));
  }
}