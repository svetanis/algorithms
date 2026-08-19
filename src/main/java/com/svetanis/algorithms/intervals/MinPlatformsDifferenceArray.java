package com.svetanis.algorithms.intervals;

import static java.lang.Math.max;

// given two arrays holding the arrival and departure time of the i-th train,
// find the min number of platforms required so that no train has to wait.
// if one train departs at the same time another arrives, the two CANNOT
// share a platform -- so a platform is released at dep + 1, not at dep.

public final class MinPlatformsDifferenceArray {
  // Time Complexity: O(n + range), range = latest departure time
  // Space Complexity: O(range)
  //
  // No sorting at all. Times are clock readings in HHMM, so they are small
  // integers -- and when the values are bounded, COUNTING beats sorting.
  // Mark +1 where a train arrives and -1 just after it leaves, then sweep
  // the clock once adding the marks up: the running total at time t is how
  // many trains are standing at t, and the largest total is the answer.

  public static int count(int[] in, int[] out) {
    if (in.length == 0) {
      return 0;
    }

    // one past the last departure, so the final release has somewhere to land.
    // GeeksforGeeks hardcodes 2361 here because HHMM cannot exceed 2359;
    // reading the bound off the data costs one pass and cannot go out of range.
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
