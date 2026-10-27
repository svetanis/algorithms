package com.svetanis.algorithms.prefixsum;

import java.util.Arrays;

// 724. Find Pivot Index
//
// Input: an array a (nums on LeetCode). The pivot index is an index where the sum of the elements
// strictly to its left equals the sum of the elements strictly to its right; at the ends, the
// missing side sums to 0. Returns the leftmost pivot index, or -1 when there is none. On LeetCode:
// 1 <= a.length <= 10^4, -1000 <= a[i] <= 1000.
//
// The one idea: walking left to right, left is the running sum of everything before i. The right
// side is everything else except a[i]: total - left - a[i]. So one total up front and one running
// sum answer every index, and no prefix array is stored.
//
// Time: O(n) -- one pass for the total, one for the walk.
// Space: O(1) -- the total and the running sum.
//
// Siblings: none.

public final class PivotIndex {

  public static int pivotIndex(int[] a) {
    int n = a.length;
    int left = 0; // LEFT: the sum of a[0 .. i - 1]
    int total = Arrays.stream(a).sum();
    for (int i = 0; i < n; i++) {
      int right = total - left - a[i]; // RIGHT: everything after i
      if (left == right) {
        return i; // FIRST pivot from the left
      }
      left += a[i]; // MOVE a[i] into the left side
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