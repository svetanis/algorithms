package com.svetanis.algorithms.prefixsum;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;
import static java.util.Arrays.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.svetanis.algorithms.intervals.Interval;

// given an array of both positive and negative
// numbers, find the longest subarray whose sum
// is closest to zero, and return
// {left index, right index, distance from zero}

// no LeetCode number exists for this problem

// prefix[k] = a[0] + a[1] + ... + a[k-1], so
// sum of a[left ... right] = prefix[right + 1] - prefix[left].
// the sum closest to zero is therefore the smallest
// difference between any two prefix values, and after
// sorting the prefix values the two closest of them
// must be adjacent -- anything lying between them
// would be closer to each than they are to each other.

// finding the smallest difference is not the whole
// problem. many subarrays can share that difference,
// and the question asks for the longest of them. those
// competing subarrays are NOT all adjacent pairs in the
// sorted list, so a length tie-break over adjacent pairs
// misses them: on {5, 0, 0, 0, 5} it reports a span of
// length 1 where a span of length 3 also sums to zero.

// so the distance is found first, and the longest
// subarray carrying it is found by a second pass --
// the ordinary longest-subarray-with-a-given-sum loop,
// asked for the distance and for its negation.

public final class LongestSubArrayWithSumClosestToZero {
  // Time complexity: O(n log n)
  // Space complexity: O(n)

  // an empty array has no subarrays, so there is nothing
  // to report. -1 is safe to say that with here: a real
  // distance is an absolute value and is never negative

  private static final int[] NONE = {-1, -1, -1};

  public static int[] subArraySum(List<Integer> list) {
    if (list.isEmpty()) {
      return NONE.clone();
    }
    int distance = minDistance(list);
    Interval interval = longestAt(distance, list);
    return new int[] {interval.start, interval.end, distance};
  }

  // no absolute value is needed: the rows are sorted
  // ascending, so every adjacent difference is >= 0

  private static int minDistance(List<Integer> list) {
    int[][] prefixes = sortedPrefixes(list);
    int min = MAX_VALUE;
    for (int i = 1; i < prefixes.length; i++) {
      min = min(min, prefixes[i][0] - prefixes[i - 1][0]);
    }
    return min;
  }

  // each row is {prefix value, the index it came from},
  // sorted by value, so the index survives the sort

  private static int[][] sortedPrefixes(List<Integer> list) {
    int n = list.size();
    int[][] prefixes = new int[n + 1][2];
    prefixes[0] = new int[] {0, -1}; // the empty prefix, covering nothing
    for (int i = 1; i <= n; i++) {
      prefixes[i] = new int[] {prefixes[i - 1][0] + list.get(i - 1), i - 1};
    }
    sort(prefixes, Comparator.comparingInt(row -> row[0]));
    return prefixes;
  }

  // a subarray whose sum is `distance` from zero sums to
  // either +distance or -distance; both are asked for, and
  // the longer answer wins. at distance 0 the two coincide.

  private static Interval longestAt(int distance, List<Integer> list) {
    Interval positive = LongestSubArrayWithSumEqualsKHashMap.maxSubArray(distance, list);
    if (distance == 0) {
      return positive;
    }
    Interval negative = LongestSubArrayWithSumEqualsKHashMap.maxSubArray(-distance, list);
    return length(negative) > length(positive) ? negative : positive;
  }

  private static int length(Interval interval) {
    return interval.start < 0 ? -1 : interval.end - interval.start + 1;
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
