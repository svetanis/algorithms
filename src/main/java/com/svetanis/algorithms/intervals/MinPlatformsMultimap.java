package com.svetanis.algorithms.intervals;

import static com.svetanis.java.base.collect.Lists.sort;
import static java.lang.Math.max;
import static java.util.Collections.frequency;

import java.util.Collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

// given two arrays holding the arrival and departure time of the i-th train,
// find the min number of platforms required so that no train has to wait.
// if one train departs at the same time another arrives, the two CANNOT
// share a platform -- so an arrival is counted before a departure at that time.

public final class MinPlatformsMultimap {

  private static final char ARRIVAL = 'a';
  private static final char DEPARTURE = 'd';

  public static int count(int[] in, int[] out) {
    Multimap<Integer, Character> events = events(in, out);

    int max = 0;
    int count = 0;
    for (int time : sort(events.keySet())) {
      Collection<Character> marks = events.get(time);
      // a train departing at t does not free its platform for a train arriving at t, so every
      // arrival is counted -- and the peak read -- BEFORE any departure at the same time.
      // applying them as GROUPS is what makes the answer independent of the order the trains
      // were listed in; reading the marks one at a time makes it depend on that order.
      count += frequency(marks, ARRIVAL);
      max = max(max, count);
      count -= frequency(marks, DEPARTURE);
    }
    return max;
  }

  private static Multimap<Integer, Character> events(int[] in, int[] out) {
    Multimap<Integer, Character> events = ArrayListMultimap.create();
    for (int i = 0; i < in.length; i++) {
      events.put(in[i], ARRIVAL);
      events.put(out[i], DEPARTURE);
    }
    return events;
  }

  public static void main(String[] args) {
    int[] in = { 900, 940, 950, 1100, 1500, 1800 };
    int[] out = { 910, 1200, 1120, 1130, 1900, 2000 };
    System.out.println(count(in, out));

    int[] none = {};
    System.out.println(count(none, none));
  }
}
