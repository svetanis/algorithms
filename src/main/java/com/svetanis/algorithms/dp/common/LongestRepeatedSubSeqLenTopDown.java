package com.svetanis.algorithms.dp.common;

import static java.lang.Math.max;

// Given a sequence, find the length of its longest repeating subsequence (LRS). 
// A repeating subsequence will be the one that appears at least twice 
// in the original sequence and is not overlapping 
// (i.e. none of the corresponding characters 
// in the repeating subsequences have the same index).

public final class LongestRepeatedSubSeqLenTopDown {

  public static int lcs(String str) {
    // Time Complexity: O(n^2)
    int n = str.length();
    int[][] dp = new int[n][n];
    return lcs(str, dp, 0, 0);
  }

  private static int lcs(String s, int[][] dp, int i, int j) {
    int n = s.length();

    if (i == n || j == n) {
      return 0;
    }

    if (dp[i][j] != 0) {
      return dp[i][j];
    }

    if (i != j && s.charAt(i) == s.charAt(j)) {
      dp[i][j] = 1 + lcs(s, dp, i + 1, j + 1);
    } else {
      int top = lcs(s, dp, i + 1, j);
      int left = lcs(s, dp, i, j + 1);
      dp[i][j] = max(left, top);
    }
    return dp[i][j];
  }

  public static void main(String[] args) {
    System.out.println(lcs("tomorrow"));
    System.out.println(lcs("aabdbcec"));
    System.out.println(lcs("fmff"));
  }
}
