package com.svetanis.algorithms.search.binary.bitonic;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import com.google.common.base.Optional;

// given a bitonic array,
// find bitonic point in it

// bitonic point is a point in bitonic array
// before which elements are strictly increasing
// and after which are strictly decreasing

public final class BitonicPointBinary {

  public static Optional<Integer> bitonicPoint(int[] a) {
    // Time Complexity: O(log n)

    int index = bitonicPoint(a, 0, a.length - 1);
    return index != -1 ? of(a[index]) : absent();
  }

  public static int bitonicPoint(int[] a, int left, int right) {
    if (left > right) {
      return -1;
    }

    int mid = left + (right - left) / 2;
    if (a[mid - 1] < a[mid] && a[mid + 1] < a[mid]) {
      return mid;
    }

    if (a[mid] < a[mid + 1]) {
      return bitonicPoint(a, mid + 1, right);
    } else {
      return bitonicPoint(a, left, mid - 1);
    }
  }

  public static void main(String[] args) {
    int[] a1 = { 6, 7, 8, 11, 9, 5, 2, 1 };
    int[] a2 = { -3, -2, 4, 6, 10, 8, 7, 1 };
    System.out.println(bitonicPoint(a1));
    System.out.println(bitonicPoint(a2));
  }
}
