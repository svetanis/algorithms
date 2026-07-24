package com.svetanis.algorithms.matrix;

// 348. Design Tic-Tac-Toe

public final class TicTacToeGame {
	// Time Complexity: O(1)
	// Space Complexity: O(n)

	private int size;
	private int[][] moves;

	public TicTacToeGame(int n) {
		this.size = n;
		this.moves = new int[2][2 * n + 2];
	}

	public int move(int row, int col, int player) {
		moves[player - 1][row]++;
		moves[player - 1][col + size]++;
		if (row == col) {
			moves[player - 1][size * 2]++;
		}
		if (row + col == size - 1) {
			moves[player - 1][size * 2 + 1]++;
		}
		boolean isRow = moves[player - 1][row] == size;
		boolean isCol = moves[player - 1][col + size] == size;
		boolean isDiag = moves[player - 1][size * 2] == size;
		boolean isAntiDiag = moves[player - 1][size * 2 + 1] == size;
		if (isRow || isCol || isDiag || isAntiDiag) {
			return player;
		}
		return 0;
	}

	public static void main(String[] agrs) {
		TicTacToeGame ttg = new TicTacToeGame(3);
		System.out.println(ttg.move(0, 0, 1));
		System.out.println(ttg.move(1, 1, 2));
		System.out.println(ttg.move(1, 0, 1));
		System.out.println(ttg.move(2, 2, 2));
		System.out.println(ttg.move(2, 0, 1));
	}
}
