package com.svetanis.algorithms.dp.alluniquepaths;

public final class CountAllUniquePathsWithObstacles {

  public static int count(int n, int m, int[][] matrix) {
    int[][] dp = new int[n][m];
    dp[0][0] = matrix[0][0] == 1 ? 0 : 1;

    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (matrix[i][j] == 0) {
          int right = i < 1 ? 0 : dp[i - 1][j];
          int down = j < 1 ? 0 : dp[i][j - 1];
          dp[i][j] += (right + down);
        }
      }
    }
    return dp[n - 1][m - 1];
  }

  public static void main(String[] args) {
    int n = 4, m = 4;
    int[][] matrix = { //
        { 0, 0, 0, 0 }, //
        { 1, 1, 0, 1 }, //
        { 0, 1, 0, 0 }, //
        { 1, 1, 1, 0 } };//
    System.out.println(count(n, m, matrix));
  }
}
