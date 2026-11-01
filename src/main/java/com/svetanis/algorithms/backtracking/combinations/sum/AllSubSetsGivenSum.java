package com.svetanis.algorithms.backtracking.combinations.sum;

// Print every subset with a given sum (not a LeetCode problem)
//
// Input: an array and a sum (assumed length at most about 20; sums fit an int). Prints each subset
// whose numbers add up to that sum, one per line, each number followed by a space. Returns nothing.
//
// The one idea: take-or-skip, as AllSubSetsPrint. One call decides in[i]; out is a fixed buffer of
// n slots and k is how many of them are filled, so out[0..k-1] is the subset so far. Taking writes
// the number into out[k] and passes k + 1. The sum is checked only at the end, once every number is
// decided, so nothing is cut short -- negative numbers and zeros are handled the same as positive
// ones.
//
// Time: O(n * 2^n) -- 2^n leaves, each adding up to n numbers.
// Space: O(n) -- the buffer and the recursion depth.
//
// Siblings: AllSubSetsPrint is the same walk printing every subset. CombinationSumII returns the
// subsets of positive numbers with a given sum as lists, and prunes.

public final class AllSubSetsGivenSum {
  public static void subset(int[] a, int sum) {
    int n = a.length;
    int[] out = new int[n];
    subset(a, 0, out, 0, sum);
  }

  private static void subset(int[] in, int i, int[] out, int k, int sum) {
    if (i == in.length) { // BASE: every number decided: out[0..k-1] is one subset
      if (sum(out, 0, k - 1) == sum) {
        print(out, 0, k - 1); // PRINT
      }
      return;
    }
    // SKIP in[i]: k is unchanged
    subset(in, i + 1, out, k, sum);
    // TAKE in[i]: write it into the next free slot
    out[k] = in[i];
    subset(in, i + 1, out, k + 1, sum);
  }

  // sum and print of a[start..end], inclusive; print puts a space after each number
  private static int sum(int[] a, int start, int end) {
    int total = 0;
    for (int i = start; i <= end; i++) {
      total += a[i];
    }
    return total;
  }

  private static void print(int[] a, int start, int end) {
    for (int i = start; i <= end; i++) {
      System.out.print(a[i]);
      System.out.print(' ');
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // 4 6, 2 3 5, 1 4 5, 1 3 6, 1 2 3 4, one per line
    subset(new int[] { 1, 2, 3, 4, 5, 6 }, 10);
  }
}
