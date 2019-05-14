package com.svetanis.algorithms.search.binary.frequency;

import static java.util.Arrays.sort;

// check if array[mid] is the first occurrence of x
// array[mid] is the first occurrence 
// if x is one of the following is true:
// 1. mid == 0 and array[mid] == k
// 2. array[mid - 1] < k and array[mid] == k

public final class FirstOccurrenceBinaryRecursive {

  public static int firstOccurrence(int[] a, int k) {
    int n = a.length;
    sort(a);
    return firstOccurrence(a, 0, n - 1, k);
  }

  public static int firstOccurrence(int[] a, int left, int right, int k) {
    // O(log n)

    if (right < left) {
      return -1;
    }
    int mid = left + (right - left) / 2;
    if ((mid == 0 || k > a[mid - 1]) && a[mid] == k) {
      return mid;
    } else if (k > a[mid]) {
      return firstOccurrence(a, mid + 1, right, k);
    } else {
      return firstOccurrence(a, left, mid - 1, k);
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 30, 40, 50, 60, 70, 23, 20 };
    System.out.println(firstOccurrence(a, 60));
  }
}