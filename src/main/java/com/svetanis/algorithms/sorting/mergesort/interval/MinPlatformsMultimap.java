package com.svetanis.algorithms.sorting.mergesort.interval;

import static com.svetanis.java.base.collect.Lists.sort;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

// given a list of intervals representing 
// the arrival and departure times of trains, 
// find min number of platforms required for 
// the train station so that no train has to wait.

public final class MinPlatformsMultimap {

  public static int count(int[] in, int[] out) {
    int n = in.length;
    Multimap<Integer, Character> multimap = ArrayListMultimap.create();
    for (int i = 0; i < n; i++) {
      multimap.put(in[i], 'a');
      multimap.put(out[i], 'd');
    }

    int max = MIN_VALUE;
    int count = 0;
    for (int key : sort(multimap.keySet())) {
      Iterable<Character> iterables = multimap.get(key);
      for (char item : iterables) {
        if (item == 'a') {
          count++;
        } else {
          count--;
        }
        max = max(max, count);
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