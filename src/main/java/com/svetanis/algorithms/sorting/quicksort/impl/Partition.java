package com.svetanis.algorithms.sorting.quicksort.impl;

import static com.svetanis.java.base.utils.Random.randomIndex;
import static com.svetanis.java.base.utils.Swap.swap;

public final class Partition {

  public static int randomizedPartition(int[] a, int left, int right) {
    int index = randomIndex(left, right);
    return partition(a, left, right, index);
  }

  // smaller elements to the left
  public static int partition(int[] a, int left, int right, int index) {
    int i = left - 1;
    swap(a, right, index);
    int pivot = a[right];
    for (int j = left; j < right; ++j) {
      if (a[j] <= pivot) {
        i++;
        swap(a, i, j);
      }
    }
    // move pivot to its final place
    swap(a, i + 1, right);
    return i + 1;
  }

  // larger elements to the left
  public static int reversePartition(int[] a, int left, int right, int index) {
    int i = left;
    int pivot = a[index];
    swap(a, index, right);     // move pivot to end
    for (int j = left; j < right; ++j) {
      if (a[j] > pivot) {
        swap(a, j, i);
        i++;
      }
    }
    // move pivot to its final place
    swap(a, right, i);
    return i;
  }

}
