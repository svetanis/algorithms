package com.svetanis.algorithms.twopointers.alternate;

import java.util.ArrayList;
import java.util.List;

// Alternate positive and negative numbers, each sign in its original order
//
// Input: an array of positive and negative numbers, in any mix.
// Return: a new list in which the signs alternate, starting with a positive: +, -, +, -,
// ... The positives keep their original order, and so do the negatives. Whichever sign
// has more numbers puts its extras at the end.
//
// The one idea: two readers, one per sign. pos stands on the next positive not yet
// used, neg on the next negative. Output slot i takes from pos when i is even and from
// neg when i is odd -- unless that sign is used up, and then the other sign fills every
// remaining slot. Each reader only moves forward, so each sign comes out in order.
//
// The statement does not say where 0 goes; this file counts it with the positives.
//
// Siblings:
//   AlternatePosNegInPlaceNoOrderQuickSort -- in place, O(1) space, order within each sign lost
//   AlternateNegPosInPlaceNoOrder          -- the same, starting with a negative
//   datastructures.todo.AlternatePosNegInPlaceOrderMatters -- keeps the order in place, by rotating
//
// Time: O(n) -- each reader moves forward at most n times in all, and there are n slots.
// Space: O(n) for the output list.

public final class AlternatePosNegExtraSpaceOrderMatters {

  public static List<Integer> alternate(int[] a) {
    int n = a.length;
    int pos = 0;                         // START: the reader for the positives
    int neg = 0;                         // START: the reader for the negatives
    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < n; i++) {        // WRITE output slot i
      while (neg < n && a[neg] >= 0) {
        neg++;                           // SKIP to the next negative
      }
      while (pos < n && a[pos] < 0) {
        pos++;                           // SKIP to the next positive
      }

      if (pos == n) {
        list.add(a[neg++]);              // RECORD a negative: the positives are used up
      } else if (neg == n) {
        list.add(a[pos++]);              // RECORD a positive: the negatives are used up
      } else if (i % 2 == 0) {
        list.add(a[pos++]);              // RECORD a positive in an even slot
      } else {
        list.add(a[neg++]);              // RECORD a negative in an odd slot
      }
    }
    return list;
  }

  public static void main(String[] args) {
    int[] a1 = { -2, 3, 4, -1 };
    System.out.println(alternate(a1)); // [3, -2, 4, -1]

    int[] a2 = { -2, 3, 1 };
    System.out.println(alternate(a2)); // [3, -2, 1]

    int[] a3 = { -5, 3, 4, 5, -6, -2, 8, 9, -1, -4 };
    System.out.println(alternate(a3)); // [3, -5, 4, -6, 5, -2, 8, -1, 9, -4]
  }
}
