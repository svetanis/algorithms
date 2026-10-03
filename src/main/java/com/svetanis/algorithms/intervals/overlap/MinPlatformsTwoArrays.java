package com.svetanis.algorithms.intervals.overlap;

import static java.lang.Math.max;
import static java.util.Arrays.sort;

// Minimum number of railway platforms so no train waits.
//
// in[i] and out[i] are the arrival and departure times of train i, in[i] <= out[i]. Returns the
// fewest platforms that give every train a platform from its arrival to its departure. A train
// holds its platform at both of those moments, so when one train departs at the same time
// another arrives, the two cannot share a platform. Sorts both arrays in place.
//
// This version sorts the arrivals and the departures separately and walks the two sorted
// arrays together, always taking the earlier of the next arrival and the next departure. The
// sorting forgets which departure belongs to which train, and nothing needs it: the number of
// trains in the station at any moment is the arrivals so far minus the departures so far.
// current is that number, and its largest value is the answer. On equal times the arrival is
// taken first, so the departing train is still counted when the new one comes in.
//
// The same count other ways: MinPlatformsDifferenceArray marks one slot per minute,
// MinPlatformsMultimap groups the events by time, MinPlatformsPriorityQueue keeps the trains on a
// heap, and MaxOverlappingIntervals sorts start and end points together. MaxGuestsTwoArrays is
// this walk that also reports when the peak happens.

public final class MinPlatformsTwoArrays {
  // Time Complexity: O(n log n), the two sorts; the walk after them is one pass
  // Space Complexity: O(1) beyond the sort, both arrays are sorted in place

  public static int count(int[] in, int[] out) {
    int n = in.length;
    if (n == 0) {
      return 0;
    }

    sort(in);
    sort(out);

    int i = 1;
    int j = 0;
    int current = 1; // in[0] is counted: the earliest arrival is no later than any departure
    int max = 1;
    while (i < n && j < n) { // once arrivals run out, current can only fall
      if (in[i] <= out[j]) { // tie: arrival first, the departing train still holds a platform
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
    System.out.println(count(in, out)); // 3

    // the statement's own example
    int[] arr = { 1000, 935, 1100 };
    int[] dep = { 1200, 1240, 1130 };
    System.out.println(count(arr, dep)); // 3

    // a train departs exactly as the next arrives: two platforms, not one
    int[] touch = { 900, 1000 };
    int[] touchOut = { 1000, 1100 };
    System.out.println(count(touch, touchOut)); // 2

    int[] none = {};
    System.out.println(count(none, none)); // 0
  }
}
