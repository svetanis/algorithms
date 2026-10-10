package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// Segregate 0s and 1s, in one traversal
//
// Input: an array that holds only the values 0 and 1, in random order.
// Return: nothing -- the array is rearranged in place with every 0 before every 1,
// reading the array only once.
//
// The one idea: two cursors converging from the ends. [0, left) holds 0s and
// (right, n-1] holds 1s. left skips the 0s already in place, right skips the 1s already
// in place; when both stop, a[left] is 1 and a[right] is 0, and one swap puts both
// where they belong.
//
// Siblings:
//   twopointers.segregate.SegregateZerosAndOnesCount -- counts the 0s and rewrites the
//     array instead, two passes
//   twopointers.segregate.SegregateOddAndEvenInPlace -- this loop with "is even" in place
//     of "is 0"
//   twopointers.segregate.DutchNationalFlag -- three values 0, 1, 2 need a third pointer
//
// Time: O(n) -- each step moves left or right inward, so at most n steps.
// Space: O(1) -- swaps in place.

public final class SegregateZerosAndOnesSingleTraversal {

  public static void segregate(int[] a) {
    int left = 0;                          // START: [0, left) holds 0s
    int right = a.length - 1;              // START: (right, n-1] holds 1s

    while (left < right) {                 // STOP: the cursors meet
      while (a[left] == 0 && left < right) {
        left++;                            // SKIP 0s already in place
      }
      while (a[right] == 1 && left < right) {
        right--;                           // SKIP 1s already in place
      }
      if (left < right) {
        swap(a, left, right);              // SWAP a 1 on the left with a 0 on the right
        left++;
        right--;
      }
    }
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    int[] a = { 0, 1, 0, 1, 1, 1 };
    segregate(a);
    System.out.println(Arrays.toString(a)); // [0, 0, 1, 1, 1, 1]

    int[] a1 = { 1, 0, 1, 0, 0 };
    segregate(a1);
    System.out.println(Arrays.toString(a1)); // [0, 0, 0, 1, 1]
  }
}