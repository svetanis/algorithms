package com.svetanis.algorithms.sorting.interval;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.sort;
import static java.util.Comparator.comparing;

import java.util.List;

// Given an array of intervals representing ‘N’ appointments, 
// find out if a person can attend all the appointments.

public final class ConflictingAppointments {
  // Time Complexity: O(n * log n)
	
  public static boolean conflict(List<Interval> intervals) {
    int n = intervals.size();
    List<Interval> sorted = sort(intervals, comparing(i -> i.start));
    for (int i = 1; i < n; i++) {
      if (sorted.get(i).start < sorted.get(i - 1).end) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    List<Interval> list1 = newArrayList();
    list1.add(new Interval(1, 4));
    list1.add(new Interval(2, 5));
    list1.add(new Interval(7, 9));
    System.out.println(conflict(list1));

    List<Interval> list2 = newArrayList();
    list2.add(new Interval(6, 7));
    list2.add(new Interval(2, 4));
    list2.add(new Interval(8, 12));
    System.out.println(conflict(list2));

    List<Interval> list3 = newArrayList();
    list3.add(new Interval(4, 5));
    list3.add(new Interval(2, 3));
    list3.add(new Interval(3, 6));
    System.out.println(conflict(list3));
  }
}
