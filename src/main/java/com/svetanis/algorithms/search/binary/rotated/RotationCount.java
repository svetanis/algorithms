package com.svetanis.algorithms.search.binary.rotated;

public final class RotationCount {

  // Consider an array of distinct numbers sorted in increasing order.
  // The array has been rotated (anti-clockwise) k number of times.
  // Given such an array, find the value of k.
  
  // number of rotations is equal to index of minimum element

  public static int count(int[] a) {
    int n = a.length;
    return count(a, 0, n - 1);
  }

  public static int count(int[] a, int low, int high) {
    // time complexity: O(log n)

    if (high < low) {
      return 0;
    }

    if (high == low) {
      return low;
    }

    int mid = low + (high - low) / 2;

    if (mid < high && a[mid + 1] < a[mid]) {
      return mid + 1;
    }

    if (mid > low && a[mid] < a[mid - 1]) {
      return mid;
    }

    if (a[high] > a[mid]) {
      return count(a, low, mid - 1);
    } else {
      return count(a, mid + 1, high);
    }
  }

  public static void main(String[] args) {
    int[] a = { 15, 18, 2, 3, 6, 12 };
    System.out.println(count(a));
  }
}