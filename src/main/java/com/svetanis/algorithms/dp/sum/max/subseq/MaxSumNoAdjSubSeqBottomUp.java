package com.svetanis.algorithms.dp.sum.max.subseq;

import static java.lang.Math.max;

public final class MaxSumNoAdjSubSeqBottomUp {

  public static int maxSum(int[] a) {
    // O(n) in both space and time
    
    int n = a.length;
    int[] sum = new int[n];

    for (int i = 0; i < n; i++) {
      if (i == 0) {
        sum[0] = a[0];
      } else if (i == 1) {
        sum[1] = max(sum[0], a[1]);
      } else {
        sum[i] = max(sum[i - 2] + a[i], sum[i - 1]);
      }
    }
    return sum[n - 1];
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 3, 4 };
    System.out.println(maxSum(a1));

    int[] a2 = { 2, -1, 2, 3, 4, -5 };
    System.out.println(maxSum(a2));

    int[] a3 = { 3, 2, 5, 10, 7 }; // 3 + 5 + 7 = 15
    System.out.println(maxSum(a3));

    int[] a4 = { 1, -1, 3, 8, 4 }; // 1 + 8 = 9
    System.out.println(maxSum(a4));

    int[] a5 = { 1, 5, 3, 9, 4 }; // 5 + 9 = 14
    System.out.println(maxSum(a5));

    int[] a6 = { 1, 2, 3, 4, 5 }; // 1 + 3 + 5 = 9
    System.out.println(maxSum(a6));

    int[] a7 = { 1, 5, 3, 7, -2, -4, 2 }; // 5 + 7 + 2 = 14
    System.out.println(maxSum(a7));

    int[] a8 = { 5, 5, 10, 40, 50, 35 };
    System.out.println(maxSum(a8));

    int[] a9 = { 3, 2, 7, 10 }; // 3 + 10 = 13
    System.out.println(maxSum(a9));
  }
}
