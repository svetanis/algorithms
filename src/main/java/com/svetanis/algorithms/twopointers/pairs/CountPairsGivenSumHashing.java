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
//   CountPairsGivenSum -- the same counting, line for line
//   CountPairsGivenSum2ArraysHashing -- one number from each of two arrays
//   PairsGivenSumHashing -- lists the pairs instead of counting them
//   twopointers.MaxNumOfKSumPairs1679 -- every number used in at most one pair
//
// Time: O(n) -- one pass to count, one to look up.
// Space: O(n) -- the map.

public final class CountPairsGivenSumHashing {

  public static int count(int[] a, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int x : a) {
      map.put(x, map.getOrDefault(x, 0) + 1); // COUNT each value
    }
    int count = 0;
    for (int i = 0; i < a.length; i++) {
      int diff = k - a[i];                    // the only value a[i] can pair with
      count += map.getOrDefault(diff, 0);     // COUNT the positions holding it
      if (diff == a[i]) {
        count--;                              // DROP the pair of a[i] with itself
      }
    }
    return count / 2;                         // RETURN: each pair was counted from both ends
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 5, 7, -1 };
    System.out.println(count(a1, 6)); // 2

    int[] a2 = { 1, 5, 7, -1, 5 };
    System.out.println(count(a2, 6)); // 3

    int[] a3 = { 1, 1, 1, 1 };
    System.out.println(count(a3, 2)); // 6

    int[] a4 = { 10, 12, 10, 15, -1, 7, 6, 5, 4, 2, 1, 1, 1 };
    System.out.println(count(a4, 11)); // 9
  }
}
