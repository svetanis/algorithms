package com.svetanis.algorithms.backtracking.aggregation;

// Is there a non-empty subset with a given sum?
//
// Input: an array a, which may hold negative numbers and zeros, and a sum. Returns true if some
// non-empty subset of a adds up to that sum. No LeetCode problem; the walk visits up to 2^n
// leaves, so n is limited to about 25.
//
// The one idea: one call answers one question -- "can I pick from a[i..] so that the picked
// numbers add up to the sum still to reach?". Each number is either skipped or taken, and a taken
// number is subtracted from the sum still to reach. A branch that finds an answer returns true at
// once, and the caller stops there. The empty subset always has sum 0, so oneIncl records whether
// anything was taken: without it, every array would say true for 0.
//
// Time: O(2^n) -- 2^n leaves at most, each O(1); it stops at the first answer.
// Space: O(n) -- the recursion depth.
//
// Siblings: CountSubSetsGivenSum is the same walk counting every such subset instead of stopping
// at one.

public final class SubSetGivenSum {

  public static boolean isSum(int[] a, int sum) {
    return isSum(a, 0, sum, false);
  }

  private static boolean isSum(int[] a, int i, int sum, boolean oneIncl) {
    if (i >= a.length) { // BASE: every number decided
      return sum == 0 && oneIncl; // RETURN: the sum reached, by a non-empty subset
    }
    // SKIP a[i]
    if (isSum(a, i + 1, sum, oneIncl)) {
      return true; // STOP: found, no need to try taking it
    }
    // TAKE a[i]: less sum left to reach
    return isSum(a, i + 1, sum - a[i], true);
  }

  public static void main(String[] args) {
    System.out.println(isSum(new int[] { 1 }, 0)); // false
    System.out.println(isSum(new int[] { 10, 20 }, 0)); // false
    System.out.println(isSum(new int[] { 0 }, 0)); // true
    System.out.println(isSum(new int[] { -10, 10 }, 0)); // true
    System.out.println(isSum(new int[] { -2, 2, 2, 1, 2, 3 }, 0)); // true
  }
}
