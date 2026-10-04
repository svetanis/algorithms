package com.svetanis.algorithms.backtracking.combinations.sum;

import static com.svetanis.java.base.utils.Arrays.sum;
import static com.svetanis.java.base.utils.Print.print;

// Print every subset with a given sum
//
// Given an array and a sum, print each subset whose numbers add up to that sum, one per line.
// Nothing is returned.
//
// Take-or-skip, as AllSubSetsPrint: decide each number in turn, first skipping it, then taking it.
// out is a fixed buffer of n slots and k is how many of them are filled; taking writes the number
// into out[k] and passes k + 1. The sum is checked only at the end, once every number is decided,
// so nothing is cut short -- negative numbers and zeros are handled the same as positive ones.

public final class AllSubSetsGivenSum {
  // Time Complexity: O(n * 2^n), 2^n leaves, each adding up to n numbers
  // Space Complexity: O(n), the buffer and the recursion depth

  public static void subset(int[] a, int sum) {
    int n = a.length;
    int[] out = new int[n];
    subset(a, 0, out, 0, sum);
  }

  private static void subset(int[] in, int i, int[] out, int k, int sum) {
    if (i == in.length) { // every number decided: out[0..k-1] is one subset
      if (sum(out, 0, k - 1) == sum) {
        print(out, 0, k - 1);
      }
      return;
    }
    // skip in[i]: k is unchanged
    subset(in, i + 1, out, k, sum);
    // take in[i]: write it into the next free slot
    out[k] = in[i];
    subset(in, i + 1, out, k + 1, sum);
  }

  public static void main(String[] args) {
    // 4 6, 2 3 5, 1 4 5, 1 3 6, 1 2 3 4, one per line
    subset(new int[] { 1, 2, 3, 4, 5, 6 }, 10);
  }
}
