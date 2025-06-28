package com.svetanis.algorithms.matrix;

// 36. Valid Sudoku

public final class ValidSudoku {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	private static final int SIZE = 9;

	public static boolean isSudoku(char[][] board) {
		boolean[][] rows = new boolean[SIZE][SIZE];
		boolean[][] cols = new boolean[SIZE][SIZE];
		boolean[][] grid = new boolean[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				char c = board[i][j];
				if (c == '.') {
					continue;
				}
				if (c < '1' || c > '9') {
					return false;
				}
				int value = c - '0' - 1;
				int index = (i / 3) * 3 + j / 3;

				if (rows[i][value] || cols[j][value] || grid[index][value]) {
					return false;
				}
				rows[i][value] = true;
				cols[j][value] = true;
				grid[index][value] = true;
			}
		}
		return true;
	}

	public static boolean isValidSudoku(char[][] board) {
		boolean[][] rows = new boolean[SIZE][SIZE];
		boolean[][] cols = new boolean[SIZE][SIZE];
		boolean[][][] grid = new boolean[SIZE / 3][SIZE / 3][SIZE];
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
				if (rows[i][index] || cols[j][index] || grid[i / 3][j / 3][index]) {
					return false;
				}
				rows[i][index] = true;
				cols[j][index] = true;
				grid[i / 3][j / 3][index] = true;
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
		System.out.println(isValidSudoku(board1)); // true

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
		System.out.println(isValidSudoku(board2)); // false
	}
}