package com.svetanis.algorithms.matrix;

// 1275. Find Winner on a Tic Tac Toe Game

public final class TicTacToeGameWinner {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public String winner(int[][] moves) {
		int n = 3;
		int total = moves.length;
		int[] counts = new int[8];
		for (int index = total - 1; index >= 0; index -= 2) {
			int row = moves[index][0];
			int col = moves[index][1];
			counts[row]++;
			counts[col + n]++;
			if (row == col) {
				counts[6]++;
			}
			if (row + col == 2) {
				counts[7]++;
			}
			boolean isRow = counts[row] == n;
			boolean isCol = counts[col + n] == n;
			boolean isDiag = counts[6] == n;
			boolean isAntiDiag = counts[7] == n;
			if (isRow || isCol || isDiag || isAntiDiag) {
				return index % 2 == 0 ? "A" : "B";
			}
		}
		return total == 9 ? "Draw" : "Pending";
	}

	public static void main(String[] agrs) {
		TicTacToeGameWinner tgw = new TicTacToeGameWinner();
		int[][] moves1 = { { 0, 0 }, { 2, 0 }, { 1, 1 }, { 2, 1 }, { 2, 2 } };
		System.out.println(tgw.winner(moves1)); // A

		int[][] moves2 = { { 0, 0 }, { 1, 1 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 2, 0 } };
		System.out.println(tgw.winner(moves2)); // B

		int[][] moves3 = { { 0, 0 }, { 1, 1 }, { 2, 0 }, { 1, 0 }, { 1, 2 }, { 2, 1 }, { 0, 1 }, { 0, 2 }, { 2, 2 } };
		System.out.println(tgw.winner(moves3)); // Draw
	}
}
