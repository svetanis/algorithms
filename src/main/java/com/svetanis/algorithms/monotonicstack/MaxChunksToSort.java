package com.svetanis.algorithms.monotonicstack;

// 769. Max Chunks To Make Sorted
//
// arr is a permutation of 0..n-1. Split it into the most chunks such that sorting each chunk
// on its own, then joining them, gives the whole array sorted.
//
// No stack is needed here: a chunk can end at i exactly when the largest value so far is i,
// because then positions 0..i hold exactly the values 0..i. MaxChunksToSortII drops the
// permutation guarantee and needs the stack.

public final class MaxChunksToSort {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int maxChunks(int[] arr) {
    int n = arr.length;
    int largest = 0;
    int chunks = 0;
    for (int i = 0; i < n; i++) {
      largest = Math.max(largest, arr[i]);
      if (largest == i) { // 0..i are all here: a chunk can end
        chunks++;
      }
    }
    return chunks;
  }

  public static void main(String[] args) {
    int[] a1 = { 4, 3, 2, 1, 0 };
    System.out.println(maxChunks(a1)); // 1

    int[] a2 = { 1, 0, 2, 3, 4 };
    System.out.println(maxChunks(a2)); // 4
  }
}
