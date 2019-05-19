package com.svetanis.algorithms.dp.math.pascal;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

public final class PascalTriangleDynamicSpaceEfficient {

  public static List<List<Integer>> pascal(int n) {
    // Time complexity: O(n^2)
    // Auxiliary space: O(1)

    List<List<Integer>> lists = newArrayList();
    for (int line = 1; line <= n; ++line) {
      int c = 1; // used to represent C(line, i)
      List<Integer> list = newArrayList();
      for (int i = 1; i <= line; ++i) {
        // the first value in a line is always 1
        list.add(c);
        c = c * (line - i) / i;
      }
      lists.add(list);
    }
    return lists;
  }

  public static void main(String[] args) {
    int n = 7;
    pascal(n);
  }
}
