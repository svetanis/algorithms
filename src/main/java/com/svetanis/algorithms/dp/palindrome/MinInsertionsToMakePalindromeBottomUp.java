package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.min;

public final class MinInsertionsToMakePalindromeBottomUp {

  public static int palindrome(String str) {
    // Time Complexity: O(n^2)
    // Space Complexity: O(n^2)

    int n = str.length();
    int[][] dp = new int[n][n];

    for (int gap = 1; gap < n; ++gap) {
      for (int low = 0, high = gap; high < n; ++low, ++high) {
        if (str.charAt(low) == str.charAt(high)) {
          dp[low][high] = dp[low + 1][high - 1];
        } else {
          dp[low][high] = 1 + min(dp[low][high - 1], dp[low + 1][high]);
        }
      }
    }

    // return min number of
    // insertions for chars[0 ... n-1]
    return dp[0][n - 1];
  }

  public static void main(String[] args) {
    System.out.println(palindrome("geeks"));
  }
}