package com.svetanis.algorithms.backtracking.aggregation;

// Count the subsets with a given sum
//
// Input: an array a, which may hold negative numbers and zeros, and a sum. Returns how many
// non-empty subsets of a add up to that sum; a subset is a choice of positions, so equal values
// at two positions make two subsets. No LeetCode problem; the walk visits 2^n leaves, so n is
// limited to about 25.
//
// The one idea: one call answers one question -- "how many ways to pick from a[i..] so that the
// picked numbers add up to the sum still to reach?". Each number is either skipped or taken, and a
// taken number is subtracted from the sum still to reach. The two choices cover every subset
// exactly once, so their counts are added. The count is settled only after every number is
// decided, because a subset that already reaches the sum can still take a 0, or a +4 and a -4,
// and each of those is another subset. The empty subset always has sum 0, so atLeastOneIncl
// records whether anything was taken.
//
// Time: O(2^n) -- every number skipped or taken gives 2^n leaves, each O(1).
// Space: O(n) -- the recursion depth.
//
// Siblings: SubSetGivenSum is the same walk returning true at the first subset found.

public final class CountSubSetsGivenSum {

  public static int count(int[] a, int sum) {
    return count(a, 0, sum, false);
  }

  private static int count(int[] a, int i, int sum, boolean atLeastOneIncl) {
    // BASE: the count is settled only once every number is decided: a subset that already reaches
    // the sum can still take a 0, or a +4 and a -4, and each of those is another subset to count
    if (i == a.length) {
      return sum == 0 && atLeastOneIncl ? 1 : 0; // RETURN: 1 subset found, if it is non-empty
    }

    int excl = count(a, i + 1, sum, atLeastOneIncl); // SKIP a[i]
    int incl = count(a, i + 1, sum - a[i], true); // TAKE a[i]: less sum left to reach
    return excl + incl; // RETURN: every subset is counted once, on one side or the other
  }

  public static void main(String[] args) {
    System.out.println(count(new int[] { 1, 2, 3, 4, 5, 6 }, 10)); // 5
    System.out.println(count(new int[] { 1 }, 0)); // 0
    System.out.println(count(new int[] { 10, 20 }, 0)); // 0
    System.out.println(count(new int[] { 0 }, 0)); // 1
    System.out.println(count(new int[] { -10, 10 }, 0)); // 1
    System.out.println(count(new int[] { -2, 2, 2, 1, 2, 3 }, 0)); // 3
    System.out.println(count(new int[] { -5, -10 }, -15)); // 1
    System.out.println(count(new int[] { 8, -11 }, 8)); // 1
  }
}
