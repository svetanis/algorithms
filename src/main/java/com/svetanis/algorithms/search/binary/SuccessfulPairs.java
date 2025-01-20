package com.svetanis.algorithms.search.binary;

import static com.svetanis.java.base.utils.Print.print;

import java.util.Arrays;

// 2300. Successful Pairs of Spells and Potions

public final class SuccessfulPairs {
  // Time Complexity: O(n log n)
  // Space Complexity: O(s)

  public static int[] successfulPairs(int[] spells, int[] potions, long success) {
    Arrays.sort(potions);
    int n = spells.length;
    int m = potions.length;
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      int left = 0;
      int right = m;
      while (left < right) {
        int mid = left + (right - left) / 2;
        long product = (long) spells[i] * potions[mid];
        if (product >= success) {
          right = mid;
        } else {
          left = mid + 1;
        }
      }
      a[i] = m - left;
    }
    return a;
  }

  public static void main(String[] args) {
    int[] s1 = { 5, 1, 3 };
    int[] p1 = { 1, 2, 3, 4, 5 };
    print(successfulPairs(s1, p1, 7)); // [4 0 3]

    int[] s2 = { 3, 1, 2 };
    int[] p2 = { 8, 5, 8 };
    print(successfulPairs(s2, p2, 16)); // [2 0 2]
  }
}