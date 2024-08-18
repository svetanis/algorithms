package com.svetanis.algorithms.search.binary;

import static java.util.Arrays.sort;

public final class BinarySearchIterative {

  public static int binarySearch(int[] a, int x) {
    // Time Complexity: O(log n)

    int n = a.length;
    return binarySearch(a, 0, n - 1, x);
  }

  public static int binarySearch(int[] a, int left, int right, int x) {
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (a[mid] == x) {
        return mid;
      } else if (a[mid] > x) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }

  public static int descendingBinarySearch(int[] a, int left, int right, int x) {
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (a[mid] == x) {
        return mid;
      } else if (a[mid] < x) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] a = { 1, 30, 40, 50, 60, 70, 23, 20 };
    sort(a);
    System.out.println(binarySearch(a, 1));
  }
}