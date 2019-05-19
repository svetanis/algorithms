package com.svetanis.algorithms.slidingwindow.array;

import com.svetanis.java.base.Pair;

public class GivenSumSubArraySlidingWindow {

  public static Pair<Integer, Integer> sum(int[] a, int k) {
    // Time complexity: O(n)

    int n = a.length;
    int start = 0;
    int sum = a[0];

    // add elements one by one to currentSum
    // and if the currentSum exceeds the sum,
    // then remove starting element
    for (int i = 1; i <= n; ++i) {
      while (sum > k && start < i - 1) {
        sum = sum - a[start++];
      }
      if (sum == k) {
        return Pair.build(start, i - 1);
      }
      if (i < n) {
        sum += a[i];
      }
    }
    return Pair.build(-1, -1);
  }

  public static void main(String[] args) {
    int[] a = { 15, 2, 4, 8, 9, 5, 10, 23 };
    System.out.println(sum(a, 23));
    
    int[] a1 = { 1, 4, 20, 3, 10, 5 };
    System.out.println(sum(a1, 33));
    
    int[] a3 = { 1, 4, 0, 0, 3, 10, 5 };
    System.out.println(sum(a3, 7));
    
    int[] a4 = { 1, 4 };
    System.out.println(sum(a4, 0));
  }
}