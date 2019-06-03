package com.svetanis.algorithms.dp.sum.given.subseq;

// Given a set of non-negative integers, and a value sum, 
// determine if there is a subset of the given set with sum equal to given sum.

public final class GivenSumSubSeqRecursive {

  public static boolean isSum(int[] a, int k) {
    int n = a.length;
    return isSum(a, n, k);
  }

  private static boolean isSum(int[] a, int n, int k) {
    // Time complexity: (2^n)

    if (k == 0) {
      return true;
    }

    if (n == 0 && k != 0) {
      return false;
    }

    if (a[n - 1] > k) {
      return isSum(a, n - 1, k);
    }

    // 1. include last element
    boolean incl = isSum(a, n - 1, k - a[n - 1]);
    // 2. exclude last element
    boolean excl = isSum(a, n - 1, k);
    return incl || excl;
  }

  public static void main(String[] args) {
    int[] a = { 3, 34, 4, 12, 5, 2 };
    System.out.println(isSum(a, 9));
  }
}
