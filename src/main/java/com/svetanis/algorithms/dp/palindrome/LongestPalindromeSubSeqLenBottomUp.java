package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;

//Given a sequence, find the length of its Longest Palindromic Subsequence (LPS). 
//In a palindromic subsequence, elements read the same backward and forward.

public final class LongestPalindromeSubSeqLenBottomUp {

  public static int lps(String str) {
    // Time Complexity: O(n^2)

    int n = str.length();

    // table to store length of subproblems
    int[][] dp = new int[n][n];

    // strings of length 1 are
    // palindrome of length 1
    for (int i = 0; i < n; ++i) {
      dp[i][i] = 1;
    }

    // build the table.
    // the lower diagonal values
    // of table are useless and
    // not filled in the process.
    // the values are filled in
    // a manner similar to
    // MatrixChainMultiplicationDP
    for (int start = n - 1; start >= 0; start--) {
      for (int end = start + 1; end < n; end++) {
        if (str.charAt(start) == str.charAt(end)) {
          dp[start][end] = 2 + dp[start + 1][end - 1];
        } else {
          dp[start][end] = max(dp[start + 1][end], dp[start][end - 1]);
        }
      }
    }
    return dp[0][n - 1];
  }

  public static void main(String[] args) {
    String str = "GEEKS FOR GEEKS";
    System.out.println(lps(str));
  }
}