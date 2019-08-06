package com.svetanis.algorithms.dp.sum.given.subseq;

import static java.lang.Math.abs;
import static java.lang.Math.min;

// Given a set of positive numbers, partition the set into two
// subsets with a minimum difference between their subset sums.

public final class SubSetSumMinDiffRecursive {

  // Time complexity: 2^n
  // Space Complexity: O(n)
  
  public static int minDiff(int[] a) {
    return minDiff(a, 0, 0, 0);
  }

  private static int minDiff(int[] a, int i, int sum1, int sum2) {

    int n = a.length;

    // base cases
    if (i == n) {
      return abs(sum1 - sum2);
    }

    // 1. include element in first subset
    int incl = minDiff(a, i + 1, sum1 + a[i], sum2);

    // 2. exclude element from first subset
    int excl = minDiff(a, i + 1, sum1, sum2 + a[i]);

    return min(incl, excl);
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 3, 9 };
    System.out.println(minDiff(a1)); // 3

    int[] a2 = { 1, 2, 7, 1, 5 };
    System.out.println(minDiff(a2)); // 0

    int[] a3 = { 1, 3, 100, 4 };
    System.out.println(minDiff(a3)); // 92

  }
}
