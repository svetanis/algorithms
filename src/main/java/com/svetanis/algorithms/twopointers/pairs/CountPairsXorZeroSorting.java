package com.svetanis.algorithms.twopointers.pairs;

import static java.util.Arrays.sort;

// Count Pairs With XOR Zero -- sorting
//
// Input: an array of integers.
// Return: how many pairs of positions (i, j), i < j, have a[i] ^ a[j] == 0.
//
// The one idea: x ^ y is 0 exactly when x == y, so this counts pairs of equal values.
// After sorting, the copies of each value form one run; a run of length k gives
// k * (k - 1) / 2 pairs. One pass measures the runs.
//
// Siblings:
//   CountPairsXorZeroHashing -- counts with a map instead of sorting, O(n)
//   CountPairsEqualElements -- the same count, asked as a[i] == a[j]
//   CountPairsGivenXor -- any k, not just 0
//
// Time: O(n log n) -- the sort; the pass after it is O(n).
// Space: O(log n) -- the sort. The array is sorted in place: the caller's order is lost.

public final class CountPairsXorZeroSorting {

  public static int countPairs(int[] a) {
    sort(a);                 // SORT: the copies of a value sit together
    int sum = 0;
    int count = 1;           // the length of the current run of equal values
    int n = a.length;
    for (int i = 1; i < n; i++) {
      if (a[i] == a[i - 1]) {
        count++;             // COUNT: the run goes on
      } else {
        sum += sum(count);   // RECORD the run that just ended
        count = 1;           // START a new run
      }
    }
    return sum + sum(count); // RETURN: the last run is never closed inside the loop
  }

  // the number of pairs among n equal values: choose 2 of n
  private static int sum(int n) {
    return (n * (n - 1)) / 2;
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 1, 2, 4 };
    System.out.println(countPairs(a)); // 2
  }
}
