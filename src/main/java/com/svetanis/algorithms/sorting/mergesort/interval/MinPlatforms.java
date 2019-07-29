package com.svetanis.algorithms.sorting.mergesort.interval;

import static java.lang.Math.max;
import static java.util.Arrays.sort;

public final class MinPlatforms {

  public static int count(int[] in, int[] out) {
    int n = in.length;
    
    sort(in);
    sort(out);
    
    int i = 1;
    int j = 0;
    int current = 1;
    int max = 1;
    while (i < n && j < n) {
      if (in[i] < out[j]) {
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
  }
}