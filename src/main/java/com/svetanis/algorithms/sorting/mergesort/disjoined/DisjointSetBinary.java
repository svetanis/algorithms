package com.svetanis.algorithms.sorting.mergesort.disjoined;

import static com.svetanis.algorithms.search.binary.BinarySearchRecursive.isBinary;
import static java.lang.Math.min;
import static java.util.Arrays.sort;

public final class DisjointSetBinary {

  public static boolean disjoint(int[] a1, int[] a2) {
    // Time Complexity: O(m*log m + n*log m)

    sort(a1);
    sort(a2);

    int n = a1.length;
    int m = a2.length;

    int size = min(n, m);
    if (size == n) {
      for (int i = 0; i < n; i++) {
        if (isBinary(a2, 0, m - 1, a1[i])) {
          return false;
        }
      }
    } else {
      for (int j = 0; j < m; j++) {
        if (isBinary(a1, 0, n - 1, a2[j])) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int[] set1 = { 12, 34, 11, 9, 3 };
    int[] set2 = { 2, 1, 3, 5 };

    System.out.println(disjoint(set1, set2));

    int[] set3 = { 12, 34, 11, 9, 3 };
    int[] set4 = { 7, 2, 1, 5 };

    System.out.println(disjoint(set3, set4));
  }
}