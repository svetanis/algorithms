package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// Segregate even and odd numbers, into a new array
//
// Input: an array of integers; negatives are allowed, since a % 2 == 0 tests evenness
// for both signs.
// Return: a new array holding every even number before every odd number. The order
// inside each group is not kept.
//
// The one idea: fill the output from both ends. An even number is written at the front
// cursor, an odd number at the back cursor; the two cursors meet exactly when every
// number has been placed. The input is read from both ends at once, two numbers per
// step, and with an odd length the middle number is read once, alone.
//
// Siblings -- the same question:
//   twopointers.segregate.SegregateOddAndEvenInPlace -- in place, O(1) space: swaps an
//     odd on the left with an even on the right
//   twopointers.SortByParity (LC 905) -- in place on non-negative numbers; its
//     read/write version keeps the evens' order
//
// Time: O(n) -- every number is read once.
// Space: O(n) -- the output array.

public final class SegregateOddAndEvenExtraSpace {

  public static int[] segregate(int[] a) {
    int n = a.length;
    int left = 0;                                  // START: the next slot for an even
    int right = n - 1;                             // START: the next slot for an odd
    int[] seg = new int[n];

    for (int i = 0, j = n - 1; i <= j; i++, j--) { // READ from both ends; i == j is the middle
      if (a[i] % 2 == 0) {
        seg[left] = a[i];                          // WRITE an even at the front
        left++;
      } else {
        seg[right] = a[i];                         // WRITE an odd at the back
        right--;
      }

      if (i < j) {                                 // SKIP a[j] when it is the middle, already placed
        if (a[j] % 2 == 0) {
          seg[left] = a[j];                        // WRITE an even at the front
          left++;
        } else {
          seg[right] = a[j];                       // WRITE an odd at the back
          right--;
        }
      }
    }
    return seg;
  }

  public static void main(String[] args) {
    int[] a = { 12, 34, 45, 9, 8, 90, 3 };
    System.out.println(Arrays.toString(segregate(a))); // [12, 34, 90, 8, 9, 45, 3]

    int[] a1 = { 4, 9, 5, 2, 9, 5, 7, 10 };
    System.out.println(Arrays.toString(segregate(a1))); // [4, 10, 2, 9, 5, 5, 7, 9]
  }
}
