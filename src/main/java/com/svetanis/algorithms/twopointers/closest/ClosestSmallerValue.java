package com.svetanis.algorithms.twopointers.closest;

import java.util.Arrays;
import java.util.TreeSet;

// Closest smaller value anywhere in the array
//
// Input: an array of integers.
// Return: for each position i, the largest number in the WHOLE array that is strictly
// smaller than a[i], or -1 when a[i] is the smallest.
//
// The one idea: position does not matter, only value, so put every number in a TreeSet
// once and ask TreeSet.lower(a[i]) -- the largest value strictly below a[i] -- in O(log n).
//
// Siblings -- the same TreeSet lookup:
//   twopointers.closest.ClosestGreaterValue -- higher(): the smallest value strictly above
//   twopointers.closest.CeilingOnRight      -- ceiling(), and only among the numbers to the RIGHT
//   twopointers.closest.ClosestValue        -- the nearer of the two sides
//
// Time: O(n log n) -- build the tree, then n lookups.
// Space: O(n) for the tree.

public final class ClosestSmallerValue {

  public static int[] closest(int[] a) {
    TreeSet<Integer> set = new TreeSet<>();
    for (int x : a) {
      set.add(x);                                 // RECORD every value
    }
    int[] closest = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      Integer lower = set.lower(a[i]);            // FIND the largest value below a[i]
      closest[i] = lower == null ? -1 : lower;    // RECORD it, -1 when there is none
    }
    return closest;
  }

  public static void main(String[] args) {
    int[] a1 = { 10, 5, 11, 6, 20, 12 };
    System.out.println(Arrays.toString(closest(a1))); // [6, -1, 10, 5, 12, 11]

    int[] a2 = { 10, 5, 11, 10, 20, 12 };
    System.out.println(Arrays.toString(closest(a2))); // [5, -1, 10, 5, 12, 11]
  }
}
