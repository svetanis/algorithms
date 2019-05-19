package com.svetanis.algorithms.slidingwindow.array;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import com.svetanis.java.base.Pair;

// including negative integers

public class GivenSumSubArrayHashMap {

  public static Pair<Integer, Integer> sum(int[] a, int k) {
    // Time complexity: O(n)
    // Space complexity: O(n)

    int n = a.length;
    Map<Integer, Integer> map = newHashMap();

    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += a[i];
      if (sum == k) {
        return Pair.build(0, i);
      }
      int key = sum - k;
      if (map.containsKey(key)) {
        int start = map.get(key) + 1;
        return Pair.build(start, i);
      }
      map.put(sum, i);
    }
    return Pair.build(-1, -1);
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