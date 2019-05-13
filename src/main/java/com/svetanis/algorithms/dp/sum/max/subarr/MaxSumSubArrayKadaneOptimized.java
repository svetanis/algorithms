package com.svetanis.algorithms.dp.sum.max.subarr;

public final class MaxSumSubArrayKadaneOptimized {

  public static int kadane(int[] a) {
    int n = a.length;
    int max = 0;
    int sum = 0;

    // don't compare for all elements
    // compare only when maxEndingHere > 0
    for (int i = 0; i < n; ++i) {
      sum += a[i];
      
      if (sum < 0) {
        sum = 0;
      } else if (max < sum) {
        max = sum;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a1 = { -2, -3, 4, -1, -2, 1, 5, -3 };
    System.out.println(kadane(a1));

    int[] a2 = { -1, 4, -2, 5, -5, 2, -20, 6 };
    System.out.println(kadane(a2));

    int[] a3 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
    System.out.println(kadane(a3));
    
    int[] a4 = {2, -8, 3, -2, 4, -10};
    System.out.println(kadane(a4));

    int[] a5 = {-3, -10, -5};
    System.out.println(kadane(a5));
  }
}