package com.svetanis.algorithms.dp.grid.alluniquepaths;

// 62. Unique Paths

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

	// more compact
	public static int uniquePathsSimple(int n, int m) {
		int[][] dp = new int[n + 1][m + 1];
		dp[1][1] = 1;
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= m; c++) {
				if (r == 1 && c == 1) {
					continue;
				}
				dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
			}
		}
		return dp[n][m];
	}

	// more compact
	public static int count(int n, int m) {
		int[][] dp = new int[n][m];
		dp[0][0] = 1; // 1 way to start from (0, 0)
		for (int r = 0; r < n; ++r) {
			for (int c = 0; c < m; ++c) {
				int right = r < 1 ? 0 : dp[r - 1][c];
				int down = c < 1 ? 0 : dp[r][c - 1];
				dp[r][c] += right + down;
			}
		}
		return dp[n - 1][m - 1];
	}

	public static int countUniquePaths(int n, int m) {
		int[][] dp = init(n, m);
		for (int r = 1; r < n; r++) {
			for (int c = 1; c < m; c++) {
				dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
				// + dp[r - 1][c - 1]; //if diagonal moves allowed
			}
		}
		return dp[n - 1][m - 1];
	}

	private static int[][] init(int n, int m) {
		int[][] dp = new int[n][m];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				dp[0][c] = 1;
				dp[r][0] = 1;
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		// n rows and m columns
		System.out.println(uniquePathsSimple(3, 2)); // 3
		System.out.println(uniquePathsSimple(7, 3)); // 28
		System.out.println(uniquePathsSimple(1, 1)); // 1
		System.out.println(uniquePathsSimple(10, 5)); // 715

		System.out.println(count(3, 2)); // 3
		System.out.println(count(7, 3)); // 28
		System.out.println(count(1, 1)); // 1
		System.out.println(count(10, 5)); // 715

		System.out.println(countUniquePaths(3, 2)); // 3
		System.out.println(countUniquePaths(7, 3)); // 28
		System.out.println(countUniquePaths(1, 1)); // 1
		System.out.println(countUniquePaths(10, 5)); // 715
	}
}
