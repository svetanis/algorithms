package com.svetanis.algorithms.twopointers.closest;

import java.util.Arrays;
import java.util.TreeMap;

// Closest value at another position
//
// Input: an array of integers.
// Return: for each position i, the value at ANOTHER position nearest to a[i]: a[i] itself
// when it occurs twice or more; otherwise the nearer of the next smaller and the next
// larger value, the smaller one on a tie; -1 when the array holds no other number.
//
// The one idea: position does not matter, only value. Count every value in a TreeMap; a
// value seen twice has a copy at distance 0. Otherwise the nearest value is one of its two
// neighbours in sorted order -- lowerKey and higherKey -- and the closer of them wins.
//
// Siblings -- the same tree lookups, one side each:
//   twopointers.closest.ClosestSmallerValue -- lower() only
//   twopointers.closest.ClosestGreaterValue -- higher() only
//
// Time: O(n log n) -- build the map, then n pairs of lookups.
// Space: O(n) for the map.

public final class ClosestValue {

  public static int[] closest(int[] a) {
    TreeMap<Integer, Integer> map = new TreeMap<>(); // value -> how many times it occurs
    for (int x : a) {
      map.merge(x, 1, Integer::sum);              // COUNT every value
    }
    int[] closest = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      if (map.get(a[i]) > 1) {
        closest[i] = a[i];                        // FOUND: a copy elsewhere, distance 0
      } else {
        closest[i] = nearer(a[i], map.lowerKey(a[i]), map.higherKey(a[i]));
      }
    }
    return closest;
  }

  private static int nearer(int x, Integer lower, Integer higher) {
    if (lower == null && higher == null) {
      return -1;                                  // RETURN -1: no other number at all
    }
    if (higher == null) {
      return lower;
    }
    if (lower == null) {
      return higher;
    }
    return (long) x - lower <= (long) higher - x ? lower : higher; // COMPARE distances; a tie keeps the smaller
  }

  public static void main(String[] args) {
    int[] a1 = { 10, 5, 11, 6, 20, 12, 10 };
    System.out.println(Arrays.toString(closest(a1))); // [10, 6, 10, 5, 12, 11, 10]

    int[] a2 = { 10, 5, 11, 10, 20, 12 };
    System.out.println(Arrays.toString(closest(a2))); // [10, 10, 10, 10, 12, 11]

    int[] a3 = { 1, 9, 10 };
    System.out.println(Arrays.toString(closest(a3))); // [9, 10, 9]
  }
}
