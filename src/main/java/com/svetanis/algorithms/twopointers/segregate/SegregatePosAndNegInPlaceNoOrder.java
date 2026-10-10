package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// Segregate positive and negative numbers, in place, order not kept
//
// Input: an array of positive and negative integers, no zeros. A 0 is neither: both
// cursors stop on it and the loop never ends.
// Return: the number of positive numbers, which is also the index of the first negative
// after the call. The array is rearranged in place so that every positive number comes
// before every negative one; the order inside each group is not kept. All positive
// returns n, all negative returns 0.
//
// The one idea: two cursors converging from the ends. [0, left) holds positives and
// (right, n-1] holds negatives. left skips the positives already in place, right skips
// the negatives already in place; when both stop with left < right, a[left] is negative
// and a[right] is positive, and one swap puts both where they belong. The loop ends
// when left has passed right, so left counts the positives.
//
// Siblings -- the same grouping:
//   twopointers.segregate.SegregatePosAndNegExtraSpaceOrderMatters -- positives first,
//     order kept, O(n) space
//   twopointers.segregate.SegregatePosAndNegInPlaceOrderMatters -- NEGATIVES first, order
//     kept, O(1) space, O(n^2) in the worst case
//   twopointers.alternate.AlternateNegPosInPlaceNoOrder -- carries its own copy of this
//     partition, bounded so that a 0 cannot stall it, and returns the first negative's index
//
// Time: O(n) -- every step moves left or right inward, so at most n steps.
// Space: O(1) -- swaps in place.

public final class SegregatePosAndNegInPlaceNoOrder {

  public static int segregate(int[] a) {
    int left = 0;                                // START: [0, left) holds positives
    int right = a.length - 1;                    // START: (right, n-1] holds negatives

    while (left <= right) {                      // STOP: every number is in one of the two regions
      while (left <= right && a[left] > 0) {
        left++;                                  // SKIP positives already in place
      }

      while (left <= right && a[right] < 0) {
        right--;                                 // SKIP negatives already in place
      }

      if (left < right) {
        swap(a, left, right);                    // SWAP a negative on the left with a positive on the right
      }
    }
    return left;                                 // RETURN the number of positives
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    int[] a1 = { -2, 3, 4, -1 };
    System.out.println(segregate(a1)); // 2
    System.out.println(Arrays.toString(a1)); // [4, 3, -2, -1]

    int[] a2 = { -2, 3, 1 };
    System.out.println(segregate(a2)); // 2
    System.out.println(Arrays.toString(a2)); // [1, 3, -2]

    int[] a3 = { -5, 3, 4, 5, -6, -2, 8, 9, -1, -4 };
    System.out.println(segregate(a3)); // 5
    System.out.println(Arrays.toString(a3)); // [9, 3, 4, 5, 8, -2, -6, -5, -1, -4]

    int[] a4 = { 1, 2 };
    System.out.println(segregate(a4)); // 2

    int[] a5 = { -1, -2 };
    System.out.println(segregate(a5)); // 0
  }

}
