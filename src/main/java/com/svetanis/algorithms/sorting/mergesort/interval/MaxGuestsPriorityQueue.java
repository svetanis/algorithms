package com.svetanis.algorithms.sorting.mergesort.interval;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.sort;
import static java.lang.Math.max;
import static java.util.Comparator.comparing;

import java.util.List;
import java.util.PriorityQueue;

// given a list of intervals, find the point
// where the max number of intervals overlap

public final class MaxGuestsPriorityQueue {
  // Time Complexity: O(n*log n)
  // Space Complexity: O(n)
	
  public static int maxGuests(List<Interval> intervals) {
    int max = 0;
    List<Interval> sorted = sort(intervals, comparing(i -> i.start));
    PriorityQueue<Interval> pq = new PriorityQueue<>(comparing(i -> i.end));
    
    for (Interval interval : sorted) {
      while (!pq.isEmpty() && interval.start > pq.peek().end) {
        pq.poll();
      }
      pq.offer(interval);
      max = max(max, pq.size());
    }
    return max;
  }

  public static void main(String[] args) {
    List<Interval> list = newArrayList();
    list.add(new Interval(1, 4));
    list.add(new Interval(2, 5));
    list.add(new Interval(10, 12));
    list.add(new Interval(5, 9));
    list.add(new Interval(5, 12));
    System.out.println(maxGuests(list));
  }
}
