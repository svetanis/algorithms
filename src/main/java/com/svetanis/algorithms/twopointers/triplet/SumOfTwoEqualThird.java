package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;
import java.util.Optional;

// Two numbers that add up to a third
//
// Input: an array of integers. The search below assumes none is negative.
// Return: three numbers at three different positions where two add up to the third, as
// {third, first, second}, or empty when there are none.
//
// The one idea: sort, then FIX the candidate sum a[i], from the largest down, and look for
// two numbers adding up to it among the numbers BEFORE it, with converging pointers -- Two
// Sum on a sorted array (LC 167).
// Looking only to the left of a[i] is right when no number is negative: the sum of two is
// then at least each of them, so it sits to their right in sorted order. With negatives it
// is not: in -1 + -2 == -3 the sum is the SMALLEST of the three, and this misses it.
//
// Sibling: twopointers.triplet.TripletGivenSumSorted -- the same converging loop with the
//   target given from outside instead of taken from the array.
//
// Time: O(n^2) -- n candidate sums, an O(n) converging pass for each.
// Space: O(1) besides the sort.

public final class SumOfTwoEqualThird {

  public static Optional<int[]> triplet(int[] a) {
    int n = a.length;
    Arrays.sort(a);                               // SORT: the converging pass needs it

    for (int i = n - 1; i >= 0; i--) {            // FIX the candidate sum, largest first
      int start = 0;                              // START: the two ENDS of a[0..i-1]
      int end = i - 1;
      while (start < end) {                       // STOP: a pair needs two positions
        int sum = a[start] + a[end];              // COMPARE with the candidate
        if (a[i] == sum) {
          return Optional.of(new int[] { a[i], a[start], a[end] }); // FOUND
        } else if (a[i] > sum) {
          start++;                                // DROP LEFT: too small even with the largest
        } else {
          end--;                                  // DROP RIGHT: too big even with the smallest
        }
      }
    }
    return Optional.empty();                      // RETURN empty: no such three
  }

  public static void main(String[] args) {
    int[] a1 = { 5, 32, 1, 7, 10, 50, 19, 21, 2 };
    System.out.println(triplet(a1).map(Arrays::toString).orElse("none")); // [21, 2, 19]

    int[] a2 = { 5, 32, 1, 7, 10, 50, 19, 21, 0 };
    System.out.println(triplet(a2).map(Arrays::toString).orElse("none")); // none

    int[] a3 = { -1, -2, -3 };
    System.out.println(triplet(a3).map(Arrays::toString).orElse("none")); // none -- negatives: -1 + -2 == -3 is missed
  }
}
