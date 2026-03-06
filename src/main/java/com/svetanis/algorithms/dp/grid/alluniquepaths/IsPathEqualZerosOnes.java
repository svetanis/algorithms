package com.svetanis.algorithms.dp.grid.alluniquepaths;

// 2510. Check if There is a Path With Equal Number of 0's And 1's

public final class IsPathEqualZerosOnes {
	// Time Complexity: O(n * m * (n + m))
	// Space Complexity: O(n * m * (n + m))

	private int rows;
	private int cols;
	private int target;
	private int[][] grid;
	private Boolean[][][] dp;

	public boolean isPath(int[][] grid) {
		this.rows = grid.length;
		this.cols = grid[0].length;
		int total = rows + cols - 1;
		if (total % 2 == 1) {
			return false;
		}
		this.target = total / 2;
		this.grid = grid;
		this.dp = new Boolean[rows][cols][total];
		return dfs(0, 0, 0);
	}

	private boolean dfs(int row, int col, int onesCount) {
		if (row >= rows || col >= cols) {
			return false;
		}
		onesCount += grid[row][col];
		if (dp[row][col][onesCount] != null) {
			return dp[row][col][onesCount];
		}
		// pruning 1: if current ones count exceeds target
		// pruning 2: if remaining cells can't provide enough zeros
		int cellsVisited = row + col + 1;
		int zerosCount = cellsVisited - onesCount;
		if (onesCount > target || zerosCount > target) {
			return false;
		}
		if (row == rows - 1 && col == cols - 1) {
			return onesCount == target;
		}
		boolean right = dfs(row, col + 1, onesCount);
		boolean down = dfs(row + 1, col, onesCount);
		return dp[row][col][onesCount] = right || down;
	}

	public static void main(String[] args) {
		IsPathEqualZerosOnes ipe = new IsPathEqualZerosOnes();
		// m rows and n columns
		int[][] g1 = { { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 1, 0, 1, 0 } };
		int[][] g2 = { { 1, 1, 0 }, { 0, 0, 1 }, { 1, 0, 0 } };
		System.out.println(ipe.isPath(g1)); // true
		System.out.println(ipe.isPath(g2)); // false
	}
}
