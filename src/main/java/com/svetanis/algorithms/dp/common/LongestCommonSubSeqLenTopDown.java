package com.svetanis.algorithms.dp.common;

import static java.lang.Math.max;

public final class LongestCommonSubSeqLenTopDown {

  public static int lcs(String s1, String s2) {
    // Time Complexity: O(n*m)

    int n = s1.length();
    int m = s2.length();
    int[][] dp = new int[n][m];
    return lcs(s1, s2, dp, 0, 0);
  }

  private static int lcs(String s1, String s2, int[][] dp, int i, int j) {
    int n = s1.length();
    int m = s2.length();

    if (i == n || j == m) {
      return 0;
    }

    if (dp[i][j] != 0) {
      return dp[i][j];
    }

    if (s1.charAt(i) == s2.charAt(j)) {
      dp[i][j] = 1 + lcs(s1, s2, dp, i + 1, j + 1);
    } else {
      int top = lcs(s1, s2, dp, i + 1, j);
      int left = lcs(s1, s2, dp, i, j + 1);
      dp[i][j] = max(top, left);
    }
    return dp[i][j];
  }

  public static void main(String[] args) {
    System.out.println(lcs("abdca", "cbda"));
    System.out.println(lcs("passport", "ppsspt"));
  }
}
