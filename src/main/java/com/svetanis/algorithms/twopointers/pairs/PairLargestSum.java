package com.svetanis.algorithms.twopointers.pairs;

import java.util.Arrays;

// Largest Pair Sum
//
// Input: an array of n >= 2 integers, in any order; values may repeat.
// Return: the pair {first, second} with the largest sum over two different positions:
// the largest value, then the largest value at any other position.
//
// The one idea: one pass keeping the two largest numbers seen so far. A number above
// first pushes first down to second; a number above second only replaces second. A
// copy of the maximum counts as second -- {1, 5, 5} pairs 5 with 5 -- because it sits
// at a different position.
//
// Siblings:
//   PairMaxProduct -- the largest PRODUCT, where two negatives can win
//   PairMaxSumTwoArrays -- one number from each of two arrays
//   CountPairsMaxSum -- counts the pairs that reach the largest sum
//
// Time: O(n) -- one pass.
// Space: O(1).

public final class PairLargestSum {

  public static int[] pair(int[] a) {
    int first;
    int second;
    if (a[0] > a[1]) {  // START: the first two numbers, larger first
      first = a[0];
      second = a[1];
    } else {
      first = a[1];
      second = a[0];
    }
    for (int i = 2; i < a.length; i++) {
      if (a[i] > first) {
        second = first; // MOVE the old largest down to second
        first = a[i];
      } else if (a[i] > second) {
        second = a[i];  // RECORD a new second, a copy of first included
      }
    }
    return new int[] { first, second };
  }

  public static void main(String[] args) {
    int[] a = { 12, 34, 10, 6, 40 };
    System.out.println(Arrays.toString(pair(a))); // [40, 34]

    int[] a1 = { 1, 5, 5 };
    System.out.println(Arrays.toString(pair(a1))); // [5, 5]
  }
}
