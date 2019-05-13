package com.svetanis.algorithms.dp.sum.max.subseq;

import static com.svetanis.java.base.utils.Arrays.max;
import static java.lang.Math.max;
import static java.lang.System.arraycopy;

public final class MaxSumIncreasingSubSeqLen {

// L[0] = {a[0]}
// L[i] = {MaxSum(L[j])} + a[i], j < i, a[j] < a[i]
//      = a[i], if there is no j such that a[j] < a[i] 
  
  public static int maxSum(int[] a) {
    // Time Complexity: O(n^2)

    int n = a.length;
    int[] sum = new int[n];
    arraycopy(a, 0, sum, 0, n);

    // compute max sum values
    // in bottom up manner
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (a[j] < a[i]) {
          sum[i] = max(sum[i], sum[j] + a[i]);
        }
      }
    }
    return max(sum);
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 101, 2, 3, 100, 4, 5 };
    System.out.println(maxSum(a1)); // 106

    int[] a2 = { 3, 4, 5, 10 };
    System.out.println(maxSum(a2)); // 22

    int[] a3 = { 10, 5, 4, 3 };
    System.out.println(maxSum(a3)); // 10
  }
}
