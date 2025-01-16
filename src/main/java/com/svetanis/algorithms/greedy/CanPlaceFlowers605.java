package com.svetanis.algorithms.greedy;

// 605. Can Place Flowers

public final class CanPlaceFlowers605 {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static boolean placeFlowers(int[] a, int n) {
    int len = a.length;
    for (int i = 0; i < len; i++) {
      int left = i == 0 ? 0 : a[i - 1];
      int right = i == len - 1 ? 0 : a[i + 1];
      if (left + a[i] + right == 0) {
        a[i] = 1;
        n--;
      }
    }
    return n <= 0;
  }

  public static void main(String[] args) {
    int[] a = { 1, 0, 0, 0, 1 };
    System.out.println(placeFlowers(a, 1)); // true
    int[] a1 = { 1, 0, 0, 0, 1 };
    System.out.println(placeFlowers(a1, 2)); // false
  }
}
