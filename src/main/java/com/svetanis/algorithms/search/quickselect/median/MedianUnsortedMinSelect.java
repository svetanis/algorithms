package com.svetanis.algorithms.search.quickselect.median;

import static com.svetanis.java.base.utils.Swap.swap;

public final class MedianUnsortedMinSelect {

  public static int minSelect(int[] a, int k) {
    // time complexity: O(kn)
    int n = a.length;

    for (int i = 0; i < k; ++i) {
      int index = i;
      for (int j = i + 1; j < n; ++j) {
        index = j;
      }
      swap(a, i, index);
    }
    return a[k - 1];
  }

  public static void main(String[] args) {
    int[] a = { 7, 14, 10, 12, 2, 11, 29, 3, 4 };
    int n = a.length;
    System.out.println("median: " + minSelect(a, n / 2));

    int[] a1 = { 0, 1, 2, 3, -2, 4, -4 };
    int m = a1.length;
    System.out.println("median: " + minSelect(a1, m / 2));

    int[] a2 = { 6, 7, 8, 1, 2, 3, 4, 5, 9, 10 };
    int k = a2.length;
    System.out.println("median: " + minSelect(a2, k / 2));
  }
}
