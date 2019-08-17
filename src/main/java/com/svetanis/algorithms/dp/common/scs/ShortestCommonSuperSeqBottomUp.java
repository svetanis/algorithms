package com.svetanis.algorithms.dp.common.scs;

import static java.lang.Math.min;

public final class ShortestCommonSuperSeqBottomUp {

  public static String scs(String x, String y) {
    int[][] dp = length(x, y);
    return scs(x, y, dp);
  }

  private static String scs(String x, String y, int[][] dp) {
    int n = x.length();
    int m = y.length();

    StringBuilder sb = new StringBuilder();
    int i = n;
    int j = m;

    while (i > 0 && j > 0) {
      if (x.charAt(i - 1) == y.charAt(j - 1)) {
        sb.append(x.charAt(i - 1));
        i--;
        j--;
      } else if (dp[i - 1][j] > dp[i][j - 1]) {
        sb.append(y.charAt(j - 1));
        j--;
      } else {
        sb.append(x.charAt(i - 1));
        i--;
      }
    }

    while (i > 0) {
      sb.append(x.charAt(i - 1));
      i--;
    }

    while (j > 0) {
      sb.append(y.charAt(j - 1));
      j--;
    }

    sb.reverse();
    return sb.toString();

  }

  private static int[][] length(String s1, String s2) {
    // Time Complexity: O(n*m)

    int n = s1.length();
    int m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        if (i == 0) {
          dp[i][j] = j;
        } else if (j == 0) {
          dp[i][j] = i;
        } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp;
  }

  public static void main(String[] args) {
    String x = "ABCBDAB";
    String y = "BDCABA";
    System.out.println(scs(x, y)); // ABDCABDAB
  }

  private static String scs(String x, String y, int n, int m, int[][] dp) {
    if (n == 0) {
      return y.substring(0, m);
    }

    if (m == 0) {
      return x.substring(0, n);
    }

    if (x.charAt(n - 1) == y.charAt(m - 1)) {
      return x.charAt(n - 1) + scs(x, y, n - 1, m - 1, dp);
    } else {
      if (dp[n][m - 1] > dp[n - 1][m]) {
        return x.charAt(n - 1) + scs(x, y, n - 1, m, dp);
      } else {
        return y.charAt(m - 1) + scs(x, y, n, m - 1, dp);
      }
    }
  }

}
