package com.svetanis.algorithms.dp.grid;

// a robot starts its journey at
// the top-left corner of a grid
// that measures m x n
// m rows by n columns
// at each step, the robot has only
// two possible directions: it can
// either move to the right or 
// move downward. its destination is 
// the bottom-right corner of the grid
// find the total number of unique paths
// the robot can take to reach its dst

// let dp[r][c] be the number of unique paths
// to reach cell (r, c)
// dp[r][c] = dp[r - 1][c] + dp[r][c - 1]
// where (r - 1, c) is the cell on the top
// and (r, c - 1) is the cell at the left

public final class RobotOnGridBottomUp {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int uniquePathsSimple(int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		dp[1][1] = 1;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == 1 && j == 1) {
					continue;
				}
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		return dp[m][n];
	}

	public static int countUniquePaths(int m, int n) {
		int[][] dp = init(m, n);
		for (int r = 1; r < m; r++) {
			for (int c = 1; c < n; c++) {
				dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
			}
		}
		return dp[m - 1][n - 1];
	}

	private static int[][] init(int m, int n) {
		int[][] dp = new int[m][n];
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				dp[0][c] = 1;
				dp[r][0] = 1;
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		// m rows and n columns
		System.out.println(uniquePathsSimple(3, 2)); // 3
		System.out.println(uniquePathsSimple(7, 3)); // 28
		System.out.println(uniquePathsSimple(1, 1)); // 1
		System.out.println(uniquePathsSimple(10, 5)); // 715
	}
}
