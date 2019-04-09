package com.svetanis.algorithms.sorting.quicksort.impl;

import static com.svetanis.algorithms.sorting.quicksort.impl.Partition.randomizedPartition;
import static com.svetanis.java.base.utils.Print.print;

public final class QuickSortRandomized {

  public static void sort(int[] a, int left, int right) {
    if (left < right) {
      int pivot = randomizedPartition(a, left, right);
      sort(a, left, pivot - 1);
      sort(a, pivot + 1, right);
    }
  }

  public static void main(String[] args) {
    int[] a = { 1000, 80, 10, 50, 70, 60, 90, 20, 30, 40, 0, -1000 };
    sort(a, 0, a.length - 1);
    print(a);
  }
}