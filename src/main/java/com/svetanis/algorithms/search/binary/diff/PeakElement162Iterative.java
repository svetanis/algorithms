package com.svetanis.algorithms.search.binary.diff;

// 162. Find Peak Element

// A peak element is an element that
// is strictly greater than its neighbors

public final class PeakElement162Iterative {
  // Time Complexity: O(log n)

  public static int peak(int[] a) {
    int left = 0;
    int right = a.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (a[mid] > a[mid + 1]) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 3, 1 };
    System.out.println(peak(a1)); // 2

    int[] a2 = { 1, 2, 1, 3, 5, 6, 4 };
    System.out.println(peak(a2)); // 5
  }
}