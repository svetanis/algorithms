package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.max;

// given an array of integers
// find the length of longest subarray
// whose sum is at most K

public final class LongestSubArrSumAtMostK {

  public static int sum(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int n = a.length;
    int sum = 0;
    int max = 0;
    int count = 0;

    for (int i = 0; i < n; i++) {
      if (sum + a[i] <= k) {
        sum += a[i];
        count++;
      } else {
        sum -= a[i - count];
        count--;
        if (sum + a[i] <= k) {
          sum += a[i];
          count++;
        }
      }
      max = max(max, count);
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 1, 0, 1, 1, 0 };
    System.out.println(sum(a1, 4));
  }
}
