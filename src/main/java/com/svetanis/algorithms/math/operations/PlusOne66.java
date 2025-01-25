package com.svetanis.algorithms.math.operations;

import com.svetanis.java.base.utils.Print;

// 66. Plus One

public final class PlusOne66 {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int[] plusOne(int[] a) {
    int n = a.length;
    for (int i = n - 1; i >= 0; i--) {
      a[i] += 1;
      a[i] %= 10;
      if (a[i] != 0) {
        return a;
      }
    }

    int[] result = new int[n + 1];
    result[0] = 1;
    return result;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 3 };
    Print.print(plusOne(a1)); // 1,2,4

    int[] a2 = { 4, 3, 2, 1 };
    Print.print(plusOne(a2)); // 4,3,2,2

    int[] a3 = { 9 };
    Print.print(plusOne(a3)); // 1,0
  }
}