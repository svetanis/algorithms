package com.svetanis.algorithms.intervals.overlap;

import static java.util.Arrays.sort;

import java.util.Arrays;

// The time at which the most guests are at a party, and how many.
//
// in[i] and out[i] are the entry and exit times of guest i, in[i] <= out[i], not sorted, at
// least one guest. Returns { most guests present at once, earliest time that many are
// present }. A guest is present at both the entry and the exit time, so a guest leaving at t
// and one arriving at t are both counted at t. The source's own examples disagree on that
// moment; this reading is the one MinPlatformsTwoArrays states outright, so the two files agree.
// Sorts both arrays in place.
//
// This is the same walk as MinPlatformsTwoArrays, and it also reports the time. Entries and exits
// are sorted separately and walked together, always taking the earlier of the next entry and the
// next exit. The guests present at any moment are the entries so far minus the exits so far;
// current is that number. It only rises at an entry, so the time of the peak is the entry
// time at which current first reaches its largest value.
//
// MaxGuestsPriorityQueue keeps the guests themselves on a heap, and so reports the count only.

public final class MaxGuestsTwoArrays {
  // Time Complexity: O(n log n), the two sorts; the walk after them is one pass
  // Space Complexity: O(1) beyond the sort, both arrays are sorted in place

  public static int[] count(int[] in, int[] out) {
    int n = in.length;

    sort(in);
    sort(out);

    int i = 1;
    int j = 0;
    int current = 1; // in[0] is counted: the earliest entry is no later than any exit
    int max = 1;
    int time = in[0]; // throws on an empty register
    while (i < n && j < n) {
      if (in[i] <= out[j]) { // tie: entry first, the leaving guest is still present
        current++;
        if (current > max) { // strictly more, so the earliest time of the peak is kept
          max = current;
          time = in[i];
        }
        i++;
      } else {
        current--;
        j++;
      }
    }
    return new int[] { max, time };
  }

  public static void main(String[] args) {
    int[] in = { 1, 2, 10, 5, 5 };
    int[] out = { 4, 5, 12, 9, 12 };
    System.out.println(Arrays.toString(count(in, out))); // [3, 5]
  }
}
