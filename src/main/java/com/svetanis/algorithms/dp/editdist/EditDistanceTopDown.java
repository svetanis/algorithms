package com.svetanis.algorithms.dp.editdist;

import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Maps.checkedGet;
import static com.svetanis.java.base.collect.Maps.checkedPut;
import static java.lang.Math.min;

import java.util.Map;

// Given strings s1 and s2, we need to transform s1 into s2 
// by deleting, inserting, or replacing characters. 
// Write a function to calculate the count 
// of the min number of edit operations.

public final class EditDistanceTopDown {

  public static int editDist(String x, String y) {
    // Time complexity: O(n * m)

    int n = x.length();
    int m = y.length();
    Map<String, Integer> map = newHashMap();
    return editDist(map, x, y, n, m);
  }

  private static int editDist(Map<String, Integer> map, String x, String y, int n, int m) {

    // base cases
    if (n == 0 && m == 0) {
      return 0;
    }
    if (n == 0) {
      return m;
    }
    if (m == 0) {
      return n;
    }

    String key = n + "_" + m;
    if (map.containsKey(key)) {
      return checkedGet(map, key);
    }

    if (x.charAt(n - 1) == y.charAt(m - 1)) {
      checkedPut(map, key, editDist(map, x, y, n - 1, m - 1));
    } else {
      int delete = 1 + editDist(map, x, y, n - 1, m);
      int insert = 1 + editDist(map, x, y, n, m - 1);
      int replace = 1 + editDist(map, x, y, n - 1, m - 1);
      int min = min(min(delete, insert), replace);
      checkedPut(map, key, min);
    }
    return checkedGet(map, key);
  }

  public static void main(String[] args) {
    System.out.println(editDist("Zeil", "trials"));
    System.out.println(editDist("cat", "act"));
    System.out.println(editDist("COMBO", "COIN"));
  }
}
