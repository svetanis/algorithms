package com.svetanis.algorithms.slidingwindow.array;

public final class CountSubArrsSumLessThanK {

  public static int count(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int n = a.length;
    int sum = 0;
    int count = 0;

    for (int left = 0, right = 0; right < n; right++) {
      sum += a[right];
      if (sum < k) {
        right++;
        count += (right - left);
      } else {
        sum -= a[left++];
      }
    }
    return count;
  }

  public static void main(String[] args) {

    int[] a1 = { 2, 5, 6 };
    System.out.println(count(a1, 10));

    int[] a2 = { 1, 11, 2, 3, 15 };
    System.out.println(count(a2, 10));

  }
}
