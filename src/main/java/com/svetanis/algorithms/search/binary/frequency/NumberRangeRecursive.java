package com.svetanis.algorithms.search.binary.frequency;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.svetanis.algorithms.search.binary.frequency.FirstOccurrenceBinaryRecursive.firstOccurrence;
import static com.svetanis.algorithms.search.binary.frequency.LastOccurrenceBinaryRecursive.lastOccurrence;

import com.google.common.base.Optional;
import com.svetanis.algorithms.sorting.mergesort.interval.Interval;

// given an array of numbers sorted in ascending order
// find the range of a given number k
// the range of the k will be the first
// and last position of the k in the array

public final class NumberRangeRecursive {

  public static Optional<Interval> count(int[] a, int x) {
    // Time Complexity: O(log n)

    int first = firstOccurrence(a, x);
    if (first != -1) {
      int last = lastOccurrence(a, x);
      return of(new Interval(first, last));
    }
    return absent();
  }

  public static void main(String[] args) {
    int[] a = { 4, 6, 6, 6, 9 };
    System.out.println(count(a, 6));

    int[] a1 = { 1, 3, 8, 10, 15 };
    System.out.println(count(a1, 10));
    System.out.println(count(a1, 12));
  }
}