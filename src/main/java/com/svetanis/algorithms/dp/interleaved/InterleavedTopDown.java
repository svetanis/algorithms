package com.svetanis.algorithms.dp.interleaved;

import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Maps.checkedGet;
import static com.svetanis.java.base.collect.Maps.checkedPut;

import java.util.Map;

// Given two strings str1 and str2, 
// write a function that prints all 
// interleavings of the given two strings.

public final class InterleavedTopDown {

  public static boolean isInterleaved(String s1, String s2, String s) {
    // Time Complexity: O(2^(n + m))
    // Space Complexity: O(n + m)
    Map<String, Boolean> map = newHashMap();
    return isInterleaved(map, s1, s2, s, 0, 0, 0);
  }

  private static boolean isInterleaved(Map<String, Boolean> map, String x, String y, String s, int i, int j, int k) {
    int n = x.length();
    int m = y.length();
    int p = s.length();

    if (i == n && j == m && k == p) {
      return true;
    }

    if (k == p) {
      return false;
    }

    String key = i + "_" + j + "_" + k;
    if (map.containsKey(key)) {
      return checkedGet(map, key);
    }

    boolean interleaved = false;
    if (i < n && x.charAt(i) == s.charAt(k)) {
      interleaved = interleaved || isInterleaved(map, x, y, s, i + 1, j, k + 1);
    }

    if (j < m && y.charAt(j) == s.charAt(k)) {
      interleaved = interleaved || isInterleaved(map, x, y, s, i, j + 1, k + 1);
    }
    checkedPut(map, key, interleaved);
    return checkedGet(map, key);
  }

  public static void main(String[] args) {
    System.out.println(isInterleaved("XXY", "XXZ", "XXZXXXY")); // false
    System.out.println(isInterleaved("XY", "WZ", "WZXY")); // true
    System.out.println(isInterleaved("XY", "X", "XXY")); // true
    System.out.println(isInterleaved("YX", "X", "XXY")); // false
    System.out.println(isInterleaved("XXY", "XXZ", "XXXXZY")); // true
  }
}
