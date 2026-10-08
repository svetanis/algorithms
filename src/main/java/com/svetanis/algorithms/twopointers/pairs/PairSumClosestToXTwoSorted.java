package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;

import java.util.Arrays;

// Pair With Sum Closest to x From Two Sorted Arrays
//
// Input: two non-empty arrays sorted in non-decreasing order, and a target x.
// Return: {y, z}, y from a1 and z from a2, whose sum is closest to x; the first one met
// when several tie.
//
// The one idea: the converging loop with the two ends in two arrays: left walks a1 up
// from its smallest number, right walks a2 down from its largest. If the sum is above
// x, every number left in a1 is at least a1[left], so a2[right] only makes sums further
// above x: drop right. A sum at or below x mirrors it: drop left.
//
// Siblings:
//   PairSumClosestToXSorted -- both numbers from one array
//   CountPairsGivenSum2SortedTwoPointers -- the same walk, counting exact sums
//   PairMinDiffTwoUnsorted -- one number from each array, smallest difference
//
// Time: O(n + m) -- every step moves one pointer.
// Space: O(1).

public final class PairSumClosestToXTwoSorted {

  public static int[] pair(int[] a1, int[] a2, int x) {
    int n = a1.length;
    int m = a2.length;
    int left = 0;                    // START: the smallest of a1
    int right = m - 1;               // START: the largest of a2
    int first = -1;
    int second = -1;
    int diff = MAX_VALUE;            // the closest distance to x so far
    while (left < n && right >= 0) { // STOP when either array runs out
      int sum = a1[left] + a2[right];
      if (abs(sum - x) < diff) {
        diff = abs(sum - x);         // RECORD the closest pair so far
        first = left;
        second = right;
      }
      if (sum > x) {
        right--;                     // DROP RIGHT: every number left in a1 makes the sum bigger
      } else {
        left++;                      // DROP LEFT: every number left in a2 makes the sum smaller
      }
    }
    return new int[] { a1[first], a2[second] };
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 4, 5, 7 };
    int[] a2 = { 10, 20, 30, 40 };
    System.out.println(Arrays.toString(pair(a1, a2, 32))); // [1, 30]

    int[] a3 = { 1, 4, 5, 7 };
    int[] a4 = { 10, 20, 30, 40 };
    System.out.println(Arrays.toString(pair(a3, a4, 50))); // [7, 40]
  }
}
