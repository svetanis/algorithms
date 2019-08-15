package com.svetanis.algorithms.dp.common;

import static java.lang.Math.max;

// LONGEST COMMON SUBSEQUENCE LENGTH

public final class LongestCommonSubSeqLenBottomUp {

  public static int lcs(String s1, String s2) {
    // Time Complexity: O(n*m)
    // Space Complexity: (O(n*m)

    int n = s1.length();
    int m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= m; ++j) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[n][m];
  }

  public static void main(String[] args) {
    System.out.println(lcs("abdca", "cbda"));
    System.out.println(lcs("passport", "ppsspt"));
  }
}