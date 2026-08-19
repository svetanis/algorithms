package com.svetanis.algorithms.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.sort;
import static com.svetanis.java.base.utils.Print.print;
import static java.util.Comparator.comparing;

import java.util.List;

import com.google.common.collect.ImmutableList;

// given n activities with their start and finish times, select the maximum
// number that one person can perform, doing only one at a time. an activity
// can be chosen only if its start time is GREATER THAN the finish time of the
// last chosen one -- so an activity starting exactly when the previous
// finishes CANNOT be taken.

// 1) sort the activities by finishing time
// 2) take the first one
// 3) take each later activity whose start is greater than the last taken finish

public final class ActivitySelection {

  public static ImmutableList<Interval> activities(List<Interval> intervals) {
    if (intervals.isEmpty()) {
      return ImmutableList.of();
    }
    // sorted here rather than by the caller: the greedy is only correct on
    // finish-time order, and a method that silently needs a pre-sorted input
    // returns a plausible wrong answer when handed anything else
    List<Interval> sorted = sort(intervals, comparing(i -> i.end));

    List<Interval> list = newArrayList();
    list.add(sorted.get(0));
    for (int i = 1; i < sorted.size(); i++) {
      if (sorted.get(i).start > list.get(list.size() - 1).end) {
        list.add(sorted.get(i));
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    int[] start = { 1, 3, 0, 5, 8, 5 };
    int[] end = { 2, 4, 6, 7, 9, 9 };
    print(activities(build(start, end)));

    // an activity starting exactly when another finishes cannot follow it,
    // so {10, 20} and {20, 30} cannot both be taken -- the answer is 1
    int[] touchStart = { 10, 12, 20 };
    int[] touchEnd = { 20, 25, 30 };
    print(activities(build(touchStart, touchEnd)));
  }

  private static List<Interval> build(int[] start, int[] end) {
    List<Interval> list = newArrayList();
    for (int i = 0; i < start.length; i++) {
      list.add(new Interval(start[i], end[i]));
    }
    return list;
  }
}
