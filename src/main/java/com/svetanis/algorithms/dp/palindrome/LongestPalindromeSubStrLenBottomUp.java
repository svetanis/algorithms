package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;

// Longest Palindromic SubString
//Given a string, find the length of its Longest Palindromic Substring (LPS). 
//In a palindromic string, elements read the same backward and forward.

public final class LongestPalindromeSubStrLenBottomUp {

  public static int lps(String str) {
    // Time Complexity: O(n^2)
    // Auxiliary Space O(n^2)

    int n = str.length();

    // table to store results of subproblems
    boolean[][] dp = new boolean[n][n];

    // strings of length 1 are
    // palindrome of length 1
    for (int i = 0; i < n; ++i) {
      dp[i][i] = true;
    }

    // check for substring of length 2
    for (int i = 0; i < n - 1; ++i) {
      if (str.charAt(i) == str.charAt(i + 1)) {
        dp[i][i + 1] = true;
      }
    }

    int max = 1;
    for (int start = n - 1; start >= 0; start--) {
      for (int end = start + 1; end < n; end++) {
        if (dp[start + 1][end - 1] && str.charAt(start) == str.charAt(end)) {
          dp[start][end] = true;
          max = max(max, end - start + 1);
        }
      }
    }
    return max;
  }

  public static void main(String[] args) {
    System.out.println(lps("abdbca")); // 3
    System.out.println(lps("cddpd")); // 3
    System.out.println(lps("pqr")); // 1
  }
}