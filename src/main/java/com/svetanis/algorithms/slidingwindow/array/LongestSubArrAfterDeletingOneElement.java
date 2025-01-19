package com.svetanis.algorithms.slidingwindow.array;

// 1493. Longest Subarray of 1's After Deleting One Element

public final class LongestSubArrAfterDeletingOneElement {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static int sw(int[] a) {
    int n = a.length;
    int[] prefix = prefix(a);
    int[] suffix = suffix(a);
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      int sum = prefix[i] + suffix[i];
      max = Math.max(max, sum);
    }
    return max == n ? max - 1 : max;
  }

  // count consecutive ones from left to right
  private static int[] prefix(int[] a) {
    int n = a.length;
    int[] prefix = new int[n];
    for (int i = 1; i < n; i++) {
      if (a[i - 1] == 1) {
        prefix[i] = prefix[i - 1] + 1;
      }
    }
    return prefix;
  }

  // count consecutive ones from right to left
  private static int[] suffix(int[] a) {
    int n = a.length;
    int[] suffix = new int[n];
    for (int i = n - 2; i >= 0; i--) {
      if (a[i + 1] == 1) {
        suffix[i] = suffix[i + 1] + 1;
      }
    }
    return suffix;
  }

  public static void main(String args[]) {
    int[] a1 = { 1, 1, 0, 1 };
    System.out.println(sw(a1)); // 3

    int[] a2 = { 0, 1, 1, 1, 0, 1, 1, 0, 1 };
    System.out.println(sw(a2)); // 5

    int[] a3 = { 1, 1, 1 };
    System.out.println(sw(a3)); // 2
  }
}