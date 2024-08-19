package com.svetanis.algorithms.search.binary.rotated;

import static com.svetanis.algorithms.search.binary.BinarySearchRecursive.binarySearch;
import static com.svetanis.algorithms.search.binary.rotated.RotatedPivot.pivot;

public final class RotatedGivenRecursivePivot {

  public static int search(int[] a, int k) {
    // Time Complexity: O(log n)

    int n = a.length;
    int pivot = pivot(a);

    if (pivot == -1) {
      return binarySearch(a, k);
    }

    if (a[pivot] == k) {
      return pivot;
    }

    if (a[0] <= k) {
      return binarySearch(a, 0, pivot - 1, k);
    } else {
      return binarySearch(a, pivot + 1, n - 1, k);
    }
  }

  public static void main(String[] args) {
    int[] a1 = { 5, 6, 7, 8, 9, 10, 1, 2, 3 };
    System.out.println(search(a1, 3));

    int[] a2 = { 3, 4, 5, 1, 2 };
    System.out.println(search(a2, 3));
    System.out.println(search(a2, 4));

    int[] a3 = { 1, 2, 3, 4 };
    System.out.println(search(a3, 3));
  }
}