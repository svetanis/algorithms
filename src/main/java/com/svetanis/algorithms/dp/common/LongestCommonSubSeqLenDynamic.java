package com.svetanis.algorithms.dp.common;

import static java.lang.Math.max;

// LONGEST COMMON SUBSEQUENCE LENGTH

public final class LongestCommonSubSeqLenDynamic {

  public static int lcs(String s1, String s2) {
    // Time Complexity: O(n*m)
    // Space Complexity: (O(n*m)

    int n = s1.length();
    int m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    // build len[n][m] in bottom up fashion
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= m; ++j) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    // len[n][m] contains length of LCS
    // for X[0 ... n-1] and Y[0 ... m-1]
    return dp[n][m];
  }

  public static void main(String[] args) {
    String s1 = "AGGTAB";
    String s2 = "GXTXAYB";
    System.out.println(lcs(s1, s2));
  }
}