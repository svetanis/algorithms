package com.svetanis.algorithms.dp.sum.given.subseq;

import static com.svetanis.java.base.utils.Arrays.sum;
import static java.lang.Math.abs;

//Given a set of positive numbers, partition the set into two
//subsets with a minimum difference between their subset sums.

public final class SubSetSumMinDiffBottomUp {

  // Time Complexity: O(n * sum)
  // Space Complexity: O(n * sum)

  public static int minDiff(int[] a) {
    int n = a.length;
    int sum = sum(a);
    boolean[][] dp = init(a, sum);

    for (int i = 1; i < n; i++) {
      for (int s = 1; s <= sum / 2; s++) {
        if (dp[i - 1][s]) {
          dp[i][s] = dp[i - 1][s]; // exclude
        } else if (s >= a[i]) {
          dp[i][s] = dp[i - 1][s - a[i]]; // include
        }
      }
    }

    int sum1 = getSum(dp, sum);
    int sum2 = sum - sum1;
    return abs(sum2 - sum1);
  }

  private static boolean[][] init(int[] a, int sum) {
    int n = a.length;
    boolean[][] dp = new boolean[n][sum / 2 + 1];

    for (int i = 0; i < n; i++) {
      dp[i][0] = true;
    }

    for (int s = 1; s <= sum / 2; s++) {
      dp[0][s] = a[0] == s ? true : false;
    }
    return dp;
  }

  private static int getSum(boolean[][] dp, int sum) {
    int n = dp.length;
    int sum1 = 0;
    for (int i = sum / 2; i >= 0; i--) {
      if (dp[n - 1][i]) {
        sum1 = i;
        break;
      }
    }
    return sum1;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 3, 9 };
    System.out.println(minDiff(a1)); // 3

    int[] a2 = { 1, 2, 7, 1, 5 };
    System.out.println(minDiff(a2)); // 0

    int[] a3 = { 1, 3, 100, 4 };
    System.out.println(minDiff(a3)); // 92

  }
}
