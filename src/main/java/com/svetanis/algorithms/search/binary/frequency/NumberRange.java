package com.svetanis.algorithms.search.binary.frequency;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.svetanis.algorithms.search.binary.frequency.FirstOccurrenceBinaryRecursive.firstOccurrence;
import static com.svetanis.algorithms.search.binary.frequency.LastOccurrenceBinaryRecursive.lastOccurrence;

import com.google.common.base.Optional;
import com.svetanis.java.base.Pair;

public final class NumberRange {

  public static Optional<Pair<Integer, Integer>> count(int[] a, int x) {
    // Time Complexity: O(log n)

    int first = firstOccurrence(a, x);
    int last = lastOccurrence(a, x);
    if (first == -1 && last == -1) {
      return absent();
    } else {
      return of(Pair.build(first, last));
    }
  }

  public static void main(String[] args) {
    int[] a = { 4, 6, 6, 6, 9 };
    System.out.println(count(a, 6));

    int[] a1 = { 1, 3, 8, 10, 15 };
    System.out.println(count(a1, 10));
    System.out.println(count(a1, 12));

  }
}