package com.svetanis.algorithms.search.binary.rotated;

// Consider an array of distinct numbers sorted in increasing order.
// The array has been rotated (anti-clockwise) k number of times.
// Given such an array, find the value of k.

// the number of rotations is the index of the minimum element.
// same first-true search as RotationCountNoDuplicatesIterative,
// written as a recursion: a[i] <= a[last] reads F F F F T T T

public final class RotationCountNoDuplicatesRecursive {

  public static int count(int[] a) {
    // time complexity: O(log n)

    int last = a.length - 1;
    return count(a, 0, last, last);
  }

  private static int count(int[] a, int left, int right, int last) {
    // left >= right, not ==, so this mirrors the iterative version's
    // while (left < right) exactly -- including on an empty array,
    // where both start at left = 0, right = -1 and return 0 without reading a[]
    if (left >= right) {
      return left;
    }
    int mid = left + (right - left) / 2;
    if (a[mid] <= a[last]) {
      return count(a, left, mid, last);
    }
    return count(a, mid + 1, right, last);
  }

  public static void main(String[] args) {
    int[] a1 = { 15, 18, 2, 3, 6, 12 };
    System.out.println(count(a1)); // 2

    int[] a2 = { 4, 5, 6, 7, 0, 1, 2 };
    System.out.println(count(a2)); // 4

    int[] a3 = { 1, 2, 3, 4 };
    System.out.println(count(a3)); // 0
  }
}
