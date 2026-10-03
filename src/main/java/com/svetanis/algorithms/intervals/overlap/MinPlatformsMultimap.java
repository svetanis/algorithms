package com.svetanis.algorithms.intervals.overlap;

import static java.lang.Math.max;
import static java.util.Collections.frequency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Minimum number of railway platforms so no train waits.
//
// in[i] and out[i] are the arrival and departure times of train i, in[i] <= out[i]. Returns the
// fewest platforms that give every train a platform from its arrival to its departure. A train
// holds its platform at both of those moments, so when one train departs at the same time
// another arrives, the two cannot share a platform.
//
// This version groups the events by time. One key of the map is a clock time; its value is a
// list of marks, one per train event at that time, 'a' for an arrival and 'd' for a departure. The
// distinct times are visited in increasing order with count = trains in the station. At each
// time, all its arrivals are added and the peak is read, and only then are its departures
// taken away, so a train leaving at t is still counted when another arrives at t. Handling a
// whole time at once also means the order the trains were listed in cannot change the answer.
//
// The same count other ways: MinPlatformsTwoArrays, MinPlatformsDifferenceArray,
// MinPlatformsPriorityQueue and MaxOverlappingIntervals.

public final class MinPlatformsMultimap {
  // Time Complexity: O(n log n), the TreeMap keeps the distinct times in order; each mark is
  // counted once
  // Space Complexity: O(n), two marks per train

  private static final char ARRIVAL = 'a';
  private static final char DEPARTURE = 'd';

  public static int count(int[] in, int[] out) {
    Map<Integer, List<Character>> events = events(in, out);

    int max = 0;
    int count = 0;
    for (List<Character> marks : events.values()) { // times in increasing order
      // every arrival at t is counted, and the peak read, before any departure at t
      count += frequency(marks, ARRIVAL);
      max = max(max, count);
      count -= frequency(marks, DEPARTURE);
    }
    return max;
  }

  private static Map<Integer, List<Character>> events(int[] in, int[] out) {
    Map<Integer, List<Character>> events = new TreeMap<>(); // a multimap: one time, many marks
    for (int i = 0; i < in.length; i++) {
      events.computeIfAbsent(in[i], k -> new ArrayList<>()).add(ARRIVAL);
      events.computeIfAbsent(out[i], k -> new ArrayList<>()).add(DEPARTURE);
    }
    return events;
  }

  public static void main(String[] args) {
    int[] in = { 900, 940, 950, 1100, 1500, 1800 };
    int[] out = { 910, 1200, 1120, 1130, 1900, 2000 };
    System.out.println(count(in, out)); // 3

    int[] none = {};
    System.out.println(count(none, none)); // 0
  }
}
