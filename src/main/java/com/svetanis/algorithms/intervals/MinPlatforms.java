package com.svetanis.algorithms.intervals;

import static java.lang.Math.max;
import static java.util.Arrays.sort;

// given two arrays holding the arrival and departure time of the i-th train,
// find the min number of platforms required so that no train has to wait.
// if one train departs at the same time another arrives, the two CANNOT
// share a platform -- so an arrival is counted before a departure at that time.

public final class MinPlatforms {
  // Time Complexity: O(n*log n)

  public static int count(int[] in, int[] out) {
    int n = in.length;
    if (n == 0) {
      return 0;
    }

    sort(in);
    sort(out);

    int i = 1;
    int j = 0;
    int current = 1;
    int max = 1;
    while (i < n && j < n) {
      if (in[i] <= out[j]) {
        max = max(max, ++current);
        i++;
      } else {
        current--;
        j++;
      }
    }
    return max;
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
