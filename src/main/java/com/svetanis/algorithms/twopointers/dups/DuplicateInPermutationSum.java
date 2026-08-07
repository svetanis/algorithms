package com.svetanis.algorithms.twopointers.dups;

import static com.svetanis.java.base.utils.Arrays.sum;

// given an array of n + 1 integers holding each of 1..n
// exactly once, plus a second copy of one of them
// find the value that appears twice

public final class DuplicateInPermutationSum {
  // Time Complexity: O(n)

  // the contract above is load-bearing: 1..n must each be present, so
  // that sum(a) exceeds sum(1..n) by exactly the repeated value.
  // on { 3, 3, 3, 3, 3 } this returns 5 -- see DuplicateInPlaceMarking.
  public static int duplicate(int[] a) {
    int n = a.length - 1;
    int total = n * (n + 1) / 2;
    int sum = sum(a);
    return sum - total;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 4, 2, 2 };
    System.out.println(duplicate(a1)); // 2

    int[] a2 = { 3, 1, 3, 4, 2 };
    System.out.println(duplicate(a2)); // 3

    int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10 };
    System.out.println(duplicate(a4)); // 7
  }
}
