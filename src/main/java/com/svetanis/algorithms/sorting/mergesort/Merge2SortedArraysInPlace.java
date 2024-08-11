package com.svetanis.algorithms.sorting.mergesort;

import static com.svetanis.java.base.utils.Print.print;

// given two sorted arrays
// merge these two arrays such that
// the initial numbers are in the first array
// and the remaining numbers are in second array

public final class Merge2SortedArraysInPlace {
  // Time Complexity: O(n + m)

  public static void merge(int[] a1, int[] a2) {
    int n = a1.length;
    int m = a2.length;

    // iterate over every element of a2
    for (int i = m - 1; i >= 0; i--) {
      // store last element	
      int last = a1[n - 1];
      int j = n - 1;
      // loop from last element of a1 while
      // element a1 is smaller than a2
      while (j >= 0 && a1[j] > a2[i]) {
        j--;
        // move element one position ahead
        a1[j + 1] = a1[j];
      }

      if (j != n - 1) {
        a1[j + 1] = a2[i];
        a2[i] = last;
      }
    }
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 5, 9, 10, 15, 20 };
    int[] a2 = { 2, 3, 8, 13 };
    merge(a1, a2);
    print(a1);
    print(a2);
  }
}
