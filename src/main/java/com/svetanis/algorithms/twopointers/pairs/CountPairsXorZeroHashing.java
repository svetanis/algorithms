package com.svetanis.algorithms.twopointers.pairs;

import java.util.HashMap;
import java.util.Map;

// Count Pairs With XOR Zero -- hashing
//
// Input: an array of integers.
// Return: how many pairs of positions (i, j), i < j, have a[i] ^ a[j] == 0.
//
// The one idea: x ^ y is 0 exactly when x == y -- XOR leaves a 1 wherever the two
// numbers differ -- so this counts pairs of equal values. A value that appears k times
// gives k * (k - 1) / 2 of them: choose 2 of its k positions.
//
// Siblings:
//   CountPairsXorZeroSorting -- sorts so equal values sit together, no map
//   CountPairsEqualElements -- the same count, asked as a[i] == a[j]
//   CountPairsGivenXor -- any k, not just 0
//
// Time: O(n) -- one pass to count, one over the distinct values.
// Space: O(n) -- the map.

public final class CountPairsXorZeroHashing {

  public static int countPairs(int[] a) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int x : a) {
      map.put(x, map.getOrDefault(x, 0) + 1); // COUNT each value
    }
    int sum = 0;
    for (int v : map.values()) {
      sum += (v * (v - 1)) / 2;               // COUNT: choose 2 of the v positions
    }
    return sum;
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 1, 2, 4 };
    System.out.println(countPairs(a)); // 2
  }
}
