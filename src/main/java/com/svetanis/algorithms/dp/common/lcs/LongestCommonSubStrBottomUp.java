package com.svetanis.algorithms.dp.common.lcs;

import static org.apache.commons.lang3.StringUtils.substring;

// Given two strings ‘s1’ and ‘s2’, 
// find the length of the longest substr 
// which is common in both the strings.

// return length and print common substring 
// create a table to store lengths of longest common suffixes
// of substrings. L[i][j] contains length of longest common
// suffix of X[0 ... i-1] and Y[0 ... j-1]. The first row and 
// first column entries have no logical meaning, they are used
// only simplicity of program

public final class LongestCommonSubStrBottomUp {

  public static String lcs(String s1, String s2) {
    // Time Complexity: O(n*m)
    // Space Complexity: O(n*m)

    int n = s1.length();
    int m = s2.length();
    int max = 0;
    int end = 0;
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= m; ++j) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
          if (dp[i][j] > max) {
            max = dp[i][j];
            end = i;
          }
        }
      }
    }
    int start = end - max;
    return substring(s1, start, end);
  }

  public static void main(String[] args) {
    System.out.println(lcs("abdca", "cbda"));
    System.out.println(lcs("passport", "ppsspt"));
  }
}
