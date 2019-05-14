package com.svetanis.algorithms.search.binary.frequency;

import static java.util.Arrays.sort;

public final class FirstOccurrenceOfOne {

  public static int firstOccurrence(int[] a) {
    int n = a.length;
    sort(a);
    return firstOccurrence(a, 0, n - 1);
  }

  public static int firstOccurrence(int[] a, int left, int right) {
    // O(log n)

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if ((mid == 0 || a[mid - 1] == 0) && a[mid] == 1) {
        return mid;
      } else if (a[mid] == 1) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] a = { 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 };
    System.out.println(firstOccurrence(a));
  }
}
