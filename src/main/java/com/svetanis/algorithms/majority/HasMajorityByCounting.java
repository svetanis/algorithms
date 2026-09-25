package com.svetanis.algorithms.majority;

import static com.svetanis.java.base.utils.Maps.freqMap;

import java.util.Map;

// does a[] contain an element appearing more than n/2 times?

public final class HasMajorityByCounting {

  public static boolean isMajority(int[] a) {
    // Time Complexity: O(n)
    // Space Complexity: O(k) for k distinct values -- this is what
    // MajorityElementMooreVoting collapses to O(1).

    int n = a.length;
    Map<Integer, Integer> map = freqMap(a);
    for (int val : map.values()) {
      // strictly greater, never >=: on {1, 1, 2, 2} each value has n/2 copies
      // and neither is a majority.
      if (val > n / 2) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[] a = { 2, 3, 9, 2, 2 };
    System.out.println(isMajority(a)); // true -- 2 appears 3 of 5
    int[] a1 = { 1, 1, 2, 2 };
    System.out.println(isMajority(a1)); // false -- two of four is not more than half
  }
}
