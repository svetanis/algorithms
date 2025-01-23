package com.svetanis.algorithms.search.binary;

// 1539. Kth Missing Positive Number

public final class KthMissingPositiveNum1539 {
  // Time Complexity: O(log n)
  // Space Complexity: O(1)

  public static int kthMissing(int[] a, int k) {
    if (a[0] > k) {
      return k;
    }
    int left = 0;
    int right = a.length;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (a[mid] - mid - 1 >= k) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    int missingNums = a[left - 1] - (left - 1) - 1;
    return a[left - 1] + k - missingNums;
  }

  public static void main(String[] args) {
    int[] a = { 2, 3, 4, 7, 11 };
    System.out.println(kthMissing(a, 5)); // 9
    int[] a1 = { 1, 2, 3, 4 };
    System.out.println(kthMissing(a1, 2)); // 6
  }
}