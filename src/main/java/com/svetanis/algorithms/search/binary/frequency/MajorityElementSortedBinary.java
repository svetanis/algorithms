package com.svetanis.algorithms.search.binary.frequency;

import static com.svetanis.algorithms.search.binary.frequency.FirstOccurrenceBinaryRecursive.firstOccurrence;

public final class MajorityElementSortedBinary {

  public static boolean isMajority(int[] a, int x) {
    // Time Complexity: O(log n)

    int n = a.length;

    int index = firstOccurrence(a, x);

    if (index == -1) {
      return false;
    }

    // check if the element is present more than n/2 times
    return index + n / 2 < n && a[index + n / 2] == x;
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3, 3, 3, 3, 10 };
    System.out.println(isMajority(a, 3));
  }
}