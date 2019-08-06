package com.svetanis.algorithms.dp.sum.given.subseq;

// Given a set of positive numbers, find the total number  
// of subsets whose sum is equal to a given number ‘S’.

public final class SubSetSumCountRecursive {

  public static int count(int[] a, int sum) {
    int n = a.length;
    return count(a, n - 1, sum);
  }

  private static int count(int[] a, int n, int sum) {
    // Time complexity: (2^n)

    if (sum == 0) {
      return 1;
    }

    if (n < 0 || sum < 0) {
      return 0;
    }

    // 1. include last element
    int incl = count(a, n - 1, sum - a[n]);
    // 2. exclude last element
    int excl = count(a, n - 1, sum);
    return incl + excl;
  }

  public static void main(String[] args) {
    int[] a1 = { 3, 34, 4, 12, 5, 2 };
    System.out.println(count(a1, 9));

    int[] a2 = { 1, 1, 2, 3 };
    System.out.println(count(a2, 4));

    int[] a3 = { 1, 2, 7, 1, 5 };
    System.out.println(count(a3, 9));
  }
}
