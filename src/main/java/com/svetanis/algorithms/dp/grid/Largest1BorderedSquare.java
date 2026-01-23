package com.svetanis.algorithms.dp.grid;

// 1139. Largest 1-Bordered Square

public final class Largest1BorderedSquare {
	// Time Complexity: O(n * m * min(n,m))
	// Space Complexity: O(n * m)

	private int rows;
	private int cols;
	private int[][] down;
	private int[][] right;

	public int lbs(int[][] grid) {
		this.rows = grid.length;
		this.cols = grid[0].length;
		this.down = new int[rows + 1][cols + 1];
		this.right = new int[rows + 1][cols + 1];
		precompute(grid);
		int max = Math.min(rows, cols);
		for (int side = max; side > 0; side--) {
			for (int row = 0; row <= rows - side; row++) {
				for (int col = 0; col <= cols - side; col++) {
					boolean top = right[row][col] >= side;
					boolean left = down[row][col] >= side;
					boolean bottom = right[row + side - 1][col] >= side;
					boolean rightSide = down[row][col + side - 1] >= side;
					if (left && top && rightSide && bottom) {
						return side * side;
					}
				}
			}
		}
		return 0;
	}

	public int lbs2(int[][] grid) {
		this.rows = grid.length;
		this.cols = grid[0].length;
		this.down = new int[rows + 1][cols + 1];
		this.right = new int[rows + 1][cols + 1];
		precompute(grid);
		int max = Math.min(rows, cols);
		for (int side = max; side > 0; side--) {
			for (int row = rows - side + 1; row >= 0; row--) {
				for (int col = cols - side + 1; col >= 0; col--) {
					boolean top = right[row][col] >= side;
					boolean left = down[row][col] >= side;
					boolean bottom = right[row + side - 1][col] >= side;
					boolean rightSide = down[row][col + side - 1] >= side;
					if (left && top && rightSide && bottom) {
						return side * side;
					}
				}
			}
		}
		return 0;
	}

	private void precompute(int[][] grid) {
		for (int row = rows - 1; row >= 0; row--) {
			for (int col = cols - 1; col >= 0; col--) {
				if (grid[row][col] == 1) {
					down[row][col] = row + 1 < rows ? down[row + 1][col] + 1 : 1;
					right[row][col] = col + 1 < cols ? right[row][col + 1] + 1 : 1;
				}
			}
		}
	}

	public static void main(String[] args) {
		Largest1BorderedSquare lbs = new Largest1BorderedSquare();
		int[][] g1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		System.out.println(lbs.lbs(g1)); // 9

		int[][] g2 = { { 1, 1, 0, 0 } };
		System.out.println(lbs.lbs(g2)); // 1
	}
}
