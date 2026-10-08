package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.util.Arrays.sort;

// Smallest Difference Between Two Arrays
//
// Input: two non-empty arrays of integers, in any order.
// Return: the smallest |x - y| with x from a and y from b.
//
// The one idea: sort both and walk them together, like a merge. When a[i] < b[j],
// every b after b[j] is even further above a[i], so pairing a[i] with any of them
// cannot beat the pair just compared: a[i] is done, move i. Otherwise b[j] is done by
// the mirror argument, move j. Each step finishes one number.
//
// Siblings:
//   PairMinDiffSorted -- both numbers from one array
//   PairSumClosestToXTwoSorted -- one number from each sorted array, sum closest to x
//
// Time: O(n log n + m log m) -- the sorts; the walk is O(n + m).
// Space: O(log n + log m) -- the sorts. Both arrays are sorted in place: the caller's
// order is lost.

public final class PairMinDiffTwoUnsorted {

  public static int minDiff(int[] a, int[] b) {
    int n = a.length;
    int m = b.length;
    sort(a);                       // SORT both
    sort(b);
    int i = 0;
    int j = 0;
    int min = MAX_VALUE;
    while (i < n && j < m) {       // STOP when either array runs out
      int diff = abs(a[i] - b[j]); // COMPARE
      min = min(min, diff);        // RECORD the smallest so far
      if (a[i] < b[j]) {
        i++;                       // MOVE i: every later b is further from a[i]
      } else {
        j++;                       // MOVE j: every later a is further from b[j]
      }
    }
    return min;
  }

  public static void main(String[] args) {
    int[] a = { 1, 3, 15, 11, 2 };
    int[] b = { 23, 127, 235, 19, 8 };
    System.out.println(minDiff(a, b)); // 3

    int[] c = { 10, 5, 40 };
    int[] d = { 50, 90, 80 };
    System.out.println(minDiff(c, d)); // 10

    int[] e = { 1, 2, 11, 5 };
    int[] f = { 4, 12, 19, 23, 127, 235 };
    System.out.println(minDiff(e, f)); // 1
  }
}
