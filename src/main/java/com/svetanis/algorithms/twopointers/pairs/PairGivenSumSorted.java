package com.svetanis.algorithms.twopointers.pairs;

import static java.util.Arrays.sort;

import java.util.Arrays;

// Find a Pair With a Given Sum in a Sorted Array
//
// Input: an array sorted in non-decreasing order, and a target.
// Return: a pair {x, y} of values at two different positions with x + y == target,
// smaller first; {-1, -1} if there is none.
//
// The one idea: pair each end with its most favourable partner. The largest number's
// smallest partner is a[left]; if even that sum is too big, the largest number is in no
// pair and is dropped. Mirror for the smallest number when the sum is too small. Each
// step drops a number that cannot be in a pair, so a pair is never skipped.
//
// Siblings:
//   twopointers.TwoSumSorted167 -- the same loop, returns positions counted from 1
//   PairGivenSumHashing -- unsorted input, one pass with a set
//   PairsGivenSumSorted -- every pair, not the first
//   PairGivenSumRotated -- the same loop on a sorted array that was rotated
//
// Time: O(n) -- every step drops one number.
// Space: O(1).

public final class PairGivenSumSorted {

  public static int[] pair(int[] a, int target) {
    int left = 0;                               // START: the smallest number
    int right = a.length - 1;                   // START: the largest number
    while (left < right) {                      // STOP: a pair needs two different positions
      int sum = a[left] + a[right];             // COMPARE
      if (sum == target) {
        return new int[] { a[left], a[right] }; // FOUND
      } else if (sum < target) {
        left++;                                 // DROP LEFT: too small even with the largest
      } else {
        right--;                                // DROP RIGHT: too big even with the smallest
      }
    }
    return new int[] { -1, -1 };                // RETURN: no pair
  }

  public static void main(String[] args) {
    int[] a = { 1, 4, 45, 6, 10, -8 };
    sort(a);
    System.out.println(Arrays.toString(pair(a, 16))); // [6, 10]
  }
}
