package com.svetanis.algorithms.sorting.mergesort.interval;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.sort;
import static com.svetanis.java.base.utils.Print.print;
import static java.util.Comparator.comparing;

import java.util.List;

import com.google.common.collect.ImmutableList;

// You are given n activities with their start and finish times. 
// Select the maximum number of activities that can be performed by a single person, 
// assuming that a person can only work on a single activity at a time.

// 1) Sort the activities according to their finishing time
// 2) Select the first activity from the sorted array and print it.
// 3) Do following for remaining activities in the sorted array.
//    a) If the start time of this activity is greater than or equal 
//       to the finish time of previously selected activity 
//       then select this activity and print it.

public final class ActivitySelection {

  public static ImmutableList<Interval> activities(List<Interval> intervals) {
    int n = intervals.size();
    List<Interval> list = newArrayList();
    list.add(intervals.get(0));
    for (int i = 1; i < n; i++) {
      if (intervals.get(i).start >= list.get(list.size() - 1).end) {
        list.add(intervals.get(i));
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    int[] start = { 1, 3, 0, 5, 8, 5 };
    int[] end = { 2, 4, 6, 7, 9, 9 };
    List<Interval> activities = buildActivities(start, end);
    print(activities(activities));
  }

  private static List<Interval> buildActivities(int[] start, int[] end) {
    List<Interval> list = newArrayList();
    for (int i = 0; i < start.length; i++) {
      list.add(new Interval(start[i], end[i]));
    }
    return sort(list, comparing(i -> i.end));
  }
}
