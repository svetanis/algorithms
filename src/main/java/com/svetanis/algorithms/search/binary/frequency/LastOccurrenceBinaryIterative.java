package com.svetanis.algorithms.search.binary.frequency;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static java.util.Arrays.sort;

import com.google.common.base.Optional;

public final class LastOccurrenceBinaryIterative {

  public static Optional<Integer> lastOccurrence(int[] a, int k) {
    int n = a.length - 1;
    sort(a);
    return lastOccurrence(a, 0, n - 1, k);
  }

  private static Optional<Integer> lastOccurrence(int[] a, int left, int right, int k) {
    int index = -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (k < a[mid]) {
        right = mid - 1;
      } else if (k > a[mid]) {
        left = mid + 1;
      } else { // a[mid] == k) {
        index = mid;
        left = mid + 1; // search right to find the last index
      }
    }
    return index != -1 ? of(index) : absent();
  }

  public static void main(String[] args) {
    int[] a = { 1, 30, 40, 50, 60, 60, 70, 23, 20 };
    System.out.println(lastOccurrence(a, 60));

    int[] a1 = { 2, 2, 3, 5, 6 };
    System.out.println(lastOccurrence(a1, 2));
  }
}