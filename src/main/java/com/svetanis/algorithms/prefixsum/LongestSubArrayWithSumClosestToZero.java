package com.svetanis.algorithms.prefixsum;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;
import static java.util.Arrays.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.svetanis.algorithms.intervals.Interval;

// Longest subarray with sum closest to zero -- no LeetCode number; source unknown
//
// Input: a list of integers, positive, negative and zero. A subarray is a run of neighbouring
// elements, at least one long. Returns {left index, right index, distance from zero} for the
// longest subarray whose sum is as close to zero as any subarray's sum gets; the distance is the
// absolute value of that sum. Among equally long ones, a subarray summing to +distance wins over
// one summing to -distance, then the one that ends first. An empty list returns {-1, -1, -1}. No
// constraints are given; the method assumes every prefix sum fits in an int --
// {2 * 10^9, 2 * 10^9} returns [0, 1, 294967296] where the answer is [0, 0, 2000000000].
//
// The one idea: prefix[k] is the total of the first k elements, a[0] + ... + a[k - 1], and
// prefix[0] = 0 is the empty prefix. The sum of a[left..right] is prefix[right + 1] - prefix[left],
// so every subarray is the gap between two prefix values, and the sum closest to zero is the
// smallest gap between any two of them. One row of the table holds a prefix value and the index of
// the last element it covers. Sorted by value, the two closest values must be neighbours --
// anything lying between them would be closer to each than they are to each other -- so one scan
// of neighbouring rows finds the smallest distance.
//
// The distance is not the whole answer. Many subarrays can share it, and the longest of them need
// not come from neighbouring rows: in {5, 0, 0, 0, 5} the prefix values 5, 5, 5, 5 sit side by side,
// neighbouring rows give spans of length 1, and the longest, a[1..3], comes from the first and the
// last of them. So a second pass finds the longest subarray at that distance: the
// longest-subarray-with-a-given-sum loop, asked for +distance and for -distance, the longer kept.
//
// Time: O(n log n) -- the sort; the two other passes are O(n).
// Space: O(n) -- the n + 1 prefix rows, and the map of the second pass.
//
// Siblings: LongestSubArrayWithSumClosestToZeroTreeMap finds the same distance with a TreeMap in
// place of the sort and shares the second pass, which is LongestSubArrayWithSumEqualsKHashMap.

public final class LongestSubArrayWithSumClosestToZero {

  // an empty list has no subarrays, so there is nothing
  // to report. -1 is safe to say that with here: a real
  // distance is an absolute value and is never negative

  private static final int[] NONE = {-1, -1, -1};

  public static int[] subArraySum(List<Integer> list) {
    if (list.isEmpty()) {
      return NONE.clone(); // EMPTY: a copy, so a caller cannot change NONE
    }
    int distance = minDistance(list); // PASS 1: the smallest |sum| any subarray reaches
    Interval interval = longestAt(distance, list); // PASS 2: the longest subarray at that distance
    return new int[] {interval.start, interval.end, distance};
  }

  // no absolute value is needed: the rows are sorted
  // ascending, so every neighbouring difference is >= 0

  private static int minDistance(List<Integer> list) {
    int[][] prefixes = sortedPrefixes(list);
    int min = MAX_VALUE;
    for (int i = 1; i < prefixes.length; i++) { // SCAN neighbouring rows only
      min = min(min, prefixes[i][0] - prefixes[i - 1][0]); // GAP: the sum of one subarray, up to sign
    }
    return min;
  }

  // each row is {prefix value, the index of the last element
  // it covers}, sorted by value. only the value is read later:
  // the second pass finds the span itself

  private static int[][] sortedPrefixes(List<Integer> list) {
    int n = list.size();
    int[][] prefixes = new int[n + 1][2];
    prefixes[0] = new int[] {0, -1}; // EMPTY PREFIX: covers nothing
    for (int i = 1; i <= n; i++) {
      prefixes[i] = new int[] {prefixes[i - 1][0] + list.get(i - 1), i - 1}; // PREFIX: one more element
    }
    sort(prefixes, Comparator.comparingInt(row -> row[0])); // SORT by value: the closest two are neighbours
    return prefixes;
  }

  // a subarray whose sum is `distance` from zero sums to
  // either +distance or -distance; both are asked for, and
  // the longer answer wins. at distance 0 the two coincide.

  private static Interval longestAt(int distance, List<Integer> list) {
    Interval positive = LongestSubArrayWithSumEqualsKHashMap.maxSubArray(distance, list); // ASK +distance
    if (distance == 0) {
      return positive;
    }
    Interval negative = LongestSubArrayWithSumEqualsKHashMap.maxSubArray(-distance, list); // ASK -distance
    return length(negative) > length(positive) ? negative : positive; // KEEP the longer; a tie keeps +
  }

  private static int length(Interval interval) {
    return interval.start < 0 ? -1 : interval.end - interval.start + 1; // NONE FOUND counts as -1
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(subArraySum(asList(8, -3, 2, 1, -4, 10, -5)))); // [1, 3, 0]
    System.out.println(Arrays.toString(subArraySum(asList(5, 0, 0, 0, 5)))); // [1, 3, 0]
    System.out.println(Arrays.toString(subArraySum(asList(1, -1, 1, -1)))); // [0, 3, 0]
    System.out.println(Arrays.toString(subArraySum(asList(10, -2, -7)))); // [0, 2, 1]
    System.out.println(Arrays.toString(subArraySum(asList(-5)))); // [0, 0, 5]
    System.out.println(Arrays.toString(subArraySum(asList(5)))); // [0, 0, 5]
    System.out.println(Arrays.toString(subArraySum(asList()))); // [-1, -1, -1]
  }
}
