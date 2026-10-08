package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static java.util.Arrays.sort;

import java.util.Arrays;

// Pair With the Smallest Difference
//
// Input: an array of n >= 2 integers, in any order.
// Return: a pair {x, y}, x <= y, of values at two different positions with the
// smallest difference; the first such pair in sorted order.
//
// The one idea: after sorting, the closest partner of any number is one of its two
// neighbours -- a number further away is separated from it by a neighbour that is at
// least as close -- so only the n - 1 neighbour pairs need comparing.
//
// Siblings:
//   PairsMinDiffSorted -- every pair with the smallest difference
//   PairMinDiffTwoUnsorted -- one number from each of two arrays
//
// Time: O(n log n) -- the sort; the scan is O(n).
// Space: O(log n) -- the sort. The array is sorted in place: the caller's order is lost.

public final class PairMinDiffSorted {

  public static int[] minDiff(int[] a) {
    int n = a.length;
    int min = MAX_VALUE;
    int first = -1;
    int second = -1;
    sort(a);                           // SORT: the closest partner is a neighbour
    for (int i = 0; i < n - 1; i++) {
      int diff = abs(a[i] - a[i + 1]); // COMPARE neighbours only
      if (diff < min) {
        min = diff;                    // RECORD the closest pair so far
        first = i;
        second = i + 1;
      }
    }
    return new int[] { a[first], a[second] };
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 5, 3, 19, 18, 25 };
    System.out.println(Arrays.toString(minDiff(a1))); // [18, 19]

    int[] a2 = { 30, 5, 20, 9 };
    System.out.println(Arrays.toString(minDiff(a2))); // [5, 9]

    int[] a3 = { 1, 19, -4, 31, 38, 25, 100 };
    System.out.println(Arrays.toString(minDiff(a3))); // [-4, 1]
  }
}
