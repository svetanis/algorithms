package com.svetanis.algorithms.slidingwindow.string;

import static java.lang.Math.max;

// Given an array containing 0s and 1s, 
// if you are allowed to replace no more than ‘k’ 0s with 1s, 
// find the length of the longest subarray having all 1s.

public final class LongestSubArrLenAllOnesAfterReplacement {

  public static int subStrLen(int[] a, int k) {
    // Time complexity: O(n)

    int max = 0;
    int ones = 0;
    int left = 0;
    for (int right = 0; right < a.length; right++) {
      if (a[right] == 1) {
        ones++;
      }

      if (right - left + 1 - ones > k) {
        if (a[left] == 1) {
          ones--;
        }
        left++;
      }
      max = max(max, right - left + 1);
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a1 = { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 };
    int[] a2 = { 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1 };
    System.out.println(subStrLen(a1, 2));
    System.out.println(subStrLen(a2, 3));
  }
}
