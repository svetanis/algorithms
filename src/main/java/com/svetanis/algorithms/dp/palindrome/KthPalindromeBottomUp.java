package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.min;
import static org.apache.commons.lang3.StringUtils.reverse;

public final class KthPalindromeBottomUp {

  public static boolean isKthPalindrome(String str, int k) {
    // Time Complexity: O(2^n)
    int n = str.length();
    String reversed = reverse(str);
    return isKthPalindrome(str, reversed, n, n) <= 2 * k;
  }

  private static int isKthPalindrome(String s1, String s2, int n, int m) {
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = i + j;
        } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[n][m];
  }

  public static void main(String[] args) {
    System.out.println(isKthPalindrome("acdcb", 2));
  }
}