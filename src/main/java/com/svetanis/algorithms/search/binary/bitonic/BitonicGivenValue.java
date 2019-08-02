package com.svetanis.algorithms.search.binary.bitonic;

import static com.svetanis.algorithms.search.binary.OrderAgnosticBinarySearchIterative.binarySearch;
import static com.svetanis.algorithms.search.binary.bitonic.BitonicMaxElement.max;

// Given a Bitonic array, find if a given ‘key’ is present in it. 
// An array is considered bitonic if it is monotonically increasing 
// and then monotonically decreasing. 
// Monotonically increasing or decreasing means that 
// for any index i in the array arr[i] != arr[i+1].

// Write a function to return the index of the ‘key’. 
// If the ‘key’ is not present, return -1.

public final class BitonicGivenValue {

  public static int search(int[] a, int k) {
    int max = max(a);
    int index = binarySearch(a, k, 0, max);
    if (index != -1) {
      return index;
    }
    return binarySearch(a, k, max, a.length - 1);
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 8, 4, 3 };
    System.out.println(search(a1, 4));

    int[] a2 = { 3, 8, 3, 1 };
    System.out.println(search(a2, 8));

    int[] a3 = { 1, 3, 8, 12 };
    System.out.println(search(a3, 12));

    int[] a4 = { 10, 9, 8 };
    System.out.println(search(a4, 10));

  }
}