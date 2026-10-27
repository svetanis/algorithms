package com.svetanis.algorithms.prefixsum;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

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
// The one idea: the running sum after k elements is the total of a[0..k - 1], and 0 before any
// element. The sum of a subarray is the difference between two running sums, so the sum closest
// to zero is the smallest difference between a running sum and any running sum seen before it.
// One map key is one running sum already seen (its value, an element count, is not read). A
// HashMap could only say whether an exact value is present; a TreeMap keeps its keys sorted, so
// floorKey gives the nearest key at or below the current running sum and ceilingKey the nearest at
// or above, and the closest earlier running sum has to be one of those two.
//
// The seed 0 -> 0 makes the map non-empty from the first element, so at least one of the two
// lookups returns a key: every stored key is either at or below the current running sum, or above
// it. Each running sum is stored only after its lookups, so it is never compared with itself.
//
// The distance is not the whole answer. Many subarrays can share it, and the longest of them is not
// what the nearest-key lookups find: in {5, 0, 0, 0, 5} the running sums 5, 5, 5, 5 repeat, and the
// longest zero-sum subarray, a[1..3], runs from the first of them to the last. So a second pass
// finds the longest subarray at that distance: the longest-subarray-with-a-given-sum loop, asked
// for +distance and for -distance, the longer kept.
//
// Time: O(n log n) -- n lookups and inserts into the TreeMap, each O(log n); the second pass is O(n).
// Space: O(n) -- up to n + 1 keys in the TreeMap, and the map of the second pass.
//
// Siblings: LongestSubArrayWithSumClosestToZero finds the same distance by sorting the running sums
// and scanning neighbours; both are O(n log n). Here the TreeMap is filled as the list is read, so
// the distance so far is known after every element; the longest span still needs the second pass
// over the whole list. That second pass is LongestSubArrayWithSumEqualsKHashMap.

public final class LongestSubArrayWithSumClosestToZeroTreeMap {

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

  private static int minDistance(List<Integer> list) {
    TreeMap<Integer, Integer> seen = new TreeMap<>(); // SEEN: running sums so far, kept sorted
    seen.put(0, 0); // SEED: the empty prefix, covering nothing
    int sum = 0;
    int min = MAX_VALUE;
    for (int right = 0; right < list.size(); right++) {
      sum += list.get(right); // RUNNING SUM of list[0..right]
      min = min(min, nearest(seen, sum)); // NEAREST earlier running sum: one subarray's |sum|
      seen.put(sum, right + 1); // STORE only after the lookups
    }
    return min;
  }

  // both differences are already non-negative by construction,
  // so no absolute value is taken: floorKey is at most sum and
  // ceilingKey is at least sum.

  private static int nearest(TreeMap<Integer, Integer> seen, int sum) {
    Integer below = seen.floorKey(sum); // BELOW: the largest key <= sum
    Integer above = seen.ceilingKey(sum); // ABOVE: the smallest key >= sum
    int best = MAX_VALUE;
    if (below != null) {
      best = min(best, sum - below);
    }
    if (above != null) {
      best = min(best, above - sum);
    }
    return best;
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
