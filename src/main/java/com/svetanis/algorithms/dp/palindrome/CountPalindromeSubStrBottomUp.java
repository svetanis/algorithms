package com.svetanis.algorithms.dp.palindrome;

// Given a string, find the total number of palindromic substrings in it. 

public final class CountPalindromeSubStrBottomUp {

  public static int lps(String str) {
    // Time Complexity: O(n^2)
    // Auxiliary Space O(n^2)

    int n = str.length();
    int count = 0;

    // table to store results of subproblems
    boolean[][] dp = new boolean[n][n];

    // strings of length 1 are
    // palindrome of length 1
    for (int i = 0; i < n; ++i) {
      dp[i][i] = true;
      count++;
    }

    // check for substring of length 2
    for (int i = 0; i < n - 1; ++i) {
      if (str.charAt(i) == str.charAt(i + 1)) {
        dp[i][i + 1] = true;
        count++;
      }
    }

    for (int start = n - 1; start >= 0; start--) {
      for (int end = start + 1; end < n; end++) {
        if (dp[start + 1][end - 1] && str.charAt(start) == str.charAt(end)) {
          dp[start][end] = true;
          count++;
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    System.out.println(lps("abdbca")); // 3
    System.out.println(lps("cddpd")); // 3
    System.out.println(lps("pqr")); // 1
    System.out.println(lps("abc")); // 3
    System.out.println(lps("aaa")); // 6    
  }
}