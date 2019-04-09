package com.svetanis.algorithms.search.quickselect;

import static com.svetanis.algorithms.sorting.quicksort.impl.Partition.reversePartition;
import static com.svetanis.java.base.utils.Random.randomIndex;

public final class KthLargestQuickSelectRandomized {

  public static int select(int[] a, int k) {
    // Time complexity: O(n)
    // Worst case: O(n^2)

    int left = 0;
    int right = a.length - 1;
    return select(a, left, right, k);
  }

  private static int select(int[] a, int left, int right, int k) {
    int random = randomIndex(left, right);
    int pivot = reversePartition(a, left, right, random);
    int dist = pivot - left + 1;
    if (dist == k) {
      return a[pivot];
    } else if (dist > k) {
      return select(a, left, pivot - 1, k);
    } else {
      return select(a, pivot + 1, right, k - dist);
    }
  }

  public static void main(String[] args) {
    int[] a = { 6, 7, 3, 1, 5, 9, 11 };
    System.out.println(select(a, 4));
  }
}