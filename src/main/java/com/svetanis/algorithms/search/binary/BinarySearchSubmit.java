package com.svetanis.algorithms.search.binary;

// 704. Binary Search
// com.svetanis.algorithms.search.binary.BinarySearchIterative
// com.svetanis.algorithms.search.binary.BinarySearchRecursive

public final class BinarySearchSubmit {
  // Time Complexity: O(log n)

  public static int binarySearch(int[] a, int target) {
    int left = 0;
    int right = a.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (a[mid] == target) {
        return mid;
      } else if (a[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] a = { -1, 0, 3, 5, 9, 12 };
    System.out.println(binarySearch(a, 9)); // 4
    System.out.println(binarySearch(a, 2)); // -1
  }
}