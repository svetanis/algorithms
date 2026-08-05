package com.svetanis.algorithms.dp.sum.given.subseq;

// Given a set of non-negative numbers, find the total number
// of subsets whose sum is equal to a given number ‘S’.

// zeros are allowed, and they matter: a zero can be taken or left
// without changing the sum, so every zero doubles the count.
// that is why reaching sum == 0 is not on its own an answer --
// the remaining elements still have to be walked to the end.

public final class SubSetSumCountRecursive {

  public static int count(int[] a, int sum) {
    int n = a.length;
    return count(a, n - 1, sum);
  }

  private static int count(int[] a, int n, int sum) {
    // Time complexity: (2^n)

    if (sum < 0) {
      return 0;
    }

    // only an exhausted set decides: one way if the sum landed on 0,
    // none otherwise. checking sum == 0 first would stop early and
    // miss the choices still available on any remaining zeros.
    if (n < 0) {
      return sum == 0 ? 1 : 0;
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

    // {} and {0} both sum to 0
    int[] a4 = { 2, 0, 1 };
    System.out.println(count(a4, 0)); // 2
  }
}
