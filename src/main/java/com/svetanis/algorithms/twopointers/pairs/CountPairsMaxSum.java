package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Integer.MIN_VALUE;

import java.util.HashMap;
import java.util.Map;

// Count Pairs With the Maximum Sum
//
// Input: an array of n >= 2 integers.
// Return: how many pairs of positions (i, j), i < j, reach the largest pair sum.
//
// The one idea: the largest pair sum is the largest value plus the largest value at any
// OTHER position. If the maximum appears m >= 2 times, that sum is max + max and any 2
// of its m copies reach it: m * (m - 1) / 2 pairs. If the maximum appears once, it is in
// every best pair, and its partner is any copy of the next value below it.
//
// Sibling: CountPairsMaxDifference -- the same counting for the largest difference
//
// Time: O(n) -- one pass for the two largest, one to count.
// Space: O(n) -- the map of counts.

public final class CountPairsMaxSum {

  public static int countPairs(int[] a) {
    int[] pair = largestPair(a);
    Map<Integer, Integer> map = new HashMap<>();
    for (int x : a) {
      map.put(x, map.getOrDefault(x, 0) + 1); // COUNT each value
    }
    int maxCount = map.get(pair[0]);          // the copies of the maximum
    if (maxCount > 1) {
      return maxCount * (maxCount - 1) / 2;   // RETURN: any 2 copies of the maximum
    }
    return map.get(pair[1]);                  // RETURN: the maximum with any copy of the next value
  }

  // the largest value and the largest value at another position -- equal when the maximum repeats
  private static int[] largestPair(int[] a) {
    int n = a.length;
    int first = MIN_VALUE;
    int second = MIN_VALUE;
    for (int i = 0; i < n; ++i) {
      if (a[i] > first) {
        second = first; // MOVE the old largest down to second
        first = a[i];
      } else if (a[i] > second) {
        second = a[i];  // RECORD a new second, a copy of first included
      }
    }
    return new int[] { first, second };
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 1, 1, 2, 2, 2, 3 };
    System.out.println(countPairs(a1)); // 3
  }
}
