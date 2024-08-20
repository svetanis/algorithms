package com.svetanis.algorithms.search.binary.diff;

// given an array of integers
// find a peak element in it

// an array element is peak if 
// it is NOT smaller than its neighbors

// if mid element is not peak and 
// its left neighbor is greater than it
// then left half must have a peak element

// if mid element is not peak and 
// its right neighbor is greater than it
// then right half must have a peak element

// corner cases:
// if array is sorted in strictly increasing order,
// the last element is always a peak element

// if array is sorted in strictly decreasing order,
// the first element is always a peak element

// if all elements of array are same,
// every element is a peak element

public final class PeakElement {

  public static int peak(int[] a) {
    int n = a.length;
    return peak(a, 0, n - 1);
  }

  private static int peak(int[] a, int left, int right) {
    // Time Complexity: O(log n)

    if (right < left) {
      return -1;
    }

    int n = a.length;
    int mid = left + (right - left) / 2;
    boolean one = mid == 0 || a[mid - 1] <= a[mid];
    boolean two = mid == n - 1 || a[mid + 1] <= a[mid];

    if (one && two) {
      return mid;
    } else if (mid > 0 && a[mid - 1] > a[mid]) {
      return peak(a, left, mid - 1);
    } else {
      return peak(a, mid + 1, right);
    }
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 20, 4, 1, 0 };
    System.out.println(peak(a1)); // 2

    int[] a2 = { 10, 20, 15, 2, 23, 90, 67 };
    System.out.println(peak(a2)); // 1

    int[] a3 = { 10, 20, 30, 40, 50 };
    System.out.println(peak(a3)); // 4

    int[] a4 = { 100, 80, 60, 50, 20 };
    System.out.println(peak(a4)); // 0

    int[] a5 = { 20, 20, 20, 20, 20 };
    System.out.println(peak(a5)); // 2
  }
}