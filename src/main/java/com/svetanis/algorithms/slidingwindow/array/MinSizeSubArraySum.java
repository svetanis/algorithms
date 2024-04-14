package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

// Given an array of positive integers and a number ‘S,’ 
// find the length of the smallest contiguous subarray 
// whose sum is greater than or equal to ‘S’. 
// Return 0 if no such subarray exists.

public final class MinSizeSubArraySum {

  public static int minSum(int[] a, int k) {
    // Time complexity: O(n)

    int n = a.length;
    int min = MAX_VALUE;
    int sum = 0;
    int left = 0;

    for (int right = 0; right < n; right++) {
      sum += a[right];
      while (sum >= k && left < n) {
        min = min(min, right - left + 1);
        sum -= a[left];
        left++;
      }
    }
    return min == MAX_VALUE ? 0 : min;
  }

  public static void main(String[] args) {
    int[] ar1 = { 1, 4, 45, 6, 0, 19 };
    System.out.println(minSum(ar1, 51));

    int[] ar2 = { 1, 10, 5, 2, 7 };
    System.out.println(minSum(ar2, 9));

    int[] ar3 = { 1, 11, 100, 1, 0, 200, 3, 2, 1, 250 };
    System.out.println(minSum(ar3, 280));
  }
}
