package com.svetanis.algorithms.intervals.select;


import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// 1353. Maximum Number of Events That Can Be Attended
//
// Takes events as [startDay, endDay], both days included: an event can be attended on any one
// day in that range. Only one event can be attended per day. Returns the most events that can be
// attended; 0 for no events. The input array is not changed (a copy is sorted). The last day must
// be below Integer.MAX_VALUE: the day counter steps one past each day it uses. LC keeps days
// within 1 <= start <= end <= 10^5.
//
// Same rule as MaxNumberOfEventsDayByDay: on each day, attend the waiting event whose end day is
// soonest. If a best plan spends that day on another event instead, swapping the two keeps the
// plan valid, because the other event ends no sooner. The difference is how the day moves. Events
// sorted by start day become available in the order the days reach them. Whenever nothing is
// waiting, the day jumps straight to the next event's start day, so a day with nothing on it is
// never visited, and the work depends on the number of events, not on how far apart the days are.

public final class MaxNumberOfEventsSortByStart {
  // Time Complexity: O(n log n), the sort; each pass of the loop attends one event, and each event
  // enters and leaves the queue once
  // Space Complexity: O(n), the sorted copy and the queue
  //
  // MaxNumberOfEventsDayByDay needs no sort and is shorter, and is as fast while the day range is no
  // larger than n. This one is the one to write when the days are sparse.

  public static int maxEvents(int[][] events) {
    if (events.length == 0) {
      return 0;
    }

    // sorted by start, so the events become available in the order the day
    // reaches them and one forward scan is enough
    int[][] sorted = events.clone();
    Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));

    // the queue holds the END day of every event already available and not
    // yet attended. its head is the one that expires soonest, which is the
    // one to take -- every other waiting event ends no sooner
    Queue<Integer> pq = new PriorityQueue<>();

    int next = 0;
    int day = 0;
    int count = 0;

    while (next < sorted.length || !pq.isEmpty()) {
      if (pq.isEmpty()) {
        // nothing available: skip the gap entirely instead of counting
        // through it. this line is the whole difference from DayByDay
        day = sorted[next][0];
      }
      while (next < sorted.length && sorted[next][0] <= day) {
        pq.offer(sorted[next][1]);
        next++;
      }
      pq.poll(); // attend today the waiting event that ends soonest
      count++;
      day++;
      // anything that ended before the new day can never be attended
      while (!pq.isEmpty() && pq.peek() < day) {
        pq.poll();
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[][] g1 = { { 1, 2 }, { 2, 3 }, { 3, 4 } };
    System.out.println(maxEvents(g1)); // 3

    int[][] g2 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 2 } };
    System.out.println(maxEvents(g2)); // 4

    int[][] g3 = { { 1, 4 }, { 4, 4 }, { 2, 2 }, { 3, 4 }, { 1, 1 } };
    System.out.println(maxEvents(g3)); // 4

    int[][] g4 = { { 1, 2 }, { 1, 2 }, { 3, 3 }, { 1, 5 }, { 1, 5 } };
    System.out.println(maxEvents(g4)); // 5

    // the case this file exists for: two events, a wide gap between them.
    // MaxNumberOfEventsDayByDay counts through every day in between
    int[][] gap = { { 1, 1 }, { 100000, 100000 } };
    System.out.println(maxEvents(gap)); // 2

    // one event whose window is long: attended once, on its first day
    int[][] wide = { { 1, 100000 } };
    System.out.println(maxEvents(wide)); // 1

    int[][] none = {};
    System.out.println(maxEvents(none)); // 0
  }
}
