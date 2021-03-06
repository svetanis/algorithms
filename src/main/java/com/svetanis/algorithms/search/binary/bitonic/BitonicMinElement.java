package com.svetanis.algorithms.search.binary.bitonic;

// Find the minimum value in a given Bitonic array. 
// An array is considered bitonic if it is monotonically 
// increasing and then monotonically decreasing. 
// Monotonically increasing or decreasing means that 
// for any index i in the array arr[i] != arr[i+1].

public final class BitonicMinElement {

  public static int min(int[] a) {
    int n = a.length;
    int start = 0;
    int end = n - 1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (a[mid] > a[mid + 1]) {
        start = mid + 1;
      } else {
        end = mid;
      }
    }
    return start;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 8, 12, 4, 2 };
    System.out.println(min(a1));

    int[] a2 = { 3, 8, 3, 1 };
    System.out.println(min(a2));

    int[] a3 = { 1, 3, 8, 12 };
    System.out.println(min(a3));

    int[] a4 = { 10, 9, 8 };
    System.out.println(min(a4));

  }
}