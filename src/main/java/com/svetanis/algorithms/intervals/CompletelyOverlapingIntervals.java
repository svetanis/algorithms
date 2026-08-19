package com.svetanis.algorithms.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.sort;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.reverseOrder;

import java.util.Comparator;
import java.util.List;

// given a set of intervals, check if ANY interval completely overlaps another,
// i.e. is entirely contained in it. sharing an endpoint still counts:
// {1, 3} is completely overlapped by {1, 7}.

public final class CompletelyOverlapingIntervals {

  // earliest start first, and on a tie the LONGER interval first.
  // without the tie-break, {5, 6} can be placed before {5, 8} and the
  // containment between them is never compared -- the answer would then
  // depend on the order the intervals happened to be listed in.
  private static final Comparator<Interval> BY_START_THEN_LONGEST =
      comparingInt((Interval i) -> i.start).thenComparing(i -> i.end, reverseOrder());

  public static boolean isOverlap(List<Interval> intervals) {
    List<Interval> sorted = sort(intervals, BY_START_THEN_LONGEST);
    for (int i = 1; i < sorted.size(); i++) {
      int right = sorted.get(i).end;
      int prev = sorted.get(i - 1).end;
      // starts are non-decreasing, so an end that fails to advance means
      // this interval sits entirely inside the one before it
      if (right <= prev) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    List<Interval> list1 = newArrayList();
    list1.add(new Interval(6, 8));
    list1.add(new Interval(1, 9));
    list1.add(new Interval(2, 4));
    list1.add(new Interval(4, 7));
    System.out.println(isOverlap(list1));

    List<Interval> list2 = newArrayList();
    list2.add(new Interval(1, 3));
    list2.add(new Interval(1, 7));
    list2.add(new Interval(4, 8));
    list2.add(new Interval(2, 5));
    System.out.println(isOverlap(list2));

    List<Interval> list3 = newArrayList();
    list3.add(new Interval(1, 3));
    list3.add(new Interval(7, 9));
    list3.add(new Interval(4, 6));
    list3.add(new Interval(10, 13));
    System.out.println(isOverlap(list3));

    // equal starts, shorter one listed first -- the case the tie-break exists for
    List<Interval> list4 = newArrayList();
    list4.add(new Interval(1, 3));
    list4.add(new Interval(5, 6));
    list4.add(new Interval(5, 8));
    System.out.println(isOverlap(list4));
  }
}
