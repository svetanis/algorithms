package com.svetanis.algorithms.sorting.interval;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.sort;
import static java.lang.Math.max;
import static java.util.Comparator.comparing;

import java.util.List;
import java.util.PriorityQueue;

// given a list of intervals representing 
// the arrival and departure times of trains, 
// find min number of platforms required for 
// the train station so that no train has to wait.

public final class MinPlatformsPriorityQueue {
  // Time Complexity: O(n*log n)
	
  public static int minPlatforms(List<Interval> intervals) {
    int min = 0;
    List<Interval> sorted = sort(intervals, comparing(i -> i.start));
    PriorityQueue<Interval> pq = new PriorityQueue<>(comparing(i -> i.end));
    for (Interval interval : sorted) {
      while (!pq.isEmpty() && interval.start > pq.peek().end) {
        pq.poll();
      }
      pq.offer(interval);
      min = max(min, pq.size());
    }
    return min;
  }

  public static void main(String[] args) {
    List<Interval> list = newArrayList();
    list.add(new Interval(900, 910));
    list.add(new Interval(940, 1200));
    list.add(new Interval(950, 1120));
    list.add(new Interval(1100, 1130));
    list.add(new Interval(1500, 1900));
    list.add(new Interval(1800, 2000));
    System.out.println(minPlatforms(list));
  }
}
