package com.svetanis.algorithms.sorting.mergesort;

import com.svetanis.java.base.utils.Print;

// 88. Merge Two Sorted Arrays
// com.svetanis.algorithms.sorting.mergesort.MergeFirstSortedArrayIntoSecondSortedArray

// given two sorted arrays, A and B,
// where A has a large enough buffer
// at the end to hold B
// merge B into A in sorted order

public final class MergeFirstSortedArrayIntoSecondSortedArray88 {
  // Time complexity: O(n)
  // Auxiliary Space: O(1)
  // Space Complexity: O(n) due to input size

  public static void merge(int[] larger, int m, int[] smaller, int n) {

    int i = m - 1; // current index of input part of larger array
    int j = n - 1; // current index of smaller array
    int k = m + n - 1; // current index of output part of larger array

    while (j >= 0) {
      if (i >= 0 && larger[i] > smaller[j]) {
        larger[k] = larger[i];
        i--;
      } else {
        larger[k] = smaller[j];
        j--;
      }
      k--;
    }
  }

  public static void main(String[] args) {
    int[] smaller = { 2, 5, 6 };
    int[] larger = { 1, 2, 3, 0, 0, 0 };
    merge(larger, 3, smaller, 3);
    Print.print(larger);
  }
}
