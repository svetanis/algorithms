package com.svetanis.algorithms.search.binary.frequency;

import static com.svetanis.java.base.utils.Print.print;

// Given an sorted array of positive integers, count number of occurrences for each element in the array. 
// Assume all elements in the array are less than some constant M (much smaller than n).

public final class FrequencyOfEachElement {

  public static int[] frequency(int[] a) {
    // Time Complexity: O(m log n)
    // m is highest value in array

    int n = a.length;
    int high = n - 1;
    int[] f = new int[a[high] + 1];
    binary(a, 0, high, f);
    return f;
  }

  private static void binary(int[] a, int low, int high, int[] f) {
    if (a[low] == a[high]) {
      f[a[low]] += high - low + 1;
    } else {
      int mid = low + (high - low) / 2;
      binary(a, low, mid, f);
      binary(a, mid + 1, high, f);
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 1, 1, 2, 3, 3, 5, 5, 8, 8, 8, 9, 9, 10 };
    print(frequency(a));
  }
}