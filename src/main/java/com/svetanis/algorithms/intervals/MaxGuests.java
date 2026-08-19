package com.svetanis.algorithms.intervals;

import static java.util.Arrays.sort;

import com.svetanis.java.base.Pair;

// a register logs each guest's entry and exit time. find the time at which
// the most guests are at the party, and how many. entries are not sorted.

// a guest whose exit is logged at time t IS counted as present at t, so a
// guest arriving at t and one leaving at t are both at the party -- hence
// `in[i] <= out[j]`. this matches MinPlatforms, whose statement is explicit
// about the same moment, and it matches the worked trace of the source's
// first example. NOTE: the source's own second and third examples disagree
// with its first about this, so the examples cannot all be satisfied; the
// consistent reading is the one implemented here.

public final class MaxGuests {
  // Time Complexity: O(n*log n)

  public static Pair<Integer, Integer> count(int[] in, int[] out) {
    int n = in.length;

    sort(in);
    sort(out);

    int i = 1;
    int j = 0;
    int current = 1;
    int max = 1;
    int time = in[0];
    while (i < n && j < n) {
      if (in[i] <= out[j]) {
        current++;
        if (current > max) {
          max = current;
          time = in[i];
        }
        i++;
      } else {
        current--;
        j++;
      }
    }
    return Pair.build(max, time);
  }

  public static void main(String[] args) {
    int[] in = { 1, 2, 10, 5, 5 };
    int[] out = { 4, 5, 12, 9, 12 };
    System.out.println(count(in, out));
  }
}