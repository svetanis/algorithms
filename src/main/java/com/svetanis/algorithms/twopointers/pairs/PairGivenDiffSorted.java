package com.svetanis.algorithms.twopointers.pairs;

import static java.util.Arrays.sort;

import java.util.Arrays;

// Find a Pair With a Given Difference
//
// Input: an array of integers, in any order, and k >= 0.
// Return: a pair {x, y} of values at two different positions with y - x == k, smaller
// first; {-1, -1} if there is none. With k == 0 and two copies of -1, a real answer looks
// the same as "none"; the source statement would settle what to return.
//
// The one idea: after sorting, both pointers start at the left and move the SAME way.
// If a[right] - a[left] is too small, this a[right] is too small for every a[left] still
// in play, since a larger a[left] only shrinks the difference: move right on. If it is
// too big, this a[left] is too small for every a[right] still in play: move left on.
// Neither move skips a pair, and each pointer only moves forward. The left != right
// test keeps a number from pairing with itself when k == 0.
//
// Siblings:
//   CountPairsGivenDiffBinary, CountPairsGivenDiffHashing (LC 532) -- count every pair
//     of values with difference k instead of returning one
//   PairGivenSumSorted -- converging pointers, for a sum
//
// Time: O(n log n) -- the sort; the walk is O(n).
// Space: O(log n) -- the sort. The array is sorted in place: the caller's order is lost.

public final class PairGivenDiffSorted {

  public static int[] pair(int[] a, int k) {
    int n = a.length;
    int left = 0;                               // START: both pointers at the small end
    int right = 1;
    sort(a);                                    // SORT
    while (left < n && right < n) {             // STOP when either pointer runs off the end
      int diff = a[right] - a[left];            // COMPARE
      if (left != right && diff == k) {
        return new int[] { a[left], a[right] }; // FOUND: two different positions
      } else if (diff < k) {
        right++;                                // MOVE right: a larger a[right] grows the difference
      } else {
        left++;                                 // MOVE left: a larger a[left] shrinks the difference
      }
    }
    return new int[] { -1, -1 };                // RETURN: no pair
  }

  public static void main(String[] args) {
    int[] a = { 40, 30, 1, 8, 100 };
    System.out.println(Arrays.toString(pair(a, 60))); // [40, 100]
  }
}
