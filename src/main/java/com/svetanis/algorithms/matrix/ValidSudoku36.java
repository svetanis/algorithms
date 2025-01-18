package com.svetanis.algorithms.matrix;

// 36. Valid Sudoku

public final class ValidSudoku36 {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	private static final int SIZE = 9;

	public static boolean isSudoku(char[][] board) {
		boolean[][] rows = new boolean[SIZE][SIZE];
		boolean[][] cols = new boolean[SIZE][SIZE];
		boolean[][] grid = new boolean[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				char c = board[row][col];
				if (c == '.') {
					continue;
				}
				int num = c - '0' - 1;
				int index = (row / 3) * 3 + col / 3;

				if (rows[row][num] || cols[col][num] || grid[index][num]) {
					return false;
				}
				rows[row][num] = true;
				cols[col][num] = true;
				grid[index][num] = true;
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
		System.out.println(isSudoku(board2)); // true
	}
}