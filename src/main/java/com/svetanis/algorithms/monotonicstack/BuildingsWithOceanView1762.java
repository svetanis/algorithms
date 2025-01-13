package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 1762. Buildings With an Ocean View

public final class BuildingsWithOceanView1762 {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static int[] buildings(int[] heights) {
    int n = heights.length;
    int maxRightHeight = 0;
    List<Integer> list = new ArrayList<>();
    for (int i = n - 1; i >= 0; i--) {
      if (heights[i] > maxRightHeight) {
        list.add(i);
        maxRightHeight = heights[i];
      }
    }
    Collections.reverse(list);
    return list.stream().mapToInt(Integer::intValue).toArray();
  }

  public static void main(String[] args) {
    int[] a = { 4, 2, 3, 1 };
    Print.print(buildings(a)); // 0 2 3
  }
}