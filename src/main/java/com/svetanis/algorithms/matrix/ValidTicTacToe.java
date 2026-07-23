package com.svetanis.algorithms.matrix;

// 794. Valid Tic-Tac-Toe State

public final class ValidTicTacToe {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	private String[] board;

	public boolean ttt(String[] board) {
		this.board = board;
		int zeros = count('O');
		int exes = count('X');
		if (exes != zeros && exes - 1 != zeros) {
			return false;
		}
		if (hasWon('X') && exes - 1 != zeros) {
			return false;
		}
		return !(hasWon('O') && exes != zeros);
	}

	private boolean hasWon(char c) {
		for (int i = 0; i < 3; i++) {
			// check row
			boolean row0 = board[i].charAt(0) == c;
			boolean row1 = board[i].charAt(1) == c;
			boolean row2 = board[i].charAt(2) == c;
			if (row0 && row1 & row2) {
				return true;
			}
			// check column
			boolean col0 = board[0].charAt(i) == c;
			boolean col1 = board[1].charAt(i) == c;
			boolean col2 = board[2].charAt(i) == c;
			if (col0 && col1 & col2) {
				return true;
			}
		}
		// check diagonal
		boolean dg10 = board[0].charAt(0) == c;
		boolean dg11 = board[1].charAt(1) == c;
		boolean dg12 = board[2].charAt(2) == c;
		if (dg10 && dg11 & dg12) {
			return true;
		}
		// check anti-diagonal
		boolean dg20 = board[0].charAt(2) == c;
		boolean dg21 = board[1].charAt(1) == c;
		boolean dg22 = board[2].charAt(0) == c;
		return dg20 && dg21 & dg22;
	}

	private int count(char c) {
		int count = 0;
		for (String row : board) {
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
		String[] board1 = { "O   ", "   ", "   " };
		System.out.println(vt.ttt(board1)); // false
		String[] board2 = { "XOX", " X ", "   " };
		System.out.println(vt.ttt(board2)); // false
		String[] board3 = { "XOX", "O O", "XOX" };
		System.out.println(vt.ttt(board3)); // true
	}
}
