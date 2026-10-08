package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.HashMap;
import java.util.Map;

// Pick Two Values With a Given Sum, Nearest to the Ends
//
// Input: an array of n integers and a target k. Reaching the number at position i
// (counted from 0) takes min(i + 1, n - i) steps: its distance from the nearer end,
// counting the number itself.
// Return: over every pair of two DIFFERENT values that add up to k, the cost of reaching
// both -- the larger of their two distances -- and the smallest such cost;
// Integer.MAX_VALUE if no pair exists.
// Convention the code follows: the two values must differ (x != y), so two copies of
// k / 2 never count. The source statement would settle whether they should.
//
// The one idea: only the nearest copy of each value matters, so one pass records, for
// every value, its smallest distance. Then each x looks up its partner k - x in O(1);
// the pair costs the larger of the two distances, and the answer is the smallest cost.
//
// Sibling: PairGivenSumHashing -- finds any pair with the sum, with no costs
//
// Time: O(n) -- one pass to build the map, one to try every x.
// Space: O(n) -- the map.

public final class PairGivenSumMaxShortestDist {

  public static int maxShortestDist(int[] a, int k) {
    Map<Integer, Integer> map = distMap(a);
    int min = MAX_VALUE;
    for (int x : a) {
      int y = k - x;                           // the only partner x can have
      if (x != y && map.containsKey(y)) {
        int max = max(map.get(x), map.get(y)); // reaching both waits for the farther one
        min = min(min, max);                   // RECORD the cheapest pair so far
      }
    }
    return min;
  }

  // value -> the smallest distance from an end over all its copies
  private static Map<Integer, Integer> distMap(int[] a) {
    int n = a.length;
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      int min = min(1 + i, n - i);     // the distance from the nearer end
      if (map.containsKey(a[i])) {
        int prev = map.get(a[i]);
        map.put(a[i], min(prev, min)); // RECORD the nearer copy
      } else {
        map.put(a[i], min);            // RECORD the first copy
      }
    }
    return map;
  }

  public static void main(String[] args) {
    int[] a = { 3, 5, 8, 6, 7 };
    System.out.println(maxShortestDist(a, 11)); // 2
  }
}
