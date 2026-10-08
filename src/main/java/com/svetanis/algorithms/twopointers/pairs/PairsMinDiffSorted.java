package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Math.min;
import static java.util.Arrays.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// All Pairs With the Smallest Difference
//
// Input: an array of n >= 2 distinct integers, in any order.
// Return: every pair {x, y}, x < y, whose difference y - x is the smallest of any pair,
// in ascending order.
//
// The one idea: after sorting, the smallest difference is between neighbours -- a
// number further away is separated by a neighbour that is closer -- so one pass finds
// the smallest neighbour gap and a second lists every neighbour pair with that gap.
//
// Siblings:
//   PairMinDiffSorted -- returns one such pair
//   datastructures.array.MinAbsDiff (LC 1200 Minimum Absolute Difference), in the
//     data-structures repo -- the same question on LeetCode
//
// Time: O(n log n) -- the sort; both passes are O(n).
// Space: O(log n) -- the sort, besides the list. The array is sorted in place: the
// caller's order is lost.

public final class PairsMinDiffSorted {

  public static List<int[]> pairs(int[] a) {
    sort(a);                                     // SORT: the closest pairs are neighbours
    int min = minDiff(a);
    List<int[]> pairs = new ArrayList<>();
    for (int i = 1; i < a.length; i++) {
      if (a[i] - a[i - 1] == min) {
        pairs.add(new int[] { a[i - 1], a[i] }); // FOUND a neighbour pair with the smallest gap
      }
    }
    return pairs;
  }

  // the smallest gap between neighbours of the sorted array
  private static int minDiff(int[] a) {
    int n = a.length;
    int min = a[1] - a[0];
    for (int i = 2; i < n; i++) {
      min = min(min, a[i] - a[i - 1]); // RECORD the smallest gap so far
    }
    return min;
  }

  public static void main(String[] args) {
    int[] a1 = { 10, 50, 12, 100 };
    System.out.println(show(pairs(a1))); // [[10, 12]]

    int[] a2 = { 5, 4, 3, 2 };
    System.out.println(show(pairs(a2))); // [[2, 3], [3, 4], [4, 5]]

    int[] a3 = { 5, 3, 2, 4, 1 };
    System.out.println(show(pairs(a3))); // [[1, 2], [2, 3], [3, 4], [4, 5]]
  }

  // the pairs as text, e.g. [[2, 3], [3, 4]]
  private static String show(List<int[]> pairs) {
    List<String> out = new ArrayList<>();
    for (int[] p : pairs) {
      out.add(Arrays.toString(p));
    }
    return out.toString();
  }
}
