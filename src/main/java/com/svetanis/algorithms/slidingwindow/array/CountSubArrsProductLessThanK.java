package com.svetanis.algorithms.slidingwindow.array;

public final class CountSubArrsProductLessThanK {

  public static int count(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int n = a.length;
    int prod = 1;
    int count = 0;

    for (int left = 0, right = 0; right < n; right++) {
      prod *= a[right];
      while (left < right && prod > k) {
        prod /= a[left++];
      }

      if (prod < k) {
        count += (right - left + 1);
      }
    }
    return count;
  }

  public static void main(String[] args) {
    
    int[] a1 = { 1, 2, 3, 4 };
    System.out.println(count(a1, 10));

    int[] a2 = { 1, 9, 2, 8, 6, 4, 3 };
    System.out.println(count(a2, 100));

    int[] a3 = { 5, 3, 2};
    System.out.println(count(a3, 16));

    int[] a4 = { 100, 200};
    System.out.println(count(a4, 100));

    int[] a5 = { 100, 200};
    System.out.println(count(a5, 101));

  }
}
