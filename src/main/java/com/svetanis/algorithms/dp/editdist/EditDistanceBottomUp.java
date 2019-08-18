package com.svetanis.algorithms.dp.editdist;

import static java.lang.Math.min;

public final class EditDistanceBottomUp {

  public static int editDist(String x, String y) {
    // Time complexity: O(n * m)

    int n = x.length();
    int m = y.length();

    int[][] dp = new int[n + 1][m + 1];
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= m; ++j) {
        if (i == 0) {
          dp[i][j] = j;
        } else if (j == 0) {
          dp[i][j] = i;
        } else {
          int delete = 1 + dp[i - 1][j];
          int insert = 1 + dp[i][j - 1];
          int cost = x.charAt(i - 1) == y.charAt(j - 1) ? 0 : 1;
          int replace = cost + dp[i - 1][j - 1];
          dp[i][j] = min(min(delete, insert), replace);
        }
      }
    }
    return dp[n][m];
  }

  public static void main(String[] args) {
    System.out.println(editDist("Zeil", "trials"));
    System.out.println(editDist("cat", "act"));
    System.out.println(editDist("COMBO", "COIN"));
    System.out.println(editDist("Anshuman", "Antihuman"));
  }
}
