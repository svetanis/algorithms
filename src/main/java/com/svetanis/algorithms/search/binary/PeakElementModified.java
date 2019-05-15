package com.svetanis.algorithms.search.binary;

// Given an array of integers. Find a peak element in it. 
// An array element is peak if it is greater than its neighbors. 

// if mid element is not peak and 
// its left neighbor is greater than it
// then left half must have a peak element

// if mid element is not peak and 
// its right neighbor is greater than it
// then right half must have a peak element

public final class PeakElementModified {

  private static int peak(int[] a) {
    int n = a.length;
    return peak(a, 0, n - 1);
  }

  private static int peak(int[] a, int start, int end) {
    // Time Complexity: O(log n)

    if (end < start) {
      return -1;
    }

    int mid = start + (end - start) / 2;
    int n = a.length;

    boolean one = mid == 0 || a[mid - 1] < a[mid];
    boolean two = mid == n - 1 || a[mid + 1] < a[mid];
    if (one && two) {
      return mid;
    } else if (mid > 0 && a[mid - 1] > a[mid]) {
      return peak(a, start, mid - 1);
    } else {
      return peak(a, mid + 1, end);
    }
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 20, 4, 1, 0 };
    System.out.println(peak(a1));

    int[] a2 = { 10, 20, 15, 2, 23, 90, 67 };
    System.out.println(peak(a2));

    int[] a3 = { 10, 20, 30, 40, 50 };
    System.out.println(peak(a3));

    int[] a4 = { 100, 80, 60, 50, 20 };
    System.out.println(peak(a4));

    int[] a5 = { 20, 20, 20, 20, 20 };
    System.out.println(peak(a5));
  }
}