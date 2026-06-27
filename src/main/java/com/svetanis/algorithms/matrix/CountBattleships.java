package com.svetanis.algorithms.matrix;

// 419. Battleships in a Board

public final class CountBattleships {
	// Time Complexity: O(n * m)
	// Space Complexity: O(1)

	public static int count(char[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int count = 0;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (grid[row][col] == 'X') {
					boolean one = row == 0 || grid[row - 1][col] == '.';
					boolean two = col == 0 || grid[row][col - 1] == '.';
					if (one && two) {
						count++;
					}
				}
			}
		}
		return count;
	}

	public static void main(String[] agrs) {
		char[][] g1 = { { 'X', '.', '.', 'X' }, { '.', '.', '.', 'X' }, { '.', '.', '.', 'X' } };
		System.out.println(count(g1));
	}
}
