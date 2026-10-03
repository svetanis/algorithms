package com.svetanis.algorithms.intervals.overlap;

import static java.lang.Math.max;

// Minimum number of railway platforms so no train waits.
//
// in[i] and out[i] are the arrival and departure times of train i, in[i] <= out[i]. Returns the
// fewest platforms that give every train a platform from its arrival to its departure. A train
// holds its platform at both of those moments, so when one train departs at the same time
// another arrives, the two cannot share a platform. Times are clock readings such as 0930,
// so they are small non-negative integers; a negative time throws.
//
// This version does not sort. Because the times are small, it keeps one slot per minute of
// the clock, marks[t]. A train adds +1 at its arrival minute and -1 at the minute after its
// departure. Adding the slots up from the start of the day, the running total at minute t is
// the number of trains standing at the station at t, and the largest running total is the
// answer. Putting the -1 one minute late is what keeps a departing train counted at the minute
// another train arrives.

public final class MinPlatformsDifferenceArray {
  // Time Complexity: O(n + T), T = latest departure; one pass over the trains, one over the clock
  // Space Complexity: O(T) for the slots

  public static int count(int[] in, int[] out) {
    if (in.length == 0) {
      return 0;
    }

    // slots 0 .. latest departure + 1, so the last release has a slot to land in
    int[] marks = new int[latest(out) + 2];

    for (int i = 0; i < in.length; i++) {
      marks[in[i]]++;       // a train arrives: one more platform in use
      marks[out[i] + 1]--;  // its platform is free only AFTER its departure minute
    }

    int standing = 0;
    int max = 0;
    for (int mark : marks) {
      standing += mark;
      max = max(max, standing);
    }
    return max;
  }

  private static int latest(int[] out) {
    int latest = 0;
    for (int time : out) {
      latest = max(latest, time);
    }
    return latest;
  }

  public static void main(String[] args) {
    int[] in = { 900, 940, 950, 1100, 1500, 1800 };
    int[] out = { 910, 1200, 1120, 1130, 1900, 2000 };
    System.out.println(count(in, out));

    // the statement's own example
    int[] arr = { 1000, 935, 1100 };
    int[] dep = { 1200, 1240, 1130 };
    System.out.println(count(arr, dep));

    // a train departs exactly as the next arrives: two platforms, not one
    int[] touch = { 900, 1000 };
    int[] touchOut = { 1000, 1100 };
    System.out.println(count(touch, touchOut));

    int[] none = {};
    System.out.println(count(none, none));
  }
}
