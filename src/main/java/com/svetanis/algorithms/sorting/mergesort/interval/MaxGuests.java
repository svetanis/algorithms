package com.svetanis.algorithms.sorting.mergesort.interval;

import static java.util.Arrays.sort;

import com.svetanis.java.base.Pair;

// given a list of intervals, find the point
// where the max number of intervals overlap

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