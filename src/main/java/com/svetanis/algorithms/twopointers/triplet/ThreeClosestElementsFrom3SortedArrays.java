package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;

// Three closest elements from three sorted arrays
//
// Input: three non-empty arrays a, b, c, each sorted in non-decreasing order.
// Return: {a[i], b[j], c[k]} that makes max(|a[i] - b[j]|, |b[j] - c[k]|, |c[k] - a[i]|)
// as small as possible -- the first such triple met. That maximum is simply the largest of
// the three minus the smallest, their SPREAD.
//
// The one idea: one pointer per array, all starting at the smallest numbers. Only stepping
// past the MINIMUM of the three can help: every triple still ahead that keeps the minimum
// has a maximum at least the current one, so none beats the spread just computed, and the
// minimum can be dropped for good. Stop when any array runs out, or the spread hits 0.
//
// Sibling: twopointers.triplet.Closest3Elements3Arrays -- the same loop, sorting the three
//   arrays first.
//
// Time: O(n + m + l) -- every step moves one pointer forward.
// Space: O(1).

public final class ThreeClosestElementsFrom3SortedArrays {

  public static int[] triplet(int[] a, int[] b, int[] c) {
    int diff = Integer.MAX_VALUE;                 // the smallest spread so far

    int n = a.length;
    int m = b.length;
    int l = c.length;

    int first = 0;                                // the positions that gave it
    int second = 0;
    int third = 0;

    int i = 0;                                    // START: the smallest of each array
    int j = 0;
    int k = 0;

    while (i < n && j < m && k < l) {             // STOP: one array ran out
      int min = Math.min(a[i], Math.min(b[j], c[k]));
      int max = Math.max(a[i], Math.max(b[j], c[k]));

      if (max - min < diff) {                     // COMPARE the spread of these three
        diff = max - min;                         // RECORD a smaller spread
        first = i;
        second = j;
        third = k;
      }

      if (diff == 0) {
        break;                                    // STOP: a spread of 0 cannot be beaten
      }

      if (a[i] == min) {
        i++;                                      // DROP the minimum: it has met its best partners
      } else if (b[j] == min) {
        j++;
      } else {
        k++;
      }
    }
    return new int[] { a[first], b[second], c[third] };
  }

  public static void main(String[] args) {
    int[] a = { 1, 4, 10 };
    int[] b = { 2, 15, 20 };
    int[] c = { 10, 12 };
    System.out.println(Arrays.toString(triplet(a, b, c))); // [10, 15, 10]

    int[] a1 = { 20, 24, 100 };
    int[] b1 = { 2, 19, 22, 79, 800 };
    int[] c1 = { 10, 12, 23, 24, 119 };
    System.out.println(Arrays.toString(triplet(a1, b1, c1))); // [24, 22, 23]
  }
}
