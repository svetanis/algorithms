package com.svetanis.algorithms.twopointers.closest;

import java.util.Arrays;
import java.util.TreeSet;

// Ceiling on the right
//
// Input: an array of integers.
// Return: for each position i, the SMALLEST number to the right of i that is >= a[i] --
// an equal number counts -- or -1 when there is none.
//
// The one idea: walk from the right end, keeping every number already passed in a TreeSet.
// Those are exactly the numbers to the right of i, and TreeSet.ceiling(a[i]) returns the
// smallest of them that is >= a[i] in O(log n).
//
// Siblings -- the same TreeSet lookup:
//   twopointers.closest.ClosestGreaterValue -- higher() over the WHOLE array, not only the right
//   twopointers.closest.ClosestSmallerValue -- lower() over the whole array
//   twopointers.closest.NextGreater         -- the NEAREST larger number on the right, not the
//                                             smallest, with a stack
//
// Time: O(n log n) -- n lookups and inserts in a balanced tree.
// Space: O(n) for the tree.

public final class CeilingOnRight {

  public static int[] ceiling(int[] a) {
    int n = a.length;
    int[] ceiling = new int[n];
    TreeSet<Integer> set = new TreeSet<>();       // the numbers to the right of i
    for (int i = n - 1; i >= 0; i--) {            // START at the right end
      Integer greater = set.ceiling(a[i]);        // FIND the smallest number >= a[i]
      ceiling[i] = greater == null ? -1 : greater; // RECORD it, -1 when there is none
      set.add(a[i]);                              // a[i] is to the right of every later i
    }
    return ceiling;
  }

  public static void main(String[] args) {
    int[] a1 = { 10, 5, 11, 6, 20, 12 };
    System.out.println(Arrays.toString(ceiling(a1))); // [11, 6, 12, 12, -1, -1]

    int[] a2 = { 10, 5, 11, 10, 20, 12 };
    System.out.println(Arrays.toString(ceiling(a2))); // [10, 10, 12, 12, -1, -1]

    int[] a3 = { 50, 20, 200, 100, 30 };
    System.out.println(Arrays.toString(ceiling(a3))); // [100, 30, -1, -1, -1]
  }
}
