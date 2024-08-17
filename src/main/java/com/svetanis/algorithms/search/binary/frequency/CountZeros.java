package com.svetanis.algorithms.search.binary.frequency;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import com.google.common.base.Optional;

// given an array of 1s and 0s which has 
// all 1s first followed by all 0s
// count the number of zeros in the given array

public final class CountZeros {

  public static int countZeros(int[] a) {
    int n = a.length;
    Optional<Integer> first = firstZero(a, 0, n - 1);
    if (first.isPresent()) {
      return n - first.get();
    }
    return 0;
  }

  private static Optional<Integer> firstZero(int[] a, int left, int right) {
    // O(log n)

    if (right < left) {
      return absent();
    }
    int mid = left + (right - left) / 2;
    if ((mid == 0 || a[mid - 1] == 1) && a[mid] == 0) {
      return of(mid);
    }
    // if mid element is not 0
    if (a[mid] == 1) {
      return firstZero(a, mid + 1, right);
    } else { // if mid element is 0, but not first 0
      return firstZero(a, left, mid - 1);
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 1, 1, 0, 0, 0, 0, 0 };
    System.out.println(countZeros(a));
  }
}