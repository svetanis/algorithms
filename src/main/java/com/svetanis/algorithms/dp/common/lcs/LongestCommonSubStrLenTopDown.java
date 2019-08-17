package com.svetanis.algorithms.dp.common.lcs;

import static java.lang.Math.max;

// Given two strings ‘s1’ and ‘s2’, 
// find the length of the longest substr 
// which is common in both the strings.

public final class LongestCommonSubStrLenTopDown {

  public static int lcs(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();
    int max = max(n, m);
    int[][][] dp = new int[n][m][max];
    return lcs(s1, s2, dp, 0, 0, 0);
  }

  private static int lcs(String s1, String s2, int[][][] dp, int i, int j, int max) {
    int n = s1.length();
    int m = s2.length();

    if (i == n || j == m) {
      return max;
    }

    if (dp[i][j][max] == 0) {
      int count = max;
      if (s1.charAt(i) == s2.charAt(j)) {
        count = lcs(s1, s2, dp, i + 1, j + 1, max + 1);
      }

      int first = lcs(s1, s2, dp, i, j + 1, 0);
      int second = lcs(s1, s2, dp, i + 1, j, 0);
      dp[i][j][max] = max(count, max(first, second));
    }
    return dp[i][j][max];
  }

  public static void main(String[] args) {
    System.out.println(lcs("abdca", "cbda"));
    System.out.println(lcs("passport", "ppsspt"));
  }
}