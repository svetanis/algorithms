package com.svetanis.algorithms.search.kthelement;

import static com.svetanis.algorithms.sorting.quicksort.impl.Partition.reversePartition;
import static com.svetanis.java.base.utils.Random.randomIndex;

public final class KthLargestQuickSelectRandomized {

  public static int select(int[] a, int k) {
    // Time complexity: O(n)
    // Worst case: O(n^2)

    int n = a.length;
    return select(a, 0, n - 1, k);
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