package com.svetanis.algorithms.dp.lis.variations;

import static java.lang.Math.max;

// Given a number sequence, find the length 
// of its Longest Alternating Subsequence (LAS). 
// A subsequence is considered alternating 
// if its elements are in alternating order.

public final class LongestAlternatingSubSeqLenRecursive {

  public static int las(int[] a) {
    // Time Complexity : O(n^2)
    // Auxiliary space: O(n)
    int asc = las(a, 1, true);
    int des = las(a, 1, false);
    return 1 + max(asc, des);
  }

  private static int las(int[] a, int start, boolean asc) {
    int n = a.length;
    int max = 0;
    for (int i = start; i < n; i++) {
      if (asc && a[i - 1] < a[i]) {
        max = max(max, 1 + las(a, i + 1, !asc));
      } else if (!asc && a[i - 1] > a[i]) {
        max = max(max, 1 + las(a, i + 1, !asc));
      } else {
        max = max(max, las(a, i + 1, asc));
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 3, 4 };
    System.out.println(las(a1));

    int[] a2 = { 3, 2, 1, 4 };
    System.out.println(las(a2));

    int[] a3 = { 1, 3, 2, 4 };
    System.out.println(las(a3));
  }
}
