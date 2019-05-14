package com.svetanis.algorithms.search.binary.frequency;

import static java.util.Arrays.sort;

// check if array[mid] is the first occurrence of x
// array[mid] is the first occurrence 
// if x is one of the following is true:
// 1. mid == 0 and array[mid] == k
// 2. array[mid - 1] < k and array[mid] == k

public final class LastOccurrenceBinaryRecursive {

  public static int lastOccurrence(int[] a, int k) {
    int n = a.length;
    sort(a);
    return lastOccurrence(a, 0, n - 1, k);
  }

  private static int lastOccurrence(int[] a, int left, int right, int k) {
    // O(log n)

    if (right < left) {
      return -1;
    }

    int mid = left + (right - left) / 2;

    if ((mid == right || k < a[mid + 1]) && a[mid] == k) {
      return mid;
    } else if (k < a[mid]) {
      return lastOccurrence(a, left, mid - 1, k);
    } else {
      return lastOccurrence(a, mid + 1, right, k);
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 30, 40, 50, 60, 60, 70, 23, 20 };
    System.out.println(lastOccurrence(a, 60));

    int[] a1 = { 2, 2, 3, 5, 6 };
    System.out.println(lastOccurrence(a1, 2));
  }
}