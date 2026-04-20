package com.svetanis.algorithms.monotonicstack;

// 769. Max Chunks To Make Sorted

public final class MaxChunksToSort {
  // Time complexity: O(n)
  // Space complexity: O(1)

  public static int maxChunks(int[] a) {
    int n = a.length;
    int max = 0;
    int count = 0;
    for (int i = 0; i < n; i++) {
      max = Math.max(max, a[i]);
      if (max == i) {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a1 = { 4, 3, 2, 1, 0 };
    System.out.println(maxChunks(a1)); // 1

    int[] a2 = { 1, 0, 2, 3, 4 };
    System.out.println(maxChunks(a2)); // 4
  }
}
