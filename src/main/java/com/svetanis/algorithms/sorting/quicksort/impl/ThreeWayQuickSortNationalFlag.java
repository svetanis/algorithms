package com.svetanis.algorithms.sorting.quicksort.impl;

import static com.svetanis.java.base.utils.Arrays.shuffle;
import static com.svetanis.java.base.utils.Print.print;
import static com.svetanis.java.base.utils.Swap.swap;
import static org.apache.commons.lang3.ArrayUtils.toObject;

import com.svetanis.java.base.Pair;

public final class ThreeWayQuickSortNationalFlag {

  public static void sort(int[] a) {
    shuffle(a);
    int n = a.length;
    sort(a, 0, n - 1);
  }

  private static void sort(int[] a, int left, int right) {
    if (right <= left) {
      return;
    }
    Pair<Integer, Integer> pivot = partition(a, left, right);
    sort(a, left, pivot.getLeft() - 1);
    sort(a, pivot.getRight() + 1, right);
  }

  private static Pair<Integer, Integer> partition(int[] a, int left, int right) {

    int smaller = left;
    int larger = right;
    int equal = left;
    int pivot = a[left];

    while (equal <= larger) {
      if (a[equal] < pivot) {
        swap(a, smaller, equal);
        smaller++;
        equal++;
      } else if (a[equal] > pivot) {
        swap(a, equal, larger);
        larger--;
      } else
        equal++;
    }
    return Pair.build(smaller, larger);
  }

  public static void main(String[] args) {
    int[] a = { 4, 9, 2, 4, 1, 9, 4, 2, 9, 4, 2, 1, 2 };
    sort(a);
    print(toObject(a));
  }
}
