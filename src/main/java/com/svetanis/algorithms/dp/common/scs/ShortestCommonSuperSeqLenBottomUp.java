package com.svetanis.algorithms.dp.common.scs;

import static java.lang.Math.min;

// Given two sequences ‘s1’ and ‘s2’, 
// write a method to find the len of 
// the shortest sequence which has 
// ‘s1’ and ‘s2’ as subsequences.

public final class ShortestCommonSuperSeqLenBottomUp {

  public static int scs(String s1, String s2) {
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
    return dp[n][m];
  }

  public static void main(String[] args) {
    String s1 = "geek";
    String s2 = "eke";
    System.out.println(scs(s1, s2));

    String s3 = "AGGTAB";
    String s4 = "GXTXAYB";
    System.out.println(scs(s3, s4));
  }
}