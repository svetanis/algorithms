package com.svetanis.algorithms.matrix;

// 36. Valid Sudoku
//
// Input: a 9 x 9 board; each cell holds a digit '1'..'9' or '.' for empty. Returns true if no digit
// repeats within any row, any column, or any of the nine 3 x 3 boxes. Only the filled cells are
// checked; the board need not be solvable. On LeetCode: board.length == 9,
// board[i].length == 9, board[i][j] is a digit 1-9 or '.'.
//
// The one idea: one filled cell (i, j) holding digit d belongs to three groups -- row i, column j
// and box [i / 3][j / 3]. Each group is one int whose bits 0..8 are its nine flags: bit d - 1 set
// means "digit d already seen here". 1 << (d - 1) is the flag for this digit; & with a group reads
// it, |= sets it. The cell is a repeat if any of its three groups has the bit; otherwise it sets
// the bit in all three.
//
// Time: O(1) -- the board is always 9 x 9, so 81 cells.
// Space: O(1) -- 27 ints used as bitmasks; the same information as ValidSudoku's flags, in fewer
//        machine words.
//
// Siblings: ValidSudoku keeps the same three groups as arrays of nine booleans, in two methods
// that differ only in how the box is indexed.

public final class ValidSudokuMemoryOptimized {

	private static final int SIZE = 9;

	public static boolean isSudoku(char[][] board) {
		int[] rows = new int[SIZE]; // BITS: rows[i], bit d - 1 -- d seen in row i
		int[] cols = new int[SIZE]; // BITS: cols[j], bit d - 1 -- d seen in column j
		int[][] grid = new int[SIZE / 3][SIZE / 3]; // BITS: one int per 3 x 3 box
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				char c = board[i][j];
				if (c == '.') { // SKIP: an empty cell
					continue;
				}
				if (c < '1' || c > '9') { // GUARD: not a digit -- LeetCode never sends one
					return false;
				}
				int index = c - '0' - 1;
				int value = 1 << index; // FLAG: digit d -> bit d - 1
				boolean row = (rows[i] & value) > 0; // READ: the bit in each group
				boolean col = (cols[j] & value) > 0;
				boolean sqr = (grid[i / 3][j / 3] & value) > 0;
				if (row || col || sqr) { // REPEAT: seen in a group
					return false;
				}
				rows[i] |= value; // RECORD: set the bit in all three groups
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