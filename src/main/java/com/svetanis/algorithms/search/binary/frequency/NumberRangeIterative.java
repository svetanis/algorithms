package com.svetanis.algorithms.search.binary.frequency;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import com.google.common.base.Optional;
import com.svetanis.algorithms.sorting.mergesort.interval.Interval;

// given an array of numbers sorted in ascending order
// find the range of a given number k
// the range of the k will be the first
// and last position of the k in the array

public final class NumberRangeIterative {

  public static Optional<Interval> search(int[] a, int x) {
    // Time Complexity: O(log n)

    int n = a.length;
    int first = search(a, 0, n - 1, x, false);
    if (first != -1) {
      int last = search(a, 0, n - 1, x, true);
      return of(new Interval(first, last));
    }
    return absent();
  }

  private static int search(int[] a, int left, int right, int k, boolean maxIndex) {
    int index = -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (k < a[mid]) {
        right = mid - 1;
      } else if (k > a[mid]) {
        left = mid + 1;
      } else { // a[mid] == k) {
        index = mid;
        if (maxIndex) {
          left = mid + 1;// search right to find the last index
        } else {
          right = mid - 1; // search left to find the first index
        }
      }
    }
    return index;
  }

  public static void main(String[] args) {
    int[] a = { 4, 6, 6, 6, 9 };
    System.out.println(search(a, 6));

    int[] a1 = { 1, 3, 8, 10, 15 };
    System.out.println(search(a1, 10));
    System.out.println(search(a1, 12));
  }
}