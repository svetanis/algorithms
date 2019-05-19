package com.svetanis.algorithms.slidingwindow.array;

public final class CountSubArrsGivenProduct {

  public static int count(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int n = a.length;
    int prod = 1;
    int count = 0;

    for (int left = 0, right = 0; right < n; right++) {
      prod *= a[right];
      if (prod > k) {
        while (left <= right && prod > k) {
          prod /= a[left];
          left++;
        }
      }

      if (prod == k) {
        int ones = 0;
        while (right + 1 < n && a[right + 1] == 1) {
          ones++;
          right++;
        }
        count += (ones + 1);

        while (left <= right && a[left] == 1) {
          count += (ones + 1);
          left++;
        }

        prod /= a[left];
        left++;
      }
    }
    return count;
  }

  public static void main(String[] args) {

    int[] a1 = { 2, 1, 1, 1, 4, 5 };
    System.out.println(count(a1, 4));

    int[] a2 = { 1, 2, 3, 4, 1 };
    System.out.println(count(a2, 24));

  }
}
