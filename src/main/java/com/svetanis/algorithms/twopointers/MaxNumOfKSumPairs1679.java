package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 1679. Max Number of K-Sum Pairs
//
// Input: an array of positive integers and a target k. One operation removes two
// numbers that add up to k.
// Return: the most operations that can be done.
//
// The one idea: sort, then run the converging loop of LC 167 Two Sum II, but on a
// match both numbers are used up, so BOTH pointers move and the count goes up. A
// number can only pair with the value k minus itself, so which copy pairs with which
// never matters. When the sum is too small, the smallest number is too small even
// with the largest one left, so it is in no pair and is dropped; the mirror holds
// for the largest number when the sum is too big.
// The array is sorted in place: the caller's order is lost.
//
// Siblings -- the same converging loop, different questions:
//   twopointers.TwoSumSorted167 -- stops at the first pair and returns its positions
//   twopointers.pairs.CountPairsGivenSum2SortedTwoPointers -- counts pairs taking
//     one number from each of two sorted arrays
//
// Time: O(n log n) -- the sort; the loop after it is O(n).
// Space: O(log n) -- the sort's recursion; the loop itself is O(1).

public final class MaxNumOfKSumPairs1679 {

  public static int countPairs(int[] a, int k) {
    Arrays.sort(a);                 // SORT: smallest and largest at the ends
    int n = a.length;
    int left = 0;                   // START: the smallest number
    int right = n - 1;              // START: the largest number
    int count = 0;
    while (left < right) {          // STOP: a pair needs two different numbers
      int sum = a[left] + a[right]; // COMPARE
      if (sum == k) {
        left++;                     // FOUND: both numbers are used up
        right--;
        count++;                    // RECORD one operation
      } else if (sum < k) {
        left++;                     // DROP LEFT: too small even with the largest
      } else {
        right--;                    // DROP RIGHT: too big even with the smallest
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3, 4 };
    System.out.println(countPairs(a, 5)); // 2

    int[] a1 = { 3, 1, 3, 4, 3 };
    System.out.println(countPairs(a1, 6)); // 1

    int[] a2 = { 2, 2, 2, 2, 2 };
    System.out.println(countPairs(a2, 4)); // 2 -- the fifth 2 has no partner left
  }
}