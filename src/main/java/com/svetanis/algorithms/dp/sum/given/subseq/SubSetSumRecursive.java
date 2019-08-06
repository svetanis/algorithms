package com.svetanis.algorithms.dp.sum.given.subseq;

// Given a set of non-negative integers, and a value sum, 
// determine if there is a subset of the given set with sum equal to given sum.

public final class SubSetSumRecursive {

  public static boolean isSum(int[] a, int sum) {
    int n = a.length;
    return isSum(a, n - 1, sum);
  }

  private static boolean isSum(int[] a, int n, int sum) {
    // Time complexity: (2^n)

    if (sum == 0) {
      return true;
    }

    if (n < 0 || sum < 0) {
      return false;
    }

    // 1. include last element
    boolean incl = isSum(a, n - 1, sum - a[n]);
    // 2. exclude last element
    boolean excl = isSum(a, n - 1, sum);
    return incl || excl;
  }

  public static void main(String[] args) {
    int[] a1 = { 3, 34, 4, 12, 5, 2 };
    System.out.println(isSum(a1, 9));

    int[] a2 = { 1, 2, 3, 7 };
    System.out.println(isSum(a2, 6));

    int[] a3 = { 1, 2, 7, 1, 5 };
    System.out.println(isSum(a3, 10));

    int[] a4 = { 1, 3, 4, 8 };
    System.out.println(isSum(a4, 6));

  }
}
