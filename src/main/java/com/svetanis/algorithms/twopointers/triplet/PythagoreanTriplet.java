package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;

// Pythagorean triplet in an array
//
// Input: an array of integers, any sign -- only their squares are used.
// Return: whether three numbers at three different positions satisfy x^2 + y^2 == z^2 in
// some order.
//
// The one idea: square everything and sort. Now it is 3Sum with the target inside the
// array: the largest of the three squares must be z^2, because the other two are added to
// make it and squares are never negative. So FIX the largest square and look for two
// squares to its LEFT that add up to it, with converging pointers on that prefix -- the loop
// of Valid Triangle Number (LC 611), testing == instead of >.
//
// Sibling: twopointers.ValidTriangleNumber611 -- the same "fix the largest, converge on the
//   prefix" loop, counting instead of finding.
//
// Time: O(n^2) -- the sort is O(n log n), then n fixed squares with an O(n) pass each.
// Space: O(n) for the array of squares.
// A square overflows int once a number is more than 46,340 in size.

public final class PythagoreanTriplet {

  public static boolean isPythagorean(int[] a) {
    int[] sqr = square(a);
    Arrays.sort(sqr);                             // SORT the squares: the largest is z^2
    return isPythagoreanUtil(sqr);
  }

  private static boolean isPythagoreanUtil(int[] a) {
    for (int i = a.length - 1; i >= 2; i--) {     // FIX the LARGEST square, walking down
      int left = 0;                               // START: the two ENDS of a[0..i-1]
      int right = i - 1;
      while (left < right) {                      // STOP: a pair needs two positions
        int sum = a[left] + a[right];             // COMPARE with the largest square
        if (sum == a[i]) {
          return true;                            // FOUND: x^2 + y^2 == z^2
        } else if (sum < a[i]) {
          left++;                                 // DROP LEFT: too small even with the largest
        } else {
          right--;                                // DROP RIGHT: too big even with the smallest
        }
      }
    }
    return false;
  }

  private static int[] square(int[] a) {
    int n = a.length;
    int[] square = new int[n];
    for (int i = 0; i < a.length; i++) {
      square[i] = a[i] * a[i];
    }
    return square;
  }

  public static void main(String[] args) {
    int[] a1 = { 3, 1, 4, 6, 5 };
    System.out.println(isPythagorean(a1)); // true

    int[] a2 = { 10, 4, 6, 12, 5 };
    System.out.println(isPythagorean(a2)); // false

    int[] a3 = { 5, 6, 12, 13 };
    System.out.println(isPythagorean(a3)); // true
  }
}