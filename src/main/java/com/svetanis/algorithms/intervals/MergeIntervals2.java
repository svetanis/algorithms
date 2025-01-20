package com.svetanis.algorithms.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.sort;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Math.max;
import static java.util.Comparator.comparing;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.algorithms.sorting.interval.Interval;

// Given a list of intervals, merge all the overlapping intervals  
// to produce a list that has only mutually exclusive intervals.

// 1. Sort the intervals on the start time to ensure a.start <= b.start
// 2. If ‘a’ overlaps ‘b’ (i.e. b.start <= a.end), 
//    we need to merge them into a new interval ‘c’ such that:
//    c.start = a.start
//    c.end = max(a.end, b.end)
// 3. We will keep repeating the above two steps to merge ‘c’ 
//    with the next interval if it overlaps with ‘c’.

public final class MergeIntervals2 {
  // Time Complexity: O(n*log n)
  // Space Complexity: O(n)

  public static ImmutableList<Interval> merge(List<Interval> intervals) {
    List<Interval> list = newArrayList();
    List<Interval> sorted = sort(intervals, comparing(i -> i.start));
    Iterator<Interval> iter = sorted.iterator();
    Interval interval = iter.next();
    int start = interval.start;
    int end = interval.end;

    while (iter.hasNext()) {
      Interval next = iter.next();
      int left = next.start;
      int right = next.end;
      if (left <= end) {
        end = max(end, right);
      } else {
        list.add(new Interval(start, end));
        start = left;
        end = right;
      }
    }
    list.add(new Interval(start, end));
    return newList(list);
  }

  public static void main(String[] args) {
    List<Interval> list1 = newArrayList();
    list1.add(new Interval(6, 8));
    list1.add(new Interval(1, 9));
    list1.add(new Interval(2, 4));
    list1.add(new Interval(4, 7));
    print(merge(list1)); // [1, 9]

    List<Interval> list2 = newArrayList();
    list2.add(new Interval(6, 8));
    list2.add(new Interval(1, 3));
    list2.add(new Interval(2, 4));
    list2.add(new Interval(4, 7));
    print(merge(list2)); // [1, 8]

    List<Interval> list3 = newArrayList();
    list3.add(new Interval(1, 3));
    list3.add(new Interval(7, 9));
    list3.add(new Interval(4, 6));
    list3.add(new Interval(10, 13));
    print(merge(list3)); // [1, 3], [4, 6], [7, 9], [10, 13]
  }
}

