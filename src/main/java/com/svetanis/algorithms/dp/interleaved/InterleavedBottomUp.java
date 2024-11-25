package com.svetanis.algorithms.dp.interleaved;

// 97. Interleaving String

// Given two strings str1 and str2, 
// write a function that prints all 
// interleavings of the given two strings.

public final class InterleavedBottomUp {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

  public static boolean isInterleaved(String x, String y, String s) {
    int n = x.length();
    int m = y.length();
    int k = s.length();
    if (k != n + m) {
      return false;
    }

    boolean[][] dp = new boolean[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        int len = i + j - 1;
        if (i == 0 && j == 0) {
          dp[i][j] = true;
        } else if (i == 0) {
          dp[i][j] = dp[i][j - 1] && y.charAt(j - 1) == s.charAt(len);
        } else if (j == 0) {
          dp[i][j] = dp[i - 1][j] && x.charAt(i - 1) == s.charAt(len);
        } else {
          boolean one = dp[i - 1][j] && x.charAt(i - 1) == s.charAt(len);
          boolean two = dp[i][j - 1] && y.charAt(j - 1) == s.charAt(len);
          dp[i][j] = one || two;
        }
      }
    }
    return dp[n][m];
  }

  public static void main(String[] args) {
    System.out.println(isInterleaved("XXY", "XXZ", "XXZXXXY")); // false
    System.out.println(isInterleaved("XY", "WZ", "WZXY")); // true
    System.out.println(isInterleaved("XY", "X", "XXY")); // true
    System.out.println(isInterleaved("YX", "X", "XXY")); // false
    System.out.println(isInterleaved("XXY", "XXZ", "XXXXZY")); // true
  }
}
