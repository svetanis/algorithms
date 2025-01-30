package com.svetanis.algorithms.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 56. Merge Intervals

// given a collection of intervals
// merge all overlapping intervals

public final class MergeIntervalsSubmit {
  // Time Complexity: O(n * log n)
  // Space Complexity: O(1)

  public static int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
    List<int[]> list = new ArrayList<>();
    list.add(intervals[0]);
    for (int i = 1; i < intervals.length; i++) {
      int start = intervals[i][0];
      int end = intervals[i][1];
      int[] prev = list.get(list.size() - 1);
      // overlapping: extend the last interval
      if (start <= prev[1]) {
        prev[1] = Math.max(prev[1], end);
      } else {
        list.add(intervals[i]);
      }
    }
    return list.toArray(new int[list.size()][]);
  }

  public static void main(String[] args) {
    int[][] intervals1 = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
    Print.print(merge(intervals1)); // [1,6],[8,10],[15,18]

    int[][] intervals2 = { { 1, 4 }, { 4, 5 } };
    Print.print(merge(intervals2)); // [1,5]
  }
}
