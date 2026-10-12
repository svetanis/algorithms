package com.svetanis.algorithms.twopointers.closest;

import java.util.Arrays;
import java.util.TreeSet;

// Closest greater value anywhere in the array
//
// Input: an array of integers.
// Return: for each position i, the smallest number in the WHOLE array that is strictly
// greater than a[i], or -1 when a[i] is the largest.
//
// The one idea: position does not matter, only value, so put every number in a TreeSet
// once and ask TreeSet.higher(a[i]) -- the smallest value strictly above a[i] -- in
// O(log n).
//
// Siblings -- the same TreeSet lookup:
//   twopointers.closest.ClosestSmallerValue -- lower(): the largest value strictly below
//   twopointers.closest.CeilingOnRight      -- ceiling(), and only among the numbers to the RIGHT
//   twopointers.closest.ClosestValue        -- the nearer of the two sides
//
// Time: O(n log n) -- build the tree, then n lookups.
// Space: O(n) for the tree.

public final class ClosestGreaterValue {

  public static int[] closest(int[] a) {
    TreeSet<Integer> set = new TreeSet<>();
    for (int x : a) {
      set.add(x);                                 // RECORD every value
    }
    int[] closest = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      Integer greater = set.higher(a[i]);         // FIND the smallest value above a[i]
      closest[i] = greater == null ? -1 : greater; // RECORD it, -1 when there is none
    }
    return closest;
  }

  public static void main(String[] args) {
    int[] a1 = { 10, 5, 11, 6, 20, 12 };
    System.out.println(Arrays.toString(closest(a1))); // [11, 6, 12, 10, -1, 20]

    int[] a2 = { 10, 5, 11, 10, 20, 12 };
    System.out.println(Arrays.toString(closest(a2))); // [11, 10, 12, 11, -1, 20]
  }
}
