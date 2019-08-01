package com.svetanis.algorithms.search.binary.math;

// Given an array of numbers sorted in ascending order, 
// find the floor of a given number ‘key’. 
// The floor of the ‘key’ will be the biggest element 
// in the given array smaller than or equal to the ‘key’

public final class FloorBinarySearchIterative {

  public static int floor(int[] a, int x) {
    // Time Complexity: O(log n)

    int n = a.length;
    return floor(a, 0, n - 1, x);
  }

  public static int floor(int[] a, int start, int end, int x) {

    if (start > end) {
      return -1;
    }

    if (x >= a[end]) {
      return end;
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
    return end;
  }

  public static void main(String[] args) {
    int[] a = { 1, 3, 8, 10, 10, 12, 19 };
    System.out.println(floor(a, 12));
  }
}