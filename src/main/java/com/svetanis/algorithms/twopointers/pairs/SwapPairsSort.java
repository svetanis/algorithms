package com.svetanis.algorithms.twopointers.pairs;

import static java.util.Arrays.sort;

import java.util.Arrays;

// Sum Swap -- sorting and two pointers
//
// Input: two non-empty arrays of integers.
// Return: a pair {x, y}, x from a and y from b, such that swapping x and y leaves the two
// arrays with equal sums; {-1, -1} if there is none.
// Convention the code follows: when the sums are already equal it returns {-1, -1},
// even when the arrays share a value whose swap keeps them equal. {-1, -1} is also what
// a real swap of -1 for -1 looks like. The source statement would settle both.
//
// The one idea: swapping x for y moves x - y from a to b, so the sums meet when x - y is
// half their difference, (sum(a) - sum(b)) / 2 -- a fixed target, and no target at all
// when the difference is odd. Sort both and walk them the same way: if a[i] - b[j] is
// below the target, a[i] is too small for every b still in play, since a larger b only
// lowers it: move i. If it is above, b[j] is too small for every a still in play: move j.
//
// Siblings:
//   SwapPairsHashing -- a set of b looked up from each value of a, O(n + m)
//   search.binary.FairCandySwap (LC 888) -- the same set lookup on LeetCode, where an
//     answer is guaranteed
//
// Time: O(n log n + m log m) -- the sorts; the walk is O(n + m).
// Space: O(log n + log m) -- the sorts. Both arrays are sorted in place: the caller's
// order is lost.

public final class SwapPairsSort {

  public static int[] swap(int[] a, int[] b) {
    sort(a);                               // SORT both
    sort(b);
    int diff = diff(a, b);
    if (diff == 0) {
      return new int[] { -1, -1 };         // RETURN: an odd difference, or equal sums
    }
    int i = 0;
    int j = 0;
    while (i < a.length && j < b.length) { // STOP when either array runs out
      int d = a[i] - b[j];                 // COMPARE with the target
      if (d == diff) {
        return new int[] { a[i], b[j] };   // FOUND
      } else if (d < diff) {
        i++;                               // MOVE i: only a larger a[i] can raise a[i] - b[j]
      } else {
        j++;                               // MOVE j: only a larger b[j] can lower a[i] - b[j]
      }
    }
    return new int[] { -1, -1 };           // RETURN: no swap works
  }

  // the target x - y = (sum(a) - sum(b)) / 2; 0 when the difference is odd
  private static int diff(int[] a, int[] b) {
    int sum1 = 0;
    for (int x : a) {
      sum1 += x;
    }
    int sum2 = 0;
    for (int y : b) {
      sum2 += y;
    }
    if ((sum1 - sum2) % 2 != 0) {
      return 0; // an odd difference has no integer half
    } else {
      return (sum1 - sum2) / 2;
    }
  }

  public static void main(String[] args) {
    int[] a = { 4, 1, 2, 1, 1, 2 };
    int[] b = { 3, 6, 3, 3 };
    System.out.println(Arrays.toString(swap(a, b))); // [1, 3]

    int[] c = { 5, 7, 4, 6 };
    int[] d = { 1, 2, 3, 8 };
    System.out.println(Arrays.toString(swap(c, d))); // [5, 1]

    int[] e = { 4, 1, 2, 1, 1, 2 };
    int[] f = { 1, 6, 3, 3 };
    System.out.println(Arrays.toString(swap(e, f))); // [2, 3]
  }
}
