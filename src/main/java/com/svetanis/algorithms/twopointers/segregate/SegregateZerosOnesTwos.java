package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// 75. Sort Colors
//
// Input: an array that holds only the values 0, 1 and 2.
// Return: nothing -- the array is sorted in place, in one pass, with O(1) extra space.
//
// The one idea: four regions behind three pointers. [0, low) holds 0s, [low, mid) holds
// 1s, [mid, high] has not been looked at, and (high, n-1] holds 2s. Each pass reads
// a[mid] and shrinks the unread region by one. A 2 is swapped to high and only high
// moves: what comes back from high has not been looked at yet.
//
// Siblings -- the same loop:
//   twopointers.segregate.DutchNationalFlag -- the identical method (dnf) a second time,
//     same pointer names; one solution filed twice
//   twopointers.segregate.RGBs -- the same loop on the chars 'R', 'G', 'B'
//
// Time: O(n) -- every pass shrinks [mid, high] by one, so at most n passes.
// Space: O(1) -- swaps in place.

public final class SegregateZerosOnesTwos {

  public static void segregate(int[] a) {
    int low = 0;                // START: [0, low) holds the 0s
    int mid = 0;                // START: the reader; [mid, high] is unread
    int high = a.length - 1;    // START: (high, n-1] holds the 2s
    while (mid <= high) {       // STOP: nothing left to read
      if (a[mid] == 0) {
        swap(a, low, mid);      // SWAP the 0 to the edge of the 0s
        low++;                  // MOVE both: what came back from low is a 1
        mid++;
      } else if (a[mid] == 1) {
        mid++;                  // MOVE mid: already in the 1s
      } else {
        swap(a, mid, high);     // SWAP the 2 to the edge of the 2s
        high--;                 // MOVE high only: mid reads the newcomer
      }
    }
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    int[] a = { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
    segregate(a);
    System.out.println(Arrays.toString(a)); // [0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2]

    int[] a1 = { 2, 0, 2, 1, 1, 0 };
    segregate(a1);
    System.out.println(Arrays.toString(a1)); // [0, 0, 1, 1, 2, 2]
  }
}