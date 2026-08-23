package com.svetanis.algorithms.search.binary.bitonic;

// Find the minimum value in a given Bitonic array. 
// An array is considered bitonic if it is monotonically 
// increasing and then monotonically decreasing. 
// Monotonically increasing or decreasing means that 
// for any index i in the array arr[i] != arr[i+1].

public final class BitonicMinElement {

  // no binary search exists here, and that is the point:
  // the array rises then falls, so the smallest value is at
  // one of the two ENDS. there is no interior valley to find.

  // the loop that would find one -- BitonicMaxElement's, with its
  // branches swapped -- converges on whichever end its early steps
  // steered it to, which is the right one only about 4 times in 5.

  public static int min(int[] a) {
    // Time Complexity: O(1)

    int end = a.length - 1;
    return a[0] <= a[end] ? 0 : end;
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