package com.svetanis.algorithms.sorting.interval;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.sort;
import static java.util.Comparator.comparing;

import java.util.List;

public final class CompletelyOverlapingIntervals {

  public static boolean isOverlap(List<Interval> intervals) {
    List<Interval> sorted = sort(intervals, comparing(i -> i.start));
    for (int i = 1; i < sorted.size(); i++) {
      int right = sorted.get(i).end;
      int prev = sorted.get(i - 1).end;
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
  }
}
