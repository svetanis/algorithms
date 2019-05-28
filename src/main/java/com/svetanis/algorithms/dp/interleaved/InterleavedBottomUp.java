package com.svetanis.algorithms.dp.interleaved;

// Given two strings str1 and str2, write a function that prints all interleavings of the given two strings.
// https://leetcode.com/problems/interleaving-string/

public final class InterleavedBottomUp {

  public static boolean isInterleaved(String s1, String s2, String s) {
    int n = s1.length();
    int m = s2.length();
    int k = s.length();
    if (k != n + m) {
      return false;
    }

    boolean[][] dp = new boolean[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        if (i == 0 && j == 0) {
          dp[i][j] = true;
        } else if (i == 0) {
          dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s.charAt(i + j - 1);
        } else if (j == 0) {
          dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s.charAt(i + j - 1);
        } else {
          boolean one = dp[i - 1][j] && s1.charAt(i - 1) == s.charAt(i + j - 1);
          boolean two = dp[i][j - 1] && s2.charAt(j - 1) == s.charAt(i + j - 1);
          dp[i][j] = one || two;
        }
      }
    }
    return dp[n][m];
  }

  public static void main(String[] args) {
    System.out.println(isInterleaved("XXY", "XXZ", "XXZXXXY")); // false
    System.out.println(isInterleaved("XY", "WZ", "WZXY")); // true
    System.out.println(isInterleaved("XY", "X", "XXY")); // true
    System.out.println(isInterleaved("YX", "X", "XXY")); // false
    System.out.println(isInterleaved("XXY", "XXZ", "XXXXZY")); // true
  }
}
