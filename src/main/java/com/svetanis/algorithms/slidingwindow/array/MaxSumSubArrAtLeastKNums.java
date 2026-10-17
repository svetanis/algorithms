package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.max;

// Largest sum subarray with at least k numbers -- no LeetCode number; source unknown

// Given an array, find the subarray 
// (containing at least k numbers)
// which has the largest sum. 
// Takes a (numbers may be negative) and 1 <= k <= a.length; returns
// that largest sum.

// A subarray with at least k numbers is its LAST k numbers, plus
// possibly a block that ends right before them. So for every window of
// exactly k numbers, the best answer ending there is the window alone,
// or the window plus the best sum of a block ending just before it --
// and that second number is exactly what Kadane's running sum holds at
// each index.

// Time: O(n) -- one pass for kadane's array, one for the window
// Space: O(n) -- kadane's array, one entry per index

// Siblings:
//   fixed.MaxSumSubArraySizeK -- exactly k numbers: the window alone.
//   dp.sum.max.subarr.MaxSumSubArrayKadane -- no lower limit on the
//     length: Kadane's running sum alone.

public final class MaxSumSubArrAtLeastKNums {

  public static int maxSum(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(n) -- kadane's array
    int n = a.length;

    // sum of first k elements
    int sum = sum(a, k); // BUILD
    int max = sum;
    // largest sum of a block ending exactly at index i
    int[] kadane = kadane(a);
    for (int i = k; i < n; i++) {
      sum += a[i] - a[i - k]; // IN a[i], OUT a[i - k]
      max = max(max, sum); // RECORD: exactly k numbers
      // include the best block ending at [i - k],
      // right before the window, if it increases overall max
      max = max(max, sum + kadane[i - k]); // RECORD: the window plus that block
    }
    return max;
  }

  // arr[i] is the largest sum of a block that ENDS at index i: it must
  // end there so that it joins the window with no gap.
  public static int[] kadane(int[] a) {
    int[] arr = new int[a.length];
    arr[0] = a[0];
    int sum = a[0];
    for (int i = 1; i < a.length; i++) {
      sum = max(a[i], sum + a[i]); // extend the block ending at i - 1, or start again at i
      arr[i] = sum;
    }
    return arr;
  }

  private static int sum(int[] a, int k) {
    int sum = 0;
    for (int i = 0; i < k; i++) {
      sum += a[i];
    }
    return sum;
  }

  public static void main(String[] args) {

    int[] a1 = { 1, 2, 3, -10, -3 };
    System.out.println(maxSum(a1, 4)); // -4 : 1,2,3,-10

    int[] a2 = { -4, -2, 1, -3 };
    System.out.println(maxSum(a2, 2)); // -1 : -2,1

    int[] a3 = { 1, 1, 1, 1, 1, 1 };
    System.out.println(maxSum(a3, 2)); // 6 : all of it

  }
}
