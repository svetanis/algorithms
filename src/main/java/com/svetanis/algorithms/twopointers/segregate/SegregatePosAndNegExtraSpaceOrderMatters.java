package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// Segregate positive and negative numbers, keeping order, with extra space
//
// Input: an array of integers. A 0 is grouped with the positives (the test is >= 0).
// Return: nothing -- the array is rearranged in place so that every non-negative number
// comes before every negative one, and inside each group the numbers keep their
// original order.
//
// The one idea: kept order is easy with a second array. Copy the non-negatives into it
// in the order they are met, then the negatives, then copy it back. When one group is
// empty nothing would move, so the method returns after the first pass.
//
// Siblings -- the same grouping:
//   twopointers.segregate.SegregatePosAndNegInPlaceOrderMatters -- order kept, O(1)
//     space, but NEGATIVES first, no zeros, and O(n^2) in the worst case
//   twopointers.segregate.SegregatePosAndNegInPlaceNoOrder -- positives first, in place,
//     one pass, order not kept
//
// Time: O(n) -- three passes over the array.
// Space: O(n) -- the second array.

public final class SegregatePosAndNegExtraSpaceOrderMatters {

  public static void segregate(int[] a) {
    int n = a.length;
    int[] temp = new int[n];
    int j = 0;                       // START: the next free slot in temp
    for (int i = 0; i < n; i++) {
      if (a[i] >= 0) {
        temp[j++] = a[i];            // WRITE the non-negatives first, in input order
      }
    }
    if (j == 0 || j == n) {
      return;                        // STOP: one group is empty, nothing moves
    }
    for (int i = 0; i < n; i++) {
      if (a[i] < 0) {
        temp[j++] = a[i];            // WRITE the negatives after them, in input order
      }
    }
    for (int i = 0; i < n; i++) {
      a[i] = temp[i];                // COPY back into the input
    }
  }

  public static void main(String[] args) {
    int[] a1 = { -2, 3, 4, -1 };
    segregate(a1);
    System.out.println(Arrays.toString(a1)); // [3, 4, -2, -1]

    int[] a2 = { -2, 3, 1 };
    segregate(a2);
    System.out.println(Arrays.toString(a2)); // [3, 1, -2]

    int[] a3 = { -5, 3, 4, 5, -6, -2, 8, 9, -1, -4 };
    segregate(a3);
    System.out.println(Arrays.toString(a3)); // [3, 4, 5, 8, 9, -5, -6, -2, -1, -4]

    int[] a4 = { 1, -1, -3, -2, 7, 5, 11, 6 };
    segregate(a4);
    System.out.println(Arrays.toString(a4)); // [1, 7, 5, 11, 6, -1, -3, -2]
  }
}
