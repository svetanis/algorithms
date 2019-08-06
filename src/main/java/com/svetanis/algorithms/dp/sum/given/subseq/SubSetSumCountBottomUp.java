package com.svetanis.algorithms.dp.sum.given.subseq;

//Given a set of positive numbers, find the total number  
//of subsets whose sum is equal to a given number ‘S’.

public final class SubSetSumCountBottomUp {

  public static int count(int[] a, int sum) {
    // Time complexity: O(sum * n)

    int n = a.length;

    // the value of subset[i][j] will be true
    // if there is a subset of set[0 ... j - 1]
    // with sum equal to i
    int[][] dp = new int[sum + 1][n + 1];

    // if sum is 0, then answer is true
    for (int i = 0; i <= n; ++i) {
      dp[0][i] = 1;
    }

    // if sum is not 0 and set is empty,
    // then answer is false
    for (int s = 1; s <= sum; s++) {
      dp[s][0] = 0;
    }

    // fill the subset table in bottom up manner
    for (int s = 1; s <= sum; ++s) {
      for (int j = 1; j <= n; ++j) {
        dp[s][j] = dp[s][j - 1];

        if (s >= a[j - 1]) {
          int incl = dp[s - a[j - 1]][j - 1];
          int excl = dp[s][j - 1];
          dp[s][j] = incl + excl;
        }
      }
    }
    return dp[sum][n];
  }

  public static void main(String[] args) {
    int[] a1 = { 3, 34, 4, 12, 5, 2 };
    System.out.println(count(a1, 9));

    int[] a2 = { 1, 1, 2, 3 };
    System.out.println(count(a2, 4));

    int[] a3 = { 1, 2, 7, 1, 5 };
    System.out.println(count(a3, 9));
  }
}
