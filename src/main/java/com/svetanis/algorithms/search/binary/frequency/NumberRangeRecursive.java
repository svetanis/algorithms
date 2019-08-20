package com.svetanis.algorithms.search.binary.frequency;

import static com.svetanis.algorithms.search.binary.frequency.FirstOccurrenceBinaryRecursive.firstOccurrence;
import static com.svetanis.algorithms.search.binary.frequency.LastOccurrenceBinaryRecursive.lastOccurrence;

import com.svetanis.java.base.Pair;

public final class NumberRangeRecursive {

  public static Pair<Integer, Integer> count(int[] a, int x) {
    // Time Complexity: O(log n)

    int first = firstOccurrence(a, x);
    if (first != -1) {
      int last = lastOccurrence(a, x);
      return Pair.build(first, last);
    }
    return Pair.build(-1, -1);
  }

  public static void main(String[] args) {
    int[] a = { 4, 6, 6, 6, 9 };
    System.out.println(count(a, 6));

    int[] a1 = { 1, 3, 8, 10, 15 };
    System.out.println(count(a1, 10));
    System.out.println(count(a1, 12));
  }
}