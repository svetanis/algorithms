package com.svetanis.algorithms.intervals;

import java.util.Arrays;

// 435. Non-overlapping Intervals

public final class NonOverlappingIntervals {
  // Time Complexity: O(n * log n)
  // Space Complexity: O(1)

  public static int count(int[][] intervals) {
    // sort by end time
    Arrays.sort(intervals, (i1, i2) -> i1[1] - i2[1]);
    int overlaps = 0;
    int end = intervals[0][1];
    for (int i = 1; i < intervals.length; i++) {
      int start = intervals[i][0];
      // curr doesn't overlap with prev, update end
      if (start >= end) {
        end = intervals[i][1];
      } else {
        overlaps++;
      }
    }
    return overlaps;
  }

  public static void main(String[] args) {
    int[][] intervals1 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } };
    System.out.println(count(intervals1)); // 1

    int[][] intervals2 = { { 1, 2 }, { 1, 2 }, { 1, 2 } };
    System.out.println(count(intervals2)); // 2

    int[][] intervals3 = { { 1, 2 }, { 2, 3 } };
    System.out.println(count(intervals3)); // 0
  }
}
