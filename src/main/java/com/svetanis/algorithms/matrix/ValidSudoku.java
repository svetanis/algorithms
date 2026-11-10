package com.svetanis.algorithms.matrix;

// 36. Valid Sudoku
//
// Input: a 9 x 9 board; each cell holds a digit '1'..'9' or '.' for empty. Returns true if no digit
// repeats within any row, any column, or any of the nine 3 x 3 boxes. Only the filled cells are
// checked; the board need not be solvable. On LeetCode: board.length == 9,
// board[i].length == 9, board[i][j] is a digit 1-9 or '.'.
//
// The one idea: one filled cell (i, j) holding digit d belongs to three groups -- row i, column j
// and one box -- and each group keeps nine yes/no flags, "digit d already seen here". The cell is a
// repeat if any of its three groups already has d's flag; otherwise it sets the flag in all three.
// Digit d uses flag d - 1. The box of cell (i, j) comes from dropping the remainders: i / 3 and
// j / 3 each run 0..2.
//
// Time: O(1) -- the board is always 9 x 9, so 81 cells.
// Space: O(1) -- 27 groups of 9 flags.
//
// Two methods, one problem: they differ only in how the box is indexed -- isSudoku numbers the
// nine boxes 0..8 as (i / 3) * 3 + j / 3, isValidSudoku keeps a 3 x 3 array of boxes,
// [i / 3][j / 3].
// Siblings: ValidSudokuMemoryOptimized keeps each group's nine flags as the bits of one int.

public final class ValidSudoku {

	private static final int SIZE = 9;

	public static boolean isSudoku(char[][] board) {
		boolean[][] rows = new boolean[SIZE][SIZE]; // FLAGS: rows[i][d - 1] -- d seen in row i
		boolean[][] cols = new boolean[SIZE][SIZE]; // FLAGS: cols[j][d - 1] -- d seen in column j
		boolean[][] grid = new boolean[SIZE][SIZE]; // FLAGS: grid[box][d - 1], box numbered 0..8
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				char c = board[i][j];
				if (c == '.') { // SKIP: an empty cell
					continue;
				}
				if (c < '1' || c > '9') { // GUARD: not a digit -- LeetCode never sends one
					return false;
				}
				int value = c - '0' - 1; // FLAG: digit d -> d - 1
				int index = (i / 3) * 3 + j / 3; // BOX: 3 boxes per band of rows, numbered 0..8

				if (rows[i][value] || cols[j][value] || grid[index][value]) { // REPEAT: seen in a group
					return false;
				}
				rows[i][value] = true; // RECORD: in all three groups
				cols[j][value] = true;
				grid[index][value] = true;
			}
		}
		return true;
	}

	public static boolean isValidSudoku(char[][] board) {
		boolean[][] rows = new boolean[SIZE][SIZE];
		boolean[][] cols = new boolean[SIZE][SIZE];
		boolean[][][] grid = new boolean[SIZE / 3][SIZE / 3][SIZE]; // FLAGS: grid[i / 3][j / 3][d - 1]
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				char c = board[i][j];
				if (c == '.') { // SKIP: an empty cell
					continue;
				}
				if (c < '1' || c > '9') { // GUARD: not a digit -- LeetCode never sends one
					return false;
				}
				int index = c - '0' - 1; // FLAG: digit d -> d - 1
				if (rows[i][index] || cols[j][index] || grid[i / 3][j / 3][index]) { // REPEAT
					return false;
				}
				rows[i][index] = true; // RECORD: in all three groups
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