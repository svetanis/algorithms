package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 3074. Apple Redistribution into Boxes

public final class AppleRedistributionIntoBoxes {
  // Time Complexity: O(m + n log n)
  // Space Complexity: O(1)

  public static int minBoxes(int[] apple, int[] capacity) {
    Arrays.sort(capacity); // 1 2 3 4 5
    int total = 0;
    for (int pack : apple) {
      total += pack;
    }
    int count = 0;
    int n = capacity.length;

    while (total > 0) {
      total -= capacity[n - 1 - count];
      count += 1;
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 2 };
    int[] c1 = { 4, 3, 1, 5, 2 };
    System.out.println(minBoxes(a1, c1)); // 2

    int[] a2 = { 5, 5, 5 };
    int[] c2 = { 2, 4, 2, 7 };
    System.out.println(minBoxes(a2, c2)); // 4
  }
}
