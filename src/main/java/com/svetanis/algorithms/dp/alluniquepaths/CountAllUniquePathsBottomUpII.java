package com.svetanis.algorithms.dp.alluniquepaths;

public final class CountAllUniquePathsBottomUpII {

  public static int count(int n, int m) {

    int[][] dp = new int[n][m];
    dp[0][0] = 1; // 1 way to start from (0, 0)

    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        int right = i < 1 ? 0 : dp[i - 1][j];
        int down = j < 1 ? 0 : dp[i][j - 1];
        dp[i][j] += right + down;
      }
    }
    return dp[n - 1][m - 1];
  }

  public static void main(String[] args) {
    System.out.println(count(3, 7));
  }
}
