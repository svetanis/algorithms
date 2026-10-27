package com.svetanis.algorithms.prefixsum.range;

// 304. Range Sum Query 2D - Immutable
//
// Input: a grid (matrix on LeetCode) of m rows and n columns, given once to the constructor, then
// queries sumRange(r1, c1, r2, c2), each returning the total of the rectangle with top-left corner
// [r1][c1] and bottom-right corner [r2][c2], both included. On LeetCode: 1 <= m, n <= 200,
// -10^4 <= matrix[i][j] <= 10^4, 0 <= r1 <= r2 < m, 0 <= c1 <= c2 < n, at most 10^4 queries; the
// largest total, 4 * 10^8, fits in an int.
//
// The one idea: prefix[i][j] holds the total of the rectangle made of the top i rows and the left
// j columns; row 0 and column 0 are the empty rectangle, 0. It is built from three earlier
// entries: the one above plus the one to the left counts their shared corner rectangle twice, so
// subtract that once, then add the cell itself. A query's rectangle is then four lookups: the
// rectangle down to its bottom-right corner, minus the strip above it, minus the strip to its
// left, plus the top-left rectangle that both strips removed.
//
// Time: O(m * n) once, in the constructor; O(1) per query -- four lookups.
// Space: O(m * n) -- the prefix table.
//
// Siblings: none for this problem. MatrixBlockSum (1314) builds the same table and asks it for a
// square around every cell. LeetCode 308, where cells change between queries, needs a 2D Fenwick
// tree instead.

public final class RangeSumQuery2DSubmit {

	public RangeSumQuery2DSubmit(int[][] grid) {
		this.prefix = prefix(grid); // BUILD once
	}

	private int[][] prefix;

	public int sumRange(int r1, int c1, int r2, int c2) {
		int sum1 = prefix[r2 + 1][c2 + 1]; // WHOLE: everything up to the bottom-right corner
		int sum2 = prefix[r2 + 1][c1]; // STRIP to the left of the rectangle
		int sum3 = prefix[r1][c2 + 1]; // STRIP above the rectangle
		return sum1 - sum2 - sum3 + prefix[r1][c1]; // CORNER: removed by both strips, added back once
	}

	private static int[][] prefix(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] prefix = new int[n + 1][m + 1]; // row 0 and column 0 stay 0: the empty rectangle
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				int top = prefix[i][j - 1]; // the entry to the LEFT, despite its name
				int left = prefix[i - 1][j]; // the entry ABOVE, despite its name
				int val = grid[i - 1][j - 1];
				int overlap = prefix[i - 1][j - 1]; // SHARED corner, counted in both
				prefix[i][j] = top + left - overlap + val; // TOTAL of the top i rows, left j columns
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
