package com.svetanis.algorithms.prefixsum;

import java.util.Arrays;

// 724. Find Pivot Index

public final class PivotIndex {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int pivotIndex(int[] a) {
    int n = a.length;
    int left = 0;
    int total = Arrays.stream(a).sum();
    for (int i = 0; i < n; i++) {
      int right = total - left - a[i];
      if (left == right) {
        return i;
      }
      left += a[i];
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] a = { 1, 7, 3, 6, 5, 6 };
    System.out.println(pivotIndex(a)); // 3

    int[] a1 = { 1, 2, 3 };
    System.out.println(pivotIndex(a1)); // -1

    int[] a2 = { 2, 1, -1 };
    System.out.println(pivotIndex(a2)); // 0
  }
}