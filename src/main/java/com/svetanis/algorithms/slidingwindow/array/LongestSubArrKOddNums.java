package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.max;

// Longest subarray with exactly k odd numbers -- no LeetCode number; source unknown

// given array of positive integers
// find the longest subarray that has
// exactly k odd numbers
// Takes a and k >= 0; returns that subarray's length, or 0 if no
// subarray holds exactly k odd numbers.

// For each right end, the longest answer starts as far left as it can
// while the window holds no more than k odd numbers. So the window
// shrinks only while it holds MORE than k, and is measured only when it
// holds exactly k: fewer than k is not an answer, but adding numbers on
// the right can still make it one, so it is not shrunk either.

// Time: O(n) -- each index enters the window once and leaves at most once
// Space: O(1) -- a count of odd numbers and two indices

// Sibling: prefixsum.CountNiceSubArrs (1248) asks HOW MANY subarrays
// hold exactly k odd numbers, and counts them with prefix sums of odd
// numbers instead of a window.

public final class LongestSubArrKOddNums {

  public static int longest(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int n = a.length;
    int max = 0;
    int count = 0;
    int left = 0;

    for (int i = 0; i < n; i++) {
      if (a[i] % 2 != 0) {
        count++; // ADD: an odd number entering
      }

      while (count > k && left <= i) { // SHRINK: while more than k odd numbers
        if (a[left++] % 2 != 0) {
          count--; // REMOVE: an odd number leaving; an even one changes nothing
        }
      }

      if (count == k) {
        max = max(max, i - left + 1); // MEASURE: only a window with exactly k
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a1 = { 3, 4, 6, 1, 9, 8, 2, 10 };
    System.out.println(longest(a1, 2)); // 7
    
    int[] a2 = { 2, 3, 4, 11, 4, 12, 7};
    System.out.println(longest(a2, 1)); // 4

  }
}
