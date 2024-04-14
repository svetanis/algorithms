package com.svetanis.algorithms.slidingwindow.array;

import static com.svetanis.java.base.utils.Nums.isOdd;
import static java.lang.Math.max;

// given array of positive integers
// find the longest subarray that has
// exactly k odd numbers

public final class LongestSubArrKOddNums {

  public static int longest(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int n = a.length;
    int max = 0;
    int count = 0;
    int left = 0;

    for (int i = 0; i < n; i++) {
      if (isOdd(a[i])) {
        count++;
      }

      while (count > k && left <= i) {
        if (isOdd(a[left++])) {
          count--;
        }
      }

      if (count == k) {
        max = max(max, i - left + 1);
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a1 = { 3, 4, 6, 1, 9, 8, 2, 10 };
    System.out.println(longest(a1, 2));
    
    int[] a2 = { 2, 3, 4, 11, 4, 12, 7};
    System.out.println(longest(a2, 1));

  }
}
