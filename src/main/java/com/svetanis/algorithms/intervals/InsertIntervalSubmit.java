package com.svetanis.algorithms.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 57. Insert Interval 

// com.svetanis.algorithms.intervals.InsertInterval
// com.svetanis.algorithms.sorting.interval.InsertInterval

// given a set of non-overlapping intervals,
// insert a new interval into the intervals
// (merge if necessary)

public final class InsertIntervalSubmit {
  // Time Complexity: O(n log n)
  // Space Complexity: O(n)

  public static int[][] insert(int[][] intervals, int[] interval) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    int i = 0;
    int n = intervals.length;
    List<int[]> list = new ArrayList<>();
    // add all intervals that come before the new interval
    while (i < n && intervals[i][1] < interval[0]) {
      list.add(intervals[i]);
      i++;
    }

    // merge all intervals that overlap with new interval
    while (i < n && intervals[i][0] <= interval[1]) {
      int start = Math.min(intervals[i][0], interval[0]);
      int end = Math.max(intervals[i][1], interval[1]);
      interval[0] = start;
      interval[1] = end;
      i++;
    }
    // insert new interval
    list.add(interval);

    // add all the remaining intervals
    while (i < n) {
      list.add(intervals[i++]);
    }
    return list.toArray(new int[list.size()][]);
  }

  public static void main(String[] args) {
    int[][] intervals1 = { { 1, 3 }, { 6, 9 } };
    int[] interval1 = { 2, 5 };
    Print.print(insert(intervals1, interval1)); // [1,5],[6,9]

    int[][] intervals2 = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
    int[] interval2 = { 4, 8 };
    Print.print(insert(intervals2, interval2)); // [1,2],[3,10],[12,16]
  }
}
