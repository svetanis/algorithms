package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.max;

// Given an array of integers and a sum. 
// We have to find sum of subarray having maximum sum 
// less than or equal to given sum in array. 

public final class MaxSumSubArrSumLessGivenSum {

  public static int maxSum(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    int n = a.length;
    int sum = a[0];
    int max = 0;
    int left = 0;

    for (int i = 1; i < n; i++) {
      while (sum > k && left < n) {
        sum -= a[left++];
      }
      max = max(max, sum);
      sum += a[i];
    }
    return max;
  }

  public static void main(String[] args) {

    int[] a1 = { 1, 2, 3, 4, 5 };
    System.out.println(maxSum(a1, 11));

    int[] a2 = { 2, 4, 6, 8, 10 };
    System.out.println(maxSum(a2, 7));

  }
}
