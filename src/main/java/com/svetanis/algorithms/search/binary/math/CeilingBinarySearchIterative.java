package com.svetanis.algorithms.search.binary.math;

// Given an array of numbers sorted in an ascending order, 
// find the ceiling of a given number ‘key’. 
// The ceiling of the ‘key’ will be the smallest element 
// in the given array greater than or equal to the ‘key’.

public final class CeilingBinarySearchIterative {

  public static int ceil(int[] a, int x) {
    // Time Complexity: O(log n)

    int n = a.length;
    return ceil(a, 0, n - 1, x);
  }

  public static int ceil(int[] a, int start, int end, int x) {

    if (x <= a[start]) {
      return start;
    }

    if (x > a[end]) {
      return -1;
    }

    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (x < a[mid]) {
        end = mid - 1;
      } else if (x > a[mid]) {
        start = mid + 1;
      } else {
        return mid;
      }
    }
    return start;
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 8, 10, 10, 12, 19 };
    System.out.println(ceil(a, 3));
  }
}