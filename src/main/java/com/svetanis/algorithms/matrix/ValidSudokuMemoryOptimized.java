package com.svetanis.algorithms.matrix;

// 36. Valid Sudoku

public final class ValidSudokuMemoryOptimized {
	// Time Complexity: O(1) -- the board is always 9 x 9, so 81 cells
	// Space Complexity: O(1) -- 27 ints used as bitmasks; the same
	// information as ValidSudoku's sets, in fewer machine words

	private static final int SIZE = 9;

	public static boolean isSudoku(char[][] board) {
		int[] rows = new int[SIZE];
		int[] cols = new int[SIZE];
		int[][] grid = new int[SIZE / 3][SIZE / 3];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				char c = board[i][j];
				if (c == '.') {
					continue;
				}
				if (c < '1' || c > '9') {
					return false;
				}
				int index = c - '0' - 1;
				int value = 1 << index;
				boolean row = (rows[i] & value) > 0;
				boolean col = (cols[j] & value) > 0;
				boolean sqr = (grid[i / 3][j / 3] & value) > 0;
				if (row || col || sqr) {
					return false;
				}
				rows[i] |= value;
				cols[j] |= value;
				grid[i / 3][j / 3] |= value;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		char[][] board1 = { //
				{ '5', '3', '.', '.', '7', '.', '.', '.', '.' }, //
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, //
				{ '.', '9', '8', '.', '.', '.', '.', '6', '.' }, //
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, //
				{ '4', '.', '.', '8', '.', '3', '.', '.', '1' }, //
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, //
				{ '.', '6', '.', '.', '.', '.', '2', '8', '.' }, //
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, //
				{ '.', '.', '.', '.', '8', '.', '.', '7', '9' } };//
		System.out.println(isSudoku(board1)); // true

		char[][] board2 = { //
				{ '8', '3', '.', '.', '7', '.', '.', '.', '.' }, //
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, //
				{ '.', '9', '8', '.', '.', '.', '.', '6', '.' }, //
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, //
				{ '4', '.', '.', '8', '.', '3', '.', '.', '1' }, //
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, //
				{ '.', '6', '.', '.', '.', '.', '2', '8', '.' }, //
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, //
				{ '.', '.', '.', '.', '8', '.', '.', '7', '9' } };//
		System.out.println(isSudoku(board2)); // false
	}
}