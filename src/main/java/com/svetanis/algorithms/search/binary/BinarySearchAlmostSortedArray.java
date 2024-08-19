package com.svetanis.algorithms.search.binary;

// given an array which is sorted, where after sorting
// some elements are moved to either of the adjacent positions
// the element a[i] can only be swapped with either a[i + 1] or a[i - 1]
// find a given element x in this array

public final class BinarySearchAlmostSortedArray {

  public static int search(int[] a, int x) {
    // Time Complexity: O(log n)

    int n = a.length;
    return binary(a, 0, n - 1, x);
  }

  private static int binary(int[] a, int left, int right, int x) {
    
    if (right < left) {
      return -1;
    }

    int mid = left + (right - left) / 2;

    if (a[mid] == x) {
      return mid;
    }

    if (mid > left && a[mid - 1] == x) {
      return mid - 1;
    }

    if (mid < right && a[mid + 1] == x) {
      return mid + 1;
    }

    if (a[mid] > x) {
      return binary(a, left, mid - 2, x);
    } else {
      return binary(a, mid + 2, right, x);
    }
  }

  public static void main(String[] args) {
    int[] a = { 3, 2, 10, 4, 40 };
    System.out.println(search(a, 4));
  }
}