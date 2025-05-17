package com.svetanis.algorithms.prefixsum.range;

// 304. Range Sum Query 2D - Immutable

public final class RangeSumQuery2DSubmit {
	// Query Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public RangeSumQuery2DSubmit(int[][] grid) {
		this.prefix = prefix(grid);
	}

	private int[][] prefix;

	public int sumRange(int r1, int c1, int r2, int c2) {
		int sum1 = prefix[r2 + 1][c2 + 1];
		int sum2 = prefix[r2 + 1][c1];
		int sum3 = prefix[r1][c2 + 1];
		return sum1 - sum2 - sum3 + prefix[r1][c1];
	}

	private static int[][] prefix(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] prefix = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				int top = prefix[i][j - 1];
				int left = prefix[i - 1][j];
				int val = grid[i - 1][j - 1];
				int overlap = prefix[i - 1][j - 1];
				prefix[i][j] = top + left - overlap + val;
			}
		}
		return prefix;
	}

	public static void main(String[] args) {
		int[][] grid = { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 }, { 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 }, { 1, 0, 3, 0, 5 } };
		RangeSumQuery2DSubmit rsq = new RangeSumQuery2DSubmit(grid);
		System.out.println(rsq.sumRange(2, 1, 4, 3)); // 8
		System.out.println(rsq.sumRange(1, 1, 2, 2)); // 11
		System.out.println(rsq.sumRange(1, 2, 2, 4)); // 12
	}
}
