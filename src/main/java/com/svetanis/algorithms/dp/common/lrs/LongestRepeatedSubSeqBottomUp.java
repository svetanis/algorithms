package com.svetanis.algorithms.dp.common.lrs;

import static java.lang.Math.max;

// Given a sequence, find the length of its longest repeating subsequence (LRS). 
// A repeating subsequence will be the one that appears at least twice 
// in the original sequence and is not overlapping 
// (i.e. none of the corresponding characters 
// in the repeating subsequences have the same index).

public final class LongestRepeatedSubSeqBottomUp {

  public static String lcs(String s) {
    int n = s.length();
    int[][] dp = lcsLen(s);
    int i = n;
    int j = n;
    StringBuilder sb = new StringBuilder();
    while (i > 0 && j > 0) {
      if (dp[i][j] == dp[i - 1][j - 1] + 1) {
        sb.append(s.charAt(i - 1));
        i--;
        j--;
      } else if (dp[i][j] == dp[i - 1][j]) {
        i--;
      } else {
        j--;
      }
    }
    return sb.reverse().toString();
  }

  private static int[][] lcsLen(String s) {
    // Time Complexity: O(n^2)
    int n = s.length();
    int[][] dp = new int[n + 1][n + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (i != j && s.charAt(i - 1) == s.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          int top = dp[i - 1][j];
          int left = dp[i][j - 1];
          dp[i][j] = max(left, top);
        }
      }
    }
    return dp;
  }

  public static void main(String[] args) {
    System.out.println(lcs("tomorrow"));
    System.out.println(lcs("aabdbcec"));
    System.out.println(lcs("fmff"));
  }
}
