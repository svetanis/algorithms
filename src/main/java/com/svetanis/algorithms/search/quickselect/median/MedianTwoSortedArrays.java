package com.svetanis.algorithms.search.quickselect.median;

import java.util.Arrays;

// 4. Median of Two Sorted Arrays

public final class MedianTwoSortedArrays {
  // Time Complexity: O(log (n + m))

  public static double median(int[] a1, int[] a2) {
    Arrays.sort(a2);
    int n = a1.length;
    int m = a2.length;
    int target1 = (n + m + 1) / 2;
    int target2 = (n + m + 2) / 2;
    int left = kthElement(a1, 0, a2, 0, target1);
    int right = kthElement(a1, 0, a2, 0, target2);
    return (left + right) / 2.0;
  }

  private static int kthElement(int[] a1, int start1, int[] a2, int start2, int k) {
    int n = a1.length;
    int m = a2.length;
    if (start1 >= n) {
      return a2[start2 + k - 1];
    }
    if (start2 >= m) {
      return a1[start1 + k - 1];
    }
    if (k == 1) {
      return Math.min(a1[start1], a2[start2]);
    }
    int mid = k / 2;
    boolean one = start1 + mid - 1 < n;
    boolean two = start2 + mid - 1 < m;
    int mid1 = one ? a1[start1 + mid - 1] : Integer.MAX_VALUE;
    int mid2 = two ? a2[start2 + mid - 1] : Integer.MAX_VALUE;

    if (mid1 < mid2) {
      return kthElement(a1, start1 + mid, a2, start2, k - mid);
    } else {
      return kthElement(a1, start1, a2, start2 + mid, k - mid);
    }
  }

  public static void main(String[] args) {
    int[] s1 = { 1, 3 };
    int[] p1 = { 2 };
    System.out.println(median(s1, p1)); // 2.0

    int[] s2 = { 1, 2 };
    int[] p2 = { 3, 4 };
    System.out.println(median(s2, p2)); // 2.5
  }
}