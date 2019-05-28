package com.svetanis.algorithms.dp.common;

import static java.lang.Math.max;

// create a table to store lengths of longest common suffixes
// of substrings. L[i][j] contains length of longest common
// suffix of X[0 ... i-1] and Y[0 ... j-1]. The first row and 
// first column entries have no logical meaning, they are used
// only simplicity of program

public final class LongestCommonSubStrLen {

  public static int lcs(String s1, String s2) {
    // Time Complexity: O(n*m)
    // Space Complexity: O(n*m)

    int max = 0;
    int n = s1.length();
    int m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    // following steps build L[n][m]
    // in bottom up fashion
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= m; ++j) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
          max = max(max, dp[i][j]);
        } else {
          dp[i][j] = 0;
        }
      }
    }
    return max;
  }

  public static void main(String[] args) {
    String s1 = "OldSite:GeeksforGeeks.org";
    String s2 = "NewSite:GeeksQuiz.com";
    System.out.println(lcs(s1, s2));

    String s3 = "GeeksforGeeks";
    String s4 = "GeeksQuiz";
    System.out.println(lcs(s3, s4));
  }
}