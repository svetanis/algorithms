package com.svetanis.algorithms.search.binary.frequency;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import com.google.common.base.Optional;

// given a sorted array consisting of 0's and 1's
// find the index of first '1' in the sorted array

public final class FirstOccurrenceOfOne {

  public static Optional<Integer> firstOccurrence(int[] a) {
    int n = a.length;
    return firstOccurrence(a, 0, n - 1);
  }

  public static Optional<Integer> firstOccurrence(int[] a, int left, int right) {
    // O(log n)

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if ((mid == 0 || a[mid - 1] == 0) && a[mid] == 1) {
        return of(mid);
      } else if (a[mid] == 1) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return absent();
  }

  public static void main(String[] args) {
    int[] a = { 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 };
    System.out.println(firstOccurrence(a));
  }
}
