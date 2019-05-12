package com.svetanis.algorithms.dp.alluniquepaths;

public final class CountAllUniquePathsBottomUp {

  public static int count(int m, int n) {
    // time complexity: O(n*m)

    int[][] dp = new int[m][n];

    // count of paths to reach
    // any cell in first col is 1
    for (int i = 0; i < m; i++) {
      dp[i][0] = 1;
    }

    // count of paths to reach
    // any cell in first row is 1
    for (int j = 0; j < n; j++) {
      dp[0][j] = 1;
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        // + dp[i - 1][j - 1]; //if diagonal moves allowed
      }
    }
    return dp[m - 1][n - 1];
  }

  public static void main(String[] args) {
    System.out.println(count(3, 7));
  }
}

