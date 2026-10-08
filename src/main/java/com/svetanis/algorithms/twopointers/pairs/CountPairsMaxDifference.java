package com.svetanis.algorithms.twopointers.pairs;

// Count Pairs With the Maximum Difference
//
// Input: an array of n >= 1 integers.
// Return: how many pairs of positions (i, j), i < j, reach the largest difference
// |a[i] - a[j]| of any pair.
//
// The one idea: the largest difference is max - min, and only a copy of the minimum
// paired with a copy of the maximum reaches it, so the answer is (copies of min) times
// (copies of max). The exception is an array of one repeated value: then min == max,
// every pair has difference 0, and all n * (n - 1) / 2 pairs count.
//
// Sibling: CountPairsMaxSum -- the same counting for the largest SUM
//
// Time: O(n) -- one pass for min and max, one to count their copies.
// Space: O(1).

public final class CountPairsMaxDifference {

  public static int countPairs(int[] a) {
    int n = a.length;
    int min = a[0];
    int max = a[0];
    for (int x : a) {
      min = Math.min(min, x);     // the smallest value
      max = Math.max(max, x);     // the largest value
    }
    int minCount = 0;
    int maxCount = 0;
    for (int i = 0; i < n; i++) {
      if (a[i] == min) {
        minCount++;               // COUNT the copies of min
      }
      if (a[i] == max) {
        maxCount++;               // COUNT the copies of max
      }
    }
    if (min == max) {
      return n * (n - 1) / 2;     // RETURN: one value, every pair qualifies
    } else {
      return minCount * maxCount; // RETURN: one copy of each end per pair
    }
  }

  public static void main(String[] args) {
    int[] a1 = { 3, 2, 1, 1, 3 };
    System.out.println(countPairs(a1)); // 4

    int[] a2 = { 2, 4, 1, 1 };
    System.out.println(countPairs(a2)); // 2
  }
}
