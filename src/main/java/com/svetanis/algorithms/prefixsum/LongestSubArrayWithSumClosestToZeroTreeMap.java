package com.svetanis.algorithms.prefixsum;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import com.svetanis.algorithms.intervals.Interval;

// given an array of both positive and negative
// numbers, find the longest subarray whose sum
// is closest to zero, and return
// {left index, right index, distance from zero}

// no LeetCode number exists for this problem

// the same problem as LongestSubArrayWithSumClosestToZero,
// with the sort replaced by a TreeMap.

// a HashMap cannot answer this question at all: hashing
// scatters the keys, so it can only report whether an exact
// value is present, never which stored value is nearest to
// a given one. a TreeMap keeps its keys in sorted order and
// answers that in O(log n): floorKey is the nearest key at
// or below, ceilingKey the nearest at or above, and the
// closest prefix seen so far has to be one of those two.

// the difference from the sorted version is not the cost --
// both are O(n log n) -- but that this one needs no second
// container built up front, so it works on a stream and has
// an answer available after every element.

// the seed makes the map non-empty from the first iteration,
// which is what guarantees at least one of the two lookups
// returns a key. both are null only when the map is empty,
// because every stored key is either at or below the current
// prefix, or above it. which of the two roles the seed plays
// depends on the sign of the running sum.

public final class LongestSubArrayWithSumClosestToZeroTreeMap {
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

  private static int minDistance(List<Integer> list) {
    TreeMap<Integer, Integer> seen = new TreeMap<>();
    seen.put(0, 0); // the empty prefix, covering nothing
    int sum = 0;
    int min = MAX_VALUE;
    for (int right = 0; right < list.size(); right++) {
      sum += list.get(right);
      min = min(min, nearest(seen, sum));
      seen.put(sum, right + 1); // recorded only after the lookups
    }
    return min;
  }

  // both differences are already non-negative by construction,
  // so no absolute value is taken: floorKey is at most sum and
  // ceilingKey is at least sum.

  private static int nearest(TreeMap<Integer, Integer> seen, int sum) {
    Integer below = seen.floorKey(sum);
    Integer above = seen.ceilingKey(sum);
    int best = MAX_VALUE;
    if (below != null) {
      best = min(best, sum - below);
    }
    if (above != null) {
      best = min(best, above - sum);
    }
    return best;
  }

  // finding the smallest distance does not name the longest
  // subarray carrying it -- several subarrays can share it.
  // a subarray that distance from zero sums to either
  // +distance or -distance; both are asked for, and the
  // longer answer wins. at distance 0 the two coincide.

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
