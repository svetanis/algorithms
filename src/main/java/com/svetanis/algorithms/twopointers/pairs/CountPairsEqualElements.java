package com.svetanis.algorithms.twopointers.pairs;

import java.util.HashMap;
import java.util.Map;

// Count Pairs of Equal Elements
//
// Input: an array of integers.
// Return: how many pairs of positions (i, j), i < j, hold equal values.
//
// The one idea: a pair never mixes two different values, so count each value on its
// own. A value that appears k times gives k * (k - 1) / 2 pairs -- choose 2 of its k
// positions. One pass counts the values, a second adds up the formula.
//
// Siblings -- the same count under other names:
//   CountPairsXorZeroHashing -- asks for x ^ y == 0, which holds exactly when x == y
//   CountPairsXorZeroSorting -- sorts so equal values sit together, no map
//   datastructures.hashmap.CountGoodPairs (LC 1512 Number of Good Pairs), in the
//     data-structures repo -- the same question on LeetCode
//
// Time: O(n) -- one pass to count, one over the distinct values.
// Space: O(n) -- the map holds at most n values.

public final class CountPairsEqualElements {

  public static int count(int[] a) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int x : a) {
      map.put(x, map.getOrDefault(x, 0) + 1); // COUNT each value
    }
    int total = 0;
    for (int k : map.values()) {
      total += (k * (k - 1)) / 2;             // COUNT: choose 2 of the k positions
    }
    return total;
  }

  public static void main(String[] args) {
    int[] a = { 1, 1, 2 };
    System.out.println(count(a)); // 1

    int[] a1 = { 1, 2, 3, 1, 1, 3 };
    System.out.println(count(a1)); // 4
  }
}
