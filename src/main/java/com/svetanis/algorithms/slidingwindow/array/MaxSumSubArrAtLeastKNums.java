package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.max;

import com.svetanis.java.base.utils.Arrays;

// Given an array, find the subarray 
// (containing at least k numbers)
// which has the largest sum. 

public final class MaxSumSubArrAtLeastKNums {

  public static int maxSum(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int n = a.length;

    // sum of first k elements
    int sum = Arrays.sum(a, k);
    int max = sum;
    // max sum till index i inclusive
    int[] kadane = kadane(a);
    for (int i = k; i < n; i++) {
      sum += a[i] - a[i - k];
      max = max(max, sum);
      // include max sum till [i - k]
      // if it increases overall max
      max = max(max, sum + kadane[i - k]);
    }
    return max;
  }

  public static int[] kadane(int[] a) {
    int[] arr = new int[a.length];
    arr[0] = a[0];
    int sum = a[0];
    int max = a[0];
    for (int i = 1; i < a.length; i++) {
      sum = max(a[i], sum + a[i]);
      max = max(max, sum);
      arr[i] = max;
    }
    return arr;
  }

  public static void main(String[] args) {

    int[] a1 = { 1, 2, 3, -10, -3 };
    System.out.println(maxSum(a1, 4));

    int[] a2 = { -4, -2, 1, -3 };
    System.out.println(maxSum(a2, 2));

    int[] a3 = { 1, 1, 1, 1, 1, 1 };
    System.out.println(maxSum(a3, 2));

  }
}
