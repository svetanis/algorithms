package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;

//Given a sequence, find the length of its Longest Palindromic Subsequence (LPS). 
//In a palindromic subsequence, elements read the same backward and forward.

public final class LongestPalindromeSubSeqLenTopDown {

  public static int lps(String str) {
    int n = str.length();
    int[][] dp = new int[n][n];
    return lps(str, dp, 0, n - 1);
  }

  private static int lps(String str, int[][] dp, int low, int high) {
    // Time Complexity: O(n^2)

    if (low > high) {
      return 0;
    }

    // Base case 1:
    // if there is only 1 char
    if (low == high) {
      return 1;
    }

    // Base case 2:
    // if there are only 2 chars
    // and both are same
    if (str.charAt(low) == str.charAt(high) && low + 1 == high) {
      return 2;
    }

    if (dp[low][high] != 0) {
      return dp[low][high];
    }

    // if the first and last chars match
    if (str.charAt(low) == str.charAt(high)) {
      return lps(str, dp, low + 1, high - 1) + 2;
    }

    // if first and last
    // chars don't match
    int left = lps(str, dp, low + 1, high);
    int right = lps(str, dp, low, high - 1);
    dp[low][high] = max(left, right);
    return dp[low][high];
  }

  public static void main(String[] args) {
    System.out.println(lps("abdbca"));
    System.out.println(lps("cddpd"));
    System.out.println(lps("pqr"));
  }
}