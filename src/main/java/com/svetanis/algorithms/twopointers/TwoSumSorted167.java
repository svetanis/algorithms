package com.svetanis.algorithms.twopointers;

import com.svetanis.java.base.utils.Print;

// 167. Two Sum II - Input Array is sorted
// com.svetanis.datastructures.array.pairs.PairGivenSumSorted

public final class TwoSumSorted167 {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int[] twoSum(int[] a, int target) {
    int left = 0;
    int right = a.length - 1;
    while (left < right) {
      int sum = a[left] + a[right];
      if (sum == target) {
        return new int[] { left + 1, right + 1 };
      } else if (sum < target) {
        left++;
      } else {
        right--;
      }
    }
    return new int[] { -1, -1 };
  }

  public static void main(String[] args) {
    int[] a = { 2, 7, 11, 15 };
    Print.print(twoSum(a, 9)); // 1,2

    int[] a1 = { 2, 3, 4 };
    Print.print(twoSum(a1, 6)); // 1,3

    int[] a2 = { -1, 0 };
    Print.print(twoSum(a2, -1)); // 1,2
  }
}