package com.svetanis.algorithms.sorting.interval;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.sort;
import static java.lang.Math.max;
import static java.util.Comparator.comparing;

import java.util.List;
import java.util.PriorityQueue;

// Given a list of intervals representing the start and end time of ‘N’ meetings, 
// find the minimum number of rooms required to hold all the meetings.

public final class MinMeetingRooms {
  // Time Complexity: O(n*log n)
  // Space Complexity: O(n)

  public static int minMeetingRooms(List<Interval> intervals) {
    int min = 0;
    List<Interval> sorted = sort(intervals, comparing(i -> i.start));
    PriorityQueue<Interval> pq = new PriorityQueue<>(comparing(i -> i.end));
    for (Interval interval : sorted) {
      while (!pq.isEmpty() && interval.start >= pq.peek().end) {
        pq.poll();
      }
      pq.offer(interval);
      min = max(min, pq.size());
    }
    return min;
  }

  public static void main(String[] args) {
    List<Interval> list1 = newArrayList();
    list1.add(new Interval(4, 5));
    list1.add(new Interval(2, 3));
    list1.add(new Interval(2, 4));
    list1.add(new Interval(3, 5));
    System.out.println(minMeetingRooms(list1));

    List<Interval> list2 = newArrayList();
    list2.add(new Interval(1, 4));
    list2.add(new Interval(2, 5));
    list2.add(new Interval(7, 9));
    System.out.println(minMeetingRooms(list2));

    List<Interval> list3 = newArrayList();
    list3.add(new Interval(6, 7));
    list3.add(new Interval(2, 4));
    list3.add(new Interval(8, 12));
    System.out.println(minMeetingRooms(list3));
  }
}
