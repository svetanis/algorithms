package com.svetanis.algorithms.dp.palindrome;

// Longest Palindromic SubString
//Given a string, find the length of its Longest Palindromic Substring (LPS). 
//In a palindromic string, elements read the same backward and forward.

public final class LongestPalindromeSubStrBottomUp {

  public static String lps(String str) {
    // Time Complexity: O(n^2)
    // Auxiliary Space O(n^2)

    int n = str.length();
    int max = 1;
    int start = 0;

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
        start = i;
        max = 2;
      }
    }

    for (int left = n - 1; left >= 0; left--) {
      for (int right = left + 1; right < n; right++) {
        if (dp[left + 1][right - 1] && str.charAt(left) == str.charAt(right)) {
          dp[left][right] = true;
          int len = right - left + 1;
          if(len > max) {
            max = len;
            start = left;
          }
        }
      }
    }

    return str.substring(start, start + max);
  }

  public static void main(String[] args) {
    String str = "forgeeksskeegfor";
    System.out.println(lps(str)); // geeksskeeg
  }
}