package com.svetanis.algorithms.search.binary;

import static com.google.common.collect.Iterables.size;
import static com.google.common.primitives.Ints.toArray;
import static java.util.Arrays.sort;

import java.util.List;

public final class BinarySearchRecursive {

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

    int mid = start + (end - start) / 2;

    if (a[mid] == x) {
      return mid;
    } else if (x > a[mid]) {
      return binarySearch(a, mid + 1, end, x);
    } else {
      return binarySearch(a, start, mid - 1, x);
    }
  }

  public static boolean isBinary(int[] a, int start, int end, int x) {
    // O(log n)

    if (end < start) {
      return false;
    }

    int mid = start + (end - start) / 2;
    if (a[mid] == x) {
      return true;
    } else if (x > a[mid]) {
      return isBinary(a, mid + 1, end, x);
    } else {
      return isBinary(a, start, mid - 1, x);
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 30, 40, 50, 60, 70, 23, 20 };
    sort(a);
    System.out.println(isBinary(a, 0, a.length - 1, 50));
    System.out.println(binarySearch(a, 0, a.length - 1, 50));
  }
}