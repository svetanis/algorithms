package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 167. Two Sum II - Input Array Is Sorted
//
// Input: an array sorted in non-decreasing order, and a target. Exactly one pair of
// numbers at two different positions adds up to the target.
// Return: the two positions, counted FROM 1, as {index1, index2} with index1 < index2.
// Only constant extra space is allowed, which rules out the map of LC 1 Two Sum.
//
// The one idea: pair each end with its most favourable partner. The largest number's
// smallest partner is a[left]; if even that sum is too big, every sum with the largest
// number is too big, so it is in no pair at all and is dropped for good. Mirror for
// the smallest number when the sum is too small. Each step drops one number that
// cannot be in the answer, so the answer is never dropped.
//
// Siblings -- the same converging loop, different questions:
//   twopointers.pairs.PairGivenSumSorted -- returns the two VALUES (as an int[]), not positions
//   pairs.TwoSumLessThanK1099            -- the largest pair sum strictly below k, unsorted input
//
// Time: O(n) -- every step drops one number, so at most n - 1 steps.
// Space: O(1).

public final class TwoSumSorted167 {

  public static int[] twoSum(int[] a, int target) {
    int left = 0;                                 // START: the smallest number
    int right = a.length - 1;                     // START: the largest number
    while (left < right) {                        // STOP: a pair needs two different positions
      int sum = a[left] + a[right];               // COMPARE
      if (sum == target) {
        return new int[] { left + 1, right + 1 }; // FOUND: positions counted from 1
      } else if (sum < target) {
        left++;                                   // DROP LEFT: too small even with the largest
      } else {
        right--;                                  // DROP RIGHT: too big even with the smallest
      }
    }
    return new int[] { -1, -1 };                  // unreachable on LeetCode: one solution is guaranteed
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(twoSum(new int[] { 2, 7, 11, 15 }, 9))); // [1, 2]
    System.out.println(Arrays.toString(twoSum(new int[] { 2, 3, 4 }, 6)));      // [1, 3]
    System.out.println(Arrays.toString(twoSum(new int[] { -1, 0 }, -1)));       // [1, 2]
  }
}
