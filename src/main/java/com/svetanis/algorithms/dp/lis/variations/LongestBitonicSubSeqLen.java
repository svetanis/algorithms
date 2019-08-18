package com.svetanis.algorithms.dp.lis.variations;

import static java.lang.Math.max;
import static java.util.Arrays.fill;

// Given a number sequence, find the length
// of its Longest Bitonic Subsequence (LBS). 
// A subsequence is considered bitonic if 
// it is monotonically increasing and 
// then monotonically decreasing.

// lbs() returns the length of the 
// Longest Bitonic Subsequence in arr[] of size n.
// the function mainly creates two temporary arrays 
// lis[] and lds[] and return the maximum lis[i] + lds[i] - 1

// lis[i] - Longest Increasing SubSequence ending with a[i]
// lis[i] stores the length of the
// Longest Increasing SubSeq ending with a[i]

// lds[i] - Longest Decreasing SubSequence starting with a[i]:
// lds[i] stores the length of the
// Longest Decreasing SubSeq starting from a[i]

public final class LongestBitonicSubSeqLen {

  public static int lbs(int[] a) {
    // Time Complexity : O(n^2)
    // Auxiliary space: O(n)

    int[] lis = lis(a);
    int[] lds = lds(a);
    return getMax(lis, lds);
  }

  private static int getMax(int[] lis, int[] lds) {
    int n = lis.length;

    // return the max value of LIS[i] + LDS[i] - 1
    int max = lis[0] + lds[0] - 1;
    for (int i = 1; i < n; ++i) {
      max = max(max, lis[i] + lds[i] - 1);
    }
    return max;
  }

  private static int[] lds(int[] a) {
    int n = a.length;
    int[] lds = new int[n];
    fill(lds, 1);

    // compute LDS values from right to left
    for (int i = n - 2; i >= 0; --i) {
      for (int j = n - 1; j > i; --j) {
        if (a[i] > a[j]) {
          lds[i] = max(lds[i], lds[j] + 1);
        }
      }
    }
    return lds;
  }

  private static int[] lis(int[] a) {
    int n = a.length;
    int[] lis = new int[n];
    fill(lis, 1);

    // compute LIS values from left to right
    for (int i = 1; i < n; ++i) {
      for (int j = 0; j < i; ++j) {
        if (a[i] > a[j]) {
          lis[i] = max(lis[i], lis[j] + 1);
        }
      }
    }
    return lis;
  }

  public static void main(String[] args) {
    int[] a = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
    System.out.println("Max length size: " + lbs(a));
  }
}
