package com.svetanis.algorithms.twopointers.pairs;

import java.util.HashMap;
import java.util.Map;

// Count Pairs With a Given Sum
//
// Input: an array of integers, values may repeat, and a target k.
// Return: how many pairs of positions (i, j), i < j, have a[i] + a[j] == k.
//
// The one idea: count every value first. The partners of a[i] are the positions that
// hold k - a[i], and the count says how many there are. When k - a[i] == a[i], one of
// those positions is i itself, so take one off. Every pair has now been counted from
// both of its ends, so halve the total.
//
// Siblings:
//   CountPairsGivenSumHashing -- the same counting, line for line
//   CountPairsGivenSum2ArraysHashing -- one number from each of two arrays
//   PairsGivenSumHashing -- lists the pairs instead of counting them
//   twopointers.MaxNumOfKSumPairs1679 -- every number used in at most one pair
//
// Time: O(n) -- one pass to count, one to look up.
// Space: O(n) -- the map.

public final class CountPairsGivenSum {

  public static int count(int[] a, int k) {
    int n = a.length;
    Map<Integer, Integer> map = new HashMap<>();
    for (int x : a) {
      map.put(x, map.getOrDefault(x, 0) + 1); // COUNT each value
    }
    int count = 0;
    for (int i = 0; i < n; i++) {
      count += map.getOrDefault(k - a[i], 0); // COUNT the positions holding the partner
      if (k - a[i] == a[i]) {
        count--;                              // DROP the pair of a[i] with itself
      }
    }
    return count / 2;                         // RETURN: each pair was counted from both ends
  }

  public static void main(String[] args) {
    int[] a = { 1, 5, 7, -1, 5 };
    System.out.println(count(a, 6)); // 3

    int[] a1 = { 2, 3, 9 };
    System.out.println(count(a1, 5)); // 1
  }
}
