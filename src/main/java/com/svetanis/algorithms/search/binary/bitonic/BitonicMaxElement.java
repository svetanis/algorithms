package com.svetanis.algorithms.search.binary.bitonic;

// Find the maximum value in a given Bitonic array. 
// An array is considered bitonic if it is monotonically 
// increasing and then monotonically decreasing. 
// Monotonically increasing or decreasing means that 
// for any index i in the array arr[i] != arr[i+1].

public final class BitonicMaxElement {

  public static int max(int[] a) {
	// Time Complexity: O(log n)
	  
    int start = 0;
    int end = a.length - 1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (a[mid] > a[mid + 1]) {
        end = mid;
      } else {
        start = mid + 1;
      }
    }
    return start;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 8, 12, 4, 2 };
    System.out.println(max(a1));

    int[] a2 = { 3, 8, 3, 1 };
    System.out.println(max(a2));

    int[] a3 = { 1, 3, 8, 12 };
    System.out.println(max(a3));

    int[] a4 = { 10, 9, 8 };
    System.out.println(max(a4));

  }
}