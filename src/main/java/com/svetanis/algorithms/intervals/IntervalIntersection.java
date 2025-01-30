package com.svetanis.algorithms.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given two lists of intervals, find the intersection of these two lists. 
// Each list consists of disjoint intervals sorted on their start time.

// whenever the two intervals overlap, one of the interval’s start time lies within the other interval
// the overlapping interval will be equal to:
//  start = max(a.start, b.start)
//  end = min(a.end, b.end) 
// That is, the highest start time and the lowest end time will be the overlapping interval.

public final class IntervalIntersection {
  // Time Complexity: O(n + m)

  public static ImmutableList<Interval> intersection(List<Interval> list1, List<Interval> list2) {
    int n = list1.size();
    int m = list2.size();

    int i = 0;
    int j = 0;
    List<Interval> list = newArrayList();
    
    while (i < n && j < m) {
      Interval interval1 = list1.get(i);
      Interval interval2 = list2.get(j);
      boolean one = interval1.start >= interval2.start;
      boolean two = interval1.start <= interval2.end;
      boolean three = interval2.start >= interval1.start;
      boolean four = interval2.start <= interval1.end;
      if ((one && two) || (three && four)) {
        int start = max(interval1.start, interval2.start);
        int end = min(interval1.end, interval2.end);
        list.add(new Interval(start, end));
      }

      if (interval1.end < interval2.end) {
        i++;
      } else {
        j++;
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    List<Interval> list1 = newArrayList();
    list1.add(new Interval(1, 3));
    list1.add(new Interval(5, 6));
    list1.add(new Interval(7, 9));

    List<Interval> list2 = newArrayList();
    list2.add(new Interval(2, 3));
    list2.add(new Interval(5, 7));

    print(intersection(list1, list2)); // [2, 3] [5, 6] [7, 7]

    List<Interval> list3 = newArrayList();
    list3.add(new Interval(1, 3));
    list3.add(new Interval(5, 7));
    list3.add(new Interval(9, 12));

    List<Interval> list4 = newArrayList();
    list4.add(new Interval(5, 10));

    print(intersection(list3, list4)); // [5, 7] [9, 10]
  }
}
