package com.svetanis.algorithms.search.quickselect;

import static com.svetanis.algorithms.sorting.quicksort.impl.Partition.randomizedPartition;

public final class KthSmallestQuickSelectRandomized {

  public static int select(int[] a, int k) {
    // Time complexity: O(n)
    // Worst case: O(n^2)

    int n = a.length;
    return select(a, 0, n - 1, k);
  }

  private static int select(int[] a, int left, int right, int k) {
    if (left < right) {
      int pivot = randomizedPartition(a, left, right);
      int dist = pivot - left + 1;
      if (dist == k) {
        return a[pivot];
      } else if (k < dist) {
        return select(a, left, pivot, k);
      } else {
        return select(a, pivot + 1, right, k - dist);
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] a = { 12, 3, 5, 7, 4, 19, 26 };
    System.out.println(select(a, 4));

    int[] a2 = { 8, 1, 6, 4, 0, 3, 9, 5 };
    System.out.println(select(a2, 5));
  }
}