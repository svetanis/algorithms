package com.svetanis.algorithms.search.binary.rotated;

// Consider an array of distinct numbers sorted in increasing order.
// The array has been rotated (anti-clockwise) k number of times.
// Given such an array, find the value of k.

// number of rotations is equal to index of minimum element

public final class RotationCountNoDuplicatesRecursive {

  public static int count(int[] a) {
    int n = a.length;
    return count(a, 0, n - 1);
  }

  public static int count(int[] a, int left, int right) {
    // time complexity: O(log n)

    if (right < left) {
      return 0;
    }

    if (right == left) {
      return left;
    }

    int mid = left + (right - left) / 2;

    if (mid < right && a[mid + 1] < a[mid]) {
      return mid + 1;
    }

    if (mid > left && a[mid] < a[mid - 1]) {
      return mid;
    }

    if (a[right] > a[mid]) {
	// right side is sorted,
	// pivot is on the left side
      return count(a, left, mid - 1);
    } else {
	// left side is sorted,
	// pivot is on the right side
      return count(a, mid + 1, right);
    }
  }

  public static void main(String[] args) {
    int[] a = { 15, 18, 2, 3, 6, 12 };
    System.out.println(count(a));
  }
}