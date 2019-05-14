package com.svetanis.algorithms.sorting.mergesort.disjoined;

import static java.util.Arrays.sort;

public final class DisjointSetMerging {

  public static boolean disjoint(int[] a1, int[] a2) {
    // Time complexity: O(n log n + m log m)
    // if already sorted, then O(n + m)

    int n = a1.length;
    int m = a2.length;

    sort(a1);
    sort(a2);

    int i = 0;
    int j = 0;
    while (i < n && j < m) {
      if (a1[i] < a2[j]) {
        i++;
      } else if (a2[j] < a1[i]) {
        j++;
      } else { // a1[i] == a2[j]
        return false;
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