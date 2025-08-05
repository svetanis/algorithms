package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 611. Valid Triangle Number

public final class ValidTriangleNumber611 {
  // Time Complexity: O(n log n)
  // Space Complexity: O(1)

  public static int vtn(int[] a) {
    Arrays.sort(a);
    int count = 0;
    for (int i = a.length - 1; i >= 2; i--) {
      int left = 0;
      int right = i - 1;
      while (left < right) {
        int sum = a[left] + a[right];
        if (sum > a[i]) {
          count += right - left;
          right--;
        } else {
          left++;
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = { 2, 2, 3, 4 };
    System.out.println(vtn(a)); // 3

    int[] a1 = { 4, 2, 3, 4 };
    System.out.println(vtn(a1)); // 4
  }
}