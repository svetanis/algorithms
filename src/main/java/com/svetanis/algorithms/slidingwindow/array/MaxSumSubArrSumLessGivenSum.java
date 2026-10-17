package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.max;

// Maximum subarray sum at most a given sum -- no LeetCode number; source unknown

// Given an array of integers and a sum. 
// We have to find sum of subarray having maximum sum 
// less than or equal to given sum in array. 
// Takes a and the given sum k. Returns that largest sum, or 0 when no
// number fits (or a is empty).

// The window assumes every number is >= 0 and k >= 0. With no
// negatives, adding a number never lowers the sum and removing one
// never raises it, so for each right end the window that is shrunk
// only until it fits is the largest sum ending there that fits. A
// negative number breaks that and the answer can come out too small.

// Time: O(n) -- each index enters the window once and leaves at most once
// Space: O(1) -- the running sum and two indices

// Siblings:
//   LongestSubArrSumAtMostK -- the same window and the same "at most",
//     but reports the LONGEST length that fits, not the largest sum.
//   CountSubArrsSumLessThanK -- counts the subarrays with sum below k.

public final class MaxSumSubArrSumLessGivenSum {

  public static int maxSum(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    int n = a.length;
    int sum = 0;
    int max = 0;
    int left = 0;

    for (int i = 0; i < n; i++) {
      sum += a[i]; // ADD: a[i] enters; the shrink and the measure follow
      while (sum > k && left < n) { // SHRINK: while the sum is over k
        sum -= a[left++]; // REMOVE
      }
      max = max(max, sum); // MEASURE: after the shrink, the sum fits
    }
    return max;
  }

  public static void main(String[] args) {

    int[] a1 = { 1, 2, 3, 4, 5 };
    System.out.println(maxSum(a1, 11)); // 10 : 1,2,3,4

    int[] a2 = { 2, 4, 6, 8, 10 };
    System.out.println(maxSum(a2, 7)); // 6 : 6

  }
}
