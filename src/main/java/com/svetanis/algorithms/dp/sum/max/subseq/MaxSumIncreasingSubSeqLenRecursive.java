package com.svetanis.algorithms.dp.sum.max.subseq;

import static java.lang.Math.max;

// Given a number sequence, 
// find the increasing subsequence with the highest sum. 
// Write a method that returns the highest sum.

public final class MaxSumIncreasingSubSeqLenRecursive {

  public static int maxSum(int[] a) {
    return maxSum(a, 0, -1, 0);
  }

  private static int maxSum(int[] a, int index, int prev, int sum) {
    int n = a.length;

    // base case
    if (index == n) {
      return sum;
    }

    // include
    int incl = sum;
    if (prev == -1 || a[index] > a[prev]) {
      incl = maxSum(a, index + 1, index, sum + a[index]);
    }
    // exclude
    int excl = maxSum(a, index + 1, prev, sum);
    return max(incl, excl);
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 101, 2, 3, 100, 4, 5 };
    System.out.println(maxSum(a1)); // 106

    int[] a2 = { 3, 4, 5, 10 };
    System.out.println(maxSum(a2)); // 22

    int[] a3 = { 10, 5, 4, 3 };
    System.out.println(maxSum(a3)); // 10
  }
}