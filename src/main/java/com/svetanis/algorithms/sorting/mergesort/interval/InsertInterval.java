package com.svetanis.algorithms.sorting.mergesort.interval;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given a list of non-overlapping intervals sorted by their start time, 
// insert a given interval at the correct position and merge all necessary 
// intervals to produce a list that has only mutually exclusive intervals.

// 1. Skip all intervals which end before the start of the new interval, i.e., 
//    skip all intervals with the following condition:
//    intervals[i].end < newInterval.start
// 2. Let’s call the last interval ‘b’ that does not satisfy the above condition. 
//    If ‘b’ overlaps with the new interval (a) (i.e. b.start <= a.end), 
//    we need to merge them into a new interval ‘c’:
//    c.start = min(a.start, b.start)
//    c.end = max(a.end, b.end)
// 3. We will repeat the above two steps to merge ‘c’ with the next overlapping interval.

public final class InsertInterval {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static ImmutableList<Interval> merge(List<Interval> intervals, Interval interval) {
    List<Interval> list = newArrayList();

    // skip all intervals that come before the new interval
    int i = 0;
    while (i < intervals.size() && intervals.get(i).end < interval.start) {
      list.add(intervals.get(i));
      i++;
    }

    // merge all intervals that overlap with new interval
    while (i < intervals.size() && intervals.get(i).start <= interval.end) {
      interval.start = min(interval.start, intervals.get(i).start);
      interval.end = max(interval.end, intervals.get(i).end);
      i++;
    }

    // insert new interval
    list.add(interval);

    // add all remaining intervals
    while (i < intervals.size()) {
      list.add(intervals.get(i++));
    }
    return newList(list);
  }

  public static void main(String[] args) {
    List<Interval> list1 = newArrayList();
    list1.add(new Interval(1, 3));
    list1.add(new Interval(5, 7));
    list1.add(new Interval(8, 12));
    print(merge(list1, new Interval(4, 6)));

    List<Interval> list2 = newArrayList();
    list2.add(new Interval(1, 3));
    list2.add(new Interval(5, 7));
    list2.add(new Interval(8, 12));
    print(merge(list2, new Interval(4, 10)));

    List<Interval> list3 = newArrayList();
    list3.add(new Interval(2, 3));
    list3.add(new Interval(5, 7));
    print(merge(list3, new Interval(1, 4)));
  }
}
