package com.svetanis.algorithms.sorting.mergesort;

import static com.svetanis.java.base.utils.Print.print;

public final class AlternateMerge {

  public static int[] merge(int[] a1, int[] a2) {
    int n = a1.length;
    int m = a2.length;

    int[] a = new int[n + m];
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < n && j < m) {
      a[k++] = a1[i++];
      a[k++] = a2[j++];
    }

    while (i < n) {
      a[k++] = a1[i++];
    }

    while (j < m) {
      a[k++] = a2[j++];
    }
    return a;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 5, 7, 9, 11 };
    int[] a2 = { 2, 4, 6, 8 };
    print(merge(a1, a2));
  }
}
