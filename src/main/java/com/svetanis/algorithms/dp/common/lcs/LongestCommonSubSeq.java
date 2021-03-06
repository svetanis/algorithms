package com.svetanis.algorithms.dp.common.lcs;

import static java.lang.Math.max;

public final class LongestCommonSubSeq {

  public static String lcs(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();
    int[][] dp = length(s1, s2);
    // start from the right-most-bottom-most
    // corner and one by one store chars in lcs[]
    return reconstruct(s1, s2, dp, n, m);
  }

  private static String reconstruct(String s1, String s2, int[][] dp, int n, int m) {
    if (n == 0 || m == 0) {
      return "";
    }
    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
      return reconstruct(s1, s2, dp, n - 1, m - 1) + s1.charAt(n - 1);
    }
    if (dp[n - 1][m] > dp[n][m - 1]) {
      return reconstruct(s1, s2, dp, n - 1, m);
    }
    return reconstruct(s1, s2, dp, n, m - 1);
  }

  public static int[][] length(String s1, String s2) {
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
    return dp;
  }

  public static void main(String[] args) {
    String s1 = "AGGTAB";
    String s2 = "GXTXAYB";
    System.out.println(lcs(s1, s2)); // GTAB
  }

  @Deprecated
  private static String reconstruct(String s1, String s2, int[][] dp) {
    int n = s1.length();
    int m = s2.length();

    int i = n;
    int j = m;
    int index = dp[n][m];

    char[] lcs = new char[index + 1];
    lcs[index] = '\0';

    while (i > 0 && j > 0) {
      // if current char in X[] and Y[] are same,
      // then current char is part of LCS
      if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
        lcs[index - 1] = s1.charAt(i - 1);
        i--;
        j--;
        index--;
      } else if (dp[i - 1][j] > dp[i][j - 1]) {
        // if not same, then find the larger of two
        // & go in the direction of larger value
        i--;
      } else {
        j--;
      }
    }
    return new String(lcs);
  }

}
