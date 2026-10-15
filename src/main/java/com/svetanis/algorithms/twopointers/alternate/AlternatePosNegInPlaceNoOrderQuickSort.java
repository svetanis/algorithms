package com.svetanis.algorithms.twopointers.alternate;

import java.util.Arrays;

// Alternate positive and negative numbers, in place, order not kept
//
// Input: an array of positive and negative numbers, in any mix -- one sign only is allowed.
// Return: nothing -- the array is rearranged in place so the signs alternate, starting
// with a positive: +, -, +, -, ... Whichever sign has more numbers puts its extras at
// the end. The order within each sign does not matter.
//
// The one idea: QuickSort's partition step with 0 as the pivot moves every negative to
// the front, so the positives start at index pos. The even slots 0, 2, 4, ... are the
// ones that must hold a positive: swap each with the next positive from the back block.
// neg jumps two slots while pos moves one, so the odd slots between keep their
// negatives. The loop stops when the positives run out, or when neg reaches a slot
// that no longer holds a negative -- from there on, the extras are already in place.
//
// The statement does not say where 0 goes; this file counts it with the positives.
//
// Siblings:
//   AlternateNegPosInPlaceNoOrder         -- positives partitioned to the front; starts with a negative
//   AlternatePosNegExtraSpaceOrderMatters -- keeps each sign's order; O(n) extra space
//
// Time: O(n) -- one partition pass, one swap pass.
// Space: O(1).

public final class AlternatePosNegInPlaceNoOrderQuickSort {

  public static void alternate(int[] a) {
    int n = a.length;
    int i = -1;                          // START: the last slot of the negative block
    for (int j = 0; j < n; ++j) {        // READ every number once
      if (a[j] < 0) {                    // KEEP a negative in the front block; 0 stays behind
        i++;
        swap(a, i, j);
      }
    }

    int neg = 0;                         // START: the first even slot, which must take a positive
    int pos = i + 1;                     // START: the first positive
    while (pos < n && neg < pos && a[neg] < 0) { // STOP: positives used up, or no negative left to trade
      swap(a, neg, pos);                 // SWAP the next positive into an even slot
      pos++;                             // MOVE to the next positive
      neg += 2;                          // MOVE two: the odd slot between keeps its negative
    }
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    int[] a = { -1, 2, -3, 4, 5, 6, -7, 8, 9 };
    alternate(a);
    System.out.println(Arrays.toString(a)); // [4, -3, 5, -1, 6, -7, 2, 8, 9]

    int[] a1 = { -2, 3, 4, -1 };
    alternate(a1);
    System.out.println(Arrays.toString(a1)); // [4, -1, 3, -2]

    int[] a2 = { -5, -2, -1, 3 };
    alternate(a2);
    System.out.println(Arrays.toString(a2)); // [3, -2, -1, -5]
  }
}
