package com.svetanis.algorithms.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 759. Employee Free Time

public final class EmployeeFreeTime759 {
  // Time Complexity: O(n log n)
  // Space Complexity: O(n)

  public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    List<Interval> intervals = flatten(schedule);
    Collections.sort(intervals, (a, b) -> a.start - b.start);
    int end = intervals.get(0).end;
    List<Interval> list = new ArrayList<>();
    for (Interval interval : intervals) {
      if (interval.start > end) {
        list.add(new Interval(end, interval.start));
      }
      end = Math.max(end, interval.end);
    }
    return list;
  }

  private static List<Interval> flatten(List<List<Interval>> schedule) {
    List<Interval> list = new ArrayList<>();
    for (List<Interval> employee : schedule) {
      for (Interval interval : employee) {
        list.add(interval);
      }
    }
    return list;
  }

  public static void main(String[] args) {
    List<List<Interval>> list = new ArrayList<>();
    list.add(Arrays.asList(new Interval(1, 3), new Interval(6, 7)));
    list.add(Arrays.asList(new Interval(2, 4)));
    list.add(Arrays.asList(new Interval(2, 5), new Interval(9, 12)));
    System.out.println(employeeFreeTime(list)); // [5,6], [7,9]
  }

  private static class Interval {
    private int start;
    private int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public String toString() {
      return "[" + start + ", " + end + "]";
    }
  }
}