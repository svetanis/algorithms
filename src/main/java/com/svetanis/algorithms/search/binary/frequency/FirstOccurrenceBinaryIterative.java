package com.svetanis.algorithms.search.binary.frequency;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static java.util.Arrays.sort;

import com.google.common.base.Optional;

public final class FirstOccurrenceBinaryIterative {

  public static Optional<Integer> firstOccurrence(int[] a, int k) {
    sort(a);
    return firstOccurrence(a, 0, a.length - 1, k);
  }

  private static Optional<Integer> firstOccurrence(int[] a, int left, int right, int k) {
    int index = -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (k < a[mid]) {
        right = mid - 1;
      } else if (k > a[mid]) {
        left = mid + 1;
      } else { // a[mid] == k) {
        index = mid;
        right = mid - 1; // search left to find the first index
      }
    }
    return index != -1 ? of(index) : absent();
  }

  public static void main(String[] args) {
    int[] a = { 1, 30, 40, 50, 60, 70, 23, 20 };
    System.out.println(firstOccurrence(a, 23));
  }
}