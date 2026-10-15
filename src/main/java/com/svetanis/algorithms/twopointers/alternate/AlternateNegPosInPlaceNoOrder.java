package com.svetanis.algorithms.twopointers.alternate;

import java.util.Arrays;

// Alternate negative and positive numbers, in place, order not kept
//
// Input: an array of positive and negative numbers, in any mix -- one sign only is allowed.
// Return: nothing -- the array is rearranged in place so the signs alternate, starting
// with a negative: -, +, -, +, ... Whichever sign has more numbers puts its extras at
// the end. The order within each sign does not matter.
//
// The one idea: partition first, then swap across. Move every positive to the front, so
// the negatives start at index neg. The even slots 0, 2, 4, ... are the ones that must
// hold a negative: swap each with the next negative from the back block. pos jumps two
// slots while neg moves one, so the odd slots between keep their positives.
//
// The statement does not say where 0 goes; this file counts it with the positives, as
// its two siblings do.
//
// Siblings:
//   AlternatePosNegInPlaceNoOrderQuickSort -- negatives partitioned to the front; starts with a positive
//   AlternatePosNegExtraSpaceOrderMatters  -- keeps each sign's order and starts with a positive; O(n) extra space
//   datastructures.todo.AlternatePosNegInPlaceOrderMatters -- keeps each sign's order in place, by rotating
//
// Time: O(n) -- one partition pass, one swap pass.
// Space: O(1).

public final class AlternateNegPosInPlaceNoOrder {

  public static void alternate(int[] a) {
    int n = a.length;
    int neg = partition(a);    // START: positives in [0, neg), negatives in [neg, n)
    if (neg == 0 || neg == n) {
      return;                  // STOP: one sign only, nothing to alternate
    }
    int pos = 0;               // START: the first slot that must take a negative
    while (pos < n && neg < n) {
      swap(a, pos, neg);       // SWAP the next negative into an even slot
      neg++;                   // MOVE to the next negative
      pos += 2;                // MOVE two: the odd slot between keeps its positive
    }
  }

  // moves every number >= 0 to the front; returns the index of the first negative
  private static int partition(int[] a) {
    int neg = 0;                          // START: the slot after the positives so far
    for (int i = 0; i < a.length; i++) {  // READ every number once
      if (a[i] >= 0) {                    // KEEP a positive (or 0) in the front block
        swap(a, neg, i);
        neg++;
      }
    }
    return neg;
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    int[] a1 = { -2, 3, 4, -1 };
    alternate(a1);
    System.out.println(Arrays.toString(a1)); // [-2, 4, -1, 3]

    int[] a2 = { -2, 3, 1 };
    alternate(a2);
    System.out.println(Arrays.toString(a2)); // [-2, 1, 3]

    int[] a3 = { -5, 3, 4, 5, -6, -2, 8, 9, -1, -4 };
    alternate(a3);
    System.out.println(Arrays.toString(a3)); // [-2, 4, -5, 8, -6, 3, -1, 9, -4, 5]

    int[] a4 = { 1, 5 };
    alternate(a4);
    System.out.println(Arrays.toString(a4)); // [1, 5]
  }
}
