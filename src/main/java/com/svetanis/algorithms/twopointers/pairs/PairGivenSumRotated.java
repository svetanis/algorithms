package com.svetanis.algorithms.twopointers.pairs;

import java.util.Arrays;

// Find a Pair With a Given Sum in a Sorted and Rotated Array
//
// Input: an array of n >= 1 distinct integers that was sorted and then rotated, such as
// {11, 15, 6, 8, 9, 10}, and a target k.
// Return: a pair {x, y} with x + y == k, smaller first; {-1, -1} if there is none.
//
// The one idea: LC 167's converging loop on a circle. The largest number sits just
// before the drop (the pivot) and the smallest just after it, so the loop starts
// there. Stepping up from the smallest and down from the largest is index + 1 and
// index - 1 taken modulo n, which wraps around the ends of the array. The rest is the
// sorted loop: a sum too small drops the smaller end, a sum too big drops the larger.
//
// Siblings:
//   PairsGivenSumRotated -- every pair, not the first; uses pivot() from here
//   PairGivenSumSorted -- the same loop on an array that is not rotated
//
// Time: O(n) -- one pass for the pivot, then at most n - 1 steps.
// Space: O(1).

public final class PairGivenSumRotated {

  public static int[] pair(int[] a, int k) {
    int n = a.length;
    int left = 0;                               // START: smallest and largest, when not rotated
    int right = n - 1;
    int p = pivot(a);
    if (p != -1) {
      left = (p + 1) % n;                       // START: the smallest number, just after the drop
      right = p;                                // START: the largest number, just before it
    }
    while (left != right) {                     // STOP: the two ends have met
      int sum = a[left] + a[right];             // COMPARE
      if (sum == k) {
        return new int[] { a[left], a[right] }; // FOUND
      }
      if (sum < k) {
        left = (left + 1) % n;                  // DROP LEFT: step up, wrapping past the end
      } else {
        right = (n - 1 + right) % n;            // DROP RIGHT: step down, wrapping past the start
      }
    }
    return new int[] { -1, -1 };                // RETURN: no pair
  }

  // the position of the largest number, just before the drop; -1 if the array is not rotated
  public static int pivot(int[] a) {
    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
      if (a[i] > a[i + 1]) {
        return i; // FOUND the drop
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] a1 = { 11, 15, 6, 8, 9, 10 };
    System.out.println(Arrays.toString(pair(a1, 16))); // [6, 10]

    int[] a2 = { 11, 15, 26, 38, 9, 10 };
    System.out.println(Arrays.toString(pair(a2, 35))); // [9, 26]

    int[] a3 = { 11, 15, 26, 38, 9, 10 };
    System.out.println(Arrays.toString(pair(a3, 45))); // [-1, -1]

    int[] a4 = { 11, 15, 6, 7, 8, 9, 10 };
    System.out.println(Arrays.toString(pair(a4, 16))); // [6, 10]
  }
}
