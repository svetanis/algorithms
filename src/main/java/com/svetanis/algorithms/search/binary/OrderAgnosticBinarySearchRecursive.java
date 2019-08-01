package com.svetanis.algorithms.search.binary;

import static com.google.common.collect.Iterables.size;
import static com.google.common.primitives.Ints.toArray;

import java.util.List;

// Given a sorted array of numbers, 
// find if a given number ‘key’ is present in the array. 
// Though we know that the array is sorted, 
// we don’t know if it’s sorted in ascending 
// or descending order.

public final class OrderAgnosticBinarySearchRecursive {

  public static int binarySearch(List<Integer> list, int x) {
    int n = size(list);
    return binarySearch(list, 0, n - 1, x);
  }

  public static int binarySearch(List<Integer> list, int start, int end, int x) {
    return binarySearch(toArray(list), start, end, x);
  }

  public static int binarySearch(int[] a, int x) {
    int n = a.length;
    return binarySearch(a, 0, n - 1, x);
  }

  public static int binarySearch(int[] a, int start, int end, int x) {
    // O(log n)

    if (end < start) {
      return -1;
    }

    boolean isAscending = a[start] < a[end];
    int mid = start + (end - start) / 2;

    if (a[mid] == x) {
      return mid;
    }

    if (isAscending) {
      if (x < a[mid]) {
        return binarySearch(a, start, mid - 1, x);
      } else {
        return binarySearch(a, mid + 1, end, x);
      }
    } else { // descending order
      if (x > a[mid]) {
        return binarySearch(a, start, mid - 1, x);
      } else {
        return binarySearch(a, mid + 1, end, x);
      }
    }
  }

  public static void main(String[] args) {
    int[] a1 = { 4, 6, 10 };
    System.out.println(binarySearch(a1, 10));

    int[] a2 = { 1, 2, 3, 4, 5, 6, 7 };
    System.out.println(binarySearch(a2, 5));

    int[] a3 = { 10, 6, 4 };
    System.out.println(binarySearch(a3, 10));

    int[] a4 = { 10, 6, 4 };
    System.out.println(binarySearch(a4, 4));
  }
}
