package com.svetanis.algorithms.slidingwindow.array;

import java.util.HashMap;
import java.util.Map;

// Subarray with a given sum, negatives allowed -- no LeetCode number; source unknown
//
// Given an unsorted array of integers (including negative), find a subarray which adds
// to a given number k; if there is more than one, return any of them. Returns its first
// and last index, or (-1, -1) when no subarray adds to k.
//
// Prefix sums. The sum of a[s..i] is prefix(i) - prefix(s - 1), so a subarray ending at i
// adds to k exactly when some earlier prefix equals prefix(i) - k. The walk keeps each
// prefix sum it has seen with its index, and at every i looks up the one it needs. The
// whole prefix a[0..i] has no earlier prefix to subtract, so sum == k is checked on its
// own. The answer is a subarray ending at the first index where one exists. Negative
// values are fine: nothing here assumes the sum grows. Sums past the int range wrap.
//
// Time: O(n) -- one pass; each map lookup and update is O(1) on average
// Space: O(n) -- one stored prefix sum per index
//
// Siblings: GivenSumSubArraySlidingWindow answers the same question with a window and
// no map, which needs every value to be non-negative. prefixsum/SubArrayGivenSum is this
// method returning an Optional<Interval>, with the empty prefix stored up front instead
// of the sum == k check. prefixsum/AllGivenSumSubarrays returns every such subarray, and
// prefixsum/CountSubArraysGivenSum (560) counts them.

public class GivenSumSubArrayHashMap {

  // left and right are the subarray's first and last index; (-1, -1) when none exists
  public record Pair(int left, int right) {
    @Override
    public String toString() {
      return "Pair{left=%d, right=%d}".formatted(left, right);
    }
  }

  public static Pair sum(int[] a, int k) {
    int n = a.length;
    Map<Integer, Integer> map = new HashMap<>();

    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += a[i]; // PREFIX: the sum of a[0..i]
      if (sum == k) { // LOOK: the whole prefix a[0..i] adds to k
        return new Pair(0, i);
      }
      int key = sum - k;
      if (map.containsKey(key)) { // LOOK: an earlier prefix a[0..j] with sum - k, so a[j+1..i] adds to k
        int start = map.get(key) + 1;
        return new Pair(start, i);
      }
      map.put(sum, i); // RECORD: this prefix ends at i
    }
    return new Pair(-1, -1); // RETURN: no subarray adds to k
  }

  public static void main(String[] args) {
    int[] a = { 10, 2, -2, -20, 10 };
    System.out.println(sum(a, -10));

    int[] a1 = { 1, -1, 5, -2, 3 };
    System.out.println(sum(a1, 3));

    int[] a2 = { -2, -1, 2, 1 };
    System.out.println(sum(a2, 1));
  }
}