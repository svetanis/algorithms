package com.svetanis.algorithms.dp.grid;

// 361. Bomb Enemy

public final class BombEnemy {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	private int rows;
	private int cols;
	private int[][] counts;

	public int count(char[][] grid) {
		this.rows = grid.length;
		this.cols = grid[0].length;
		this.counts = new int[rows][cols];
		rowCounts(grid);
		colCounts(grid);
		int max = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == '0') {
					max = Math.max(max, counts[i][j]);
				}
			}
		}
		return max;
	}

	private void colCounts(char[][] grid) {
		for (int j = 0; j < cols; j++) {
			int count = 0;
			// from top to bottom
			for (int i = 0; i < rows; i++) {
				count = doCount(grid[i][j], count);
				this.counts[i][j] += count;
			}
			count = 0;
			// from bottom to top
			for (int i = rows - 1; i >= 0; i--) {
				count = doCount(grid[i][j], count);
				this.counts[i][j] += count;
			}
		}
	}

	private void rowCounts(char[][] grid) {
		for (int i = 0; i < rows; i++) {
			int count = 0;
			// from left to right
			for (int j = 0; j < cols; j++) {
				count = doCount(grid[i][j], count);
				this.counts[i][j] += count;
			}
			count = 0;
			// from right to left
			for (int j = cols - 1; j >= 0; j--) {
				count = doCount(grid[i][j], count);
				this.counts[i][j] += count;
			}
		}
	}

	private int doCount(char c, int count) {
		if (c == 'W') {
			count = 0;
		} else if (c == 'E') {
			count += 1;
		}
		return count;
	}

	public static void main(String[] args) {
		BombEnemy be = new BombEnemy();
		char[][] grid = { { 'E', '0', 'E' }, { 'W', 'E', '0' }, { 'E', 'W', '0' }, { '0', 'E', 0 } };
		System.out.println(be.count(grid)); // 3
	}
}
