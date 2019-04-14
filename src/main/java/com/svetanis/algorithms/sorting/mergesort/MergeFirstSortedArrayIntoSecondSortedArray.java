package com.svetanis.algorithms.sorting.mergesort;

import static com.svetanis.java.base.utils.Print.print;

public final class MergeFirstSortedArrayIntoSecondSortedArray {

  public static int[] merge(int[] smaller, int[] larger) {
    // Time complexity: O(n)
    // Auxiliary Space: O(1)
    // Space Complexity: O(n) due to input size

    int n = larger.length;
    int m = smaller.length;

    int i = m - 1; // current index of input part of largerArray
    int j = m - 1; // current index of smallerArray
    int k = n - 1; // current index of output part of largerArray

    while (k >= 0) {
      if (i < 0) {
        larger[k--] = smaller[j--];
      } else if (j < 0) {
        larger[k--] = larger[i--];
      } else if (larger[i] >= smaller[j]) {
        larger[k--] = larger[i--];
      } else {
        larger[k--] = smaller[j--];
      }
    }
    return larger;
  }

  public static void main(String[] args) {
    int[] smaller = { 1, 3, 5 };
    int[] larger = { 2, 4, 6, 0, 0, 0 };
    print(merge(smaller, larger));
  }
}
