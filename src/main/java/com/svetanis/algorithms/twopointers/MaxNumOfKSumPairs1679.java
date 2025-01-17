package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 1679. Max Number of K-Sum Pairs
// com.svetanis.datastructures.array.pairs.CountPairsGivenSum2SortedTwoPointers

public final class MaxNumOfKSumPairs1679 {
  // Time Complexity: O(n log n)
  // Space Complexity: O(1)

  public static int countPairs(int[] a, int k) {
    Arrays.sort(a);
    int n = a.length;
    int left = 0;
    int right = n - 1;
    int count = 0;
    while (left < right) {
      int sum = a[left] + a[right];
      if (sum == k) {
        left++;
        right--;
        count++;
      } else if (sum < k) {
        left++;
      } else {
        right--;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3, 4 };
    System.out.println(countPairs(a, 5)); // 2

    int[] a1 = { 3, 1, 3, 4, 3 };
    System.out.println(countPairs(a1, 6)); // 1
  }
}