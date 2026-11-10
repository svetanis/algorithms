package com.svetanis.algorithms.matrix.tictactoe;

// 794. Valid Tic-Tac-Toe State
//
// Input: board -- three strings of three characters, each 'X', 'O' or ' ' (empty). Returns true if
// the board can be reached in a game where X moves first, the players take turns, and play stops
// as soon as a player has three in a line. On LeetCode: board.length == 3, board[i].length == 3,
// board[i][j] is 'X', 'O' or ' '.
//
// The one idea: one cell holds a mark or a space, and counting the marks tells whose turn it was.
// X moves first and the players take turns, so X has as many marks as O, or one more; any other
// count cannot happen. A win ends the game, so the winner made the last move: if X has three in a
// line, X must be one ahead; if O has three in a line, the counts must be equal. These three rules
// are the whole condition -- every board that passes them can be reached. Both players winning
// fails by itself, since X cannot be one ahead and level at once.
//
// Time: O(1) -- 9 cells counted for each mark, 8 lines checked for each player.
// Space: O(1).

public final class ValidTicTacToe {

	private String[] board; // BOARD: the current call's board, read by the helpers

	public boolean ttt(String[] board) {
		this.board = board;
		int zeros = count('O');
		int exes = count('X');
		if (exes != zeros && exes - 1 != zeros) { // TURNS: X level with O, or one ahead
			return false;
		}
		if (hasWon('X') && exes - 1 != zeros) { // X WON: X moved last, so one ahead
			return false;
		}
		return !(hasWon('O') && exes != zeros); // O WON: O moved last, so level
	}

	private boolean hasWon(char c) {
		for (int i = 0; i < 3; i++) {
			boolean row0 = board[i].charAt(0) == c; // ROW i
			boolean row1 = board[i].charAt(1) == c;
			boolean row2 = board[i].charAt(2) == c;
			if (row0 && row1 && row2) {
				return true;
			}
			boolean col0 = board[0].charAt(i) == c; // COLUMN i
			boolean col1 = board[1].charAt(i) == c;
			boolean col2 = board[2].charAt(i) == c;
			if (col0 && col1 && col2) {
				return true;
			}
		}
		boolean dg10 = board[0].charAt(0) == c; // DIAGONAL: top-left to bottom-right
		boolean dg11 = board[1].charAt(1) == c;
		boolean dg12 = board[2].charAt(2) == c;
		if (dg10 && dg11 && dg12) {
			return true;
		}
		boolean dg20 = board[0].charAt(2) == c; // ANTI-DIAGONAL: top-right to bottom-left
		boolean dg21 = board[1].charAt(1) == c;
		boolean dg22 = board[2].charAt(0) == c;
		return dg20 && dg21 && dg22;
	}

	private int count(char c) {
		int count = 0;
		for (String row : board) { // COUNT: c's marks on the board
			for (char cell : row.toCharArray()) {
				if (cell == c) {
					count += 1;
				}
			}
		}
		return count;
	}

	public static void main(String[] agrs) {
		ValidTicTacToe vt = new ValidTicTacToe();
		String[] board1 = { "O  ", "   ", "   " };
		System.out.println(vt.ttt(board1)); // false
		String[] board2 = { "XOX", " X ", "   " };
		System.out.println(vt.ttt(board2)); // false
		String[] board3 = { "XOX", "O O", "XOX" };
		System.out.println(vt.ttt(board3)); // true
	}
}
