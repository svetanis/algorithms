package com.svetanis.algorithms.search.binary;

import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 436. Find Right Interval

public final class RightInterval {
  // Time Complexity: O(log n)

  public static int[] rightInterval(int[][] intervals) {
    int n = intervals.length;
    List<Interval> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add(new Interval(intervals[i][0], i));
    }
    Collections.sort(list, Comparator.comparing(i -> i.start));
    int[] a = new int[n];
    int index = 0;
    for (int[] interval : intervals) {
      int left = 0;
      int right = n - 1;
      int end = interval[1];
      while (left < right) {
        int mid = left + (right - left) / 2;
        if (list.get(mid).start >= end) {
          right = mid;
        } else {
          left = mid + 1;
        }
      }
      a[index++] = list.get(left).start < end ? -1 : list.get(left).end;
    }
    return a;
  }

  public static void main(String[] args) {
    int[][] m1 = { { 1, 2 } };
    print(rightInterval(m1)); // [-1]
    int[][] m2 = { { 3, 4 }, { 2, 3 }, { 1, 2 } };
    print(rightInterval(m2)); // [-1,0,1]
    int[][] m3 = { { 1, 4 }, { 2, 3 }, { 3, 4 } };
    print(rightInterval(m3)); // [-1,2,-1]
  }

  private static class Interval {
    private int start;
    private int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
}