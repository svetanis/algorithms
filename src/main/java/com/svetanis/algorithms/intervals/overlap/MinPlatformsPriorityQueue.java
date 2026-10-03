package com.svetanis.algorithms.intervals.overlap;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.svetanis.algorithms.intervals.Interval;

// Minimum number of railway platforms so no train waits.
//
// Each interval is one train: start = arrival time, end = departure time, start <= end. Returns
// the fewest platforms that give every train a platform from its arrival to its departure. A
// train holds its platform at both of those moments, so when one train departs at the same
// time another arrives, the two cannot share a platform.
//
// This version keeps the trains themselves, not separate counts. One heap entry is a train
// standing at the station right now; the one that departs first is on top. Trains are taken
// in order of arrival. When a train arrives, every train that departed strictly earlier
// leaves the heap. What remains, plus the new train, are the trains standing at the station
// at this arrival time, each on its own platform. The largest such number is the answer.
//
// The same count other ways: MinPlatformsTwoArrays, MinPlatformsDifferenceArray,
// MinPlatformsMultimap and MaxOverlappingIntervals.

public final class MinPlatformsPriorityQueue {
  // Time Complexity: O(n log n), the sort; each train enters and leaves the heap once
  // Space Complexity: O(n), the sorted copy and the heap

  public static int minPlatforms(List<Interval> intervals) {
    int min = 0;
    List<Interval> sorted = new ArrayList<>(intervals); // a copy: the caller's list keeps its order
    sorted.sort((a, b) -> Integer.compare(a.start, b.start));
    PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end)); // leaves first on top
    for (Interval interval : sorted) {
      while (!pq.isEmpty() && interval.start > pq.peek().end) { // left before, not at, this arrival
        pq.poll();
      }
      pq.offer(interval);
      min = max(min, pq.size()); // trains standing at this arrival time
    }
    return min;
  }

  public static void main(String[] args) {
    List<Interval> list = new ArrayList<>();
    list.add(new Interval(900, 910));
    list.add(new Interval(940, 1200));
    list.add(new Interval(950, 1120));
    list.add(new Interval(1100, 1130));
    list.add(new Interval(1500, 1900));
    list.add(new Interval(1800, 2000));
    System.out.println(minPlatforms(list)); // 3
  }
}
