package com.svetanis.algorithms.dp.interleaved;

import static java.util.Arrays.fill;

// Given two strings str1 and str2, write a function that prints all interleavings of the given two strings.

public final class InterleavedMemoization {

  public static boolean isInterleaved(String s1, String s2, String s) {
    int n = s1.length();
    int m = s2.length();
    int[][] dp = init(n, m);
    return isInterleaved(s1, 0, s2, 0, s, 0, dp);
  }

  private static boolean isInterleaved(String s1, int i, String s2, int j, String s, int k, int[][] dp) {
    int n = s1.length();
    int m = s2.length();

    if (i == n) {
      return s2.substring(j).equals(s.substring(k));
    }

    if (j == m) {
      return s1.substring(i).equals(s.substring(k));
    }

    if (dp[i][j] >= 0) {
      return dp[i][j] == 1 ? true : false;
    }

    boolean interleaved = false;

    boolean one = s1.charAt(i) == s.charAt(k) && isInterleaved(s1, i + 1, s2, j, s, k + 1, dp);
    boolean two = s2.charAt(j) == s.charAt(k) && isInterleaved(s1, i, s2, j + 1, s, k + 1, dp);
    if (one || two) {
      interleaved = true;
    }
    dp[i][j] = interleaved ? 1 : 0;
    return interleaved;
  }

  private static int[][] init(int n, int m) {
    int[][] dp = new int[n][m];
    for (int i = 0; i < n; i++) {
      fill(dp[i], -1);
    }

    return dp;
  }

  public static void main(String[] args) {
    System.out.println(isInterleaved("XXY", "XXZ", "XXZXXXY")); // false
    System.out.println(isInterleaved("XY", "WZ", "WZXY")); // true
    System.out.println(isInterleaved("XY", "X", "XXY")); // true
    System.out.println(isInterleaved("YX", "X", "XXY")); // false
    System.out.println(isInterleaved("XXY", "XXZ", "XXXXZY")); // true
  }
}
