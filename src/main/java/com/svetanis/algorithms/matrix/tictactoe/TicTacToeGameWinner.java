package com.svetanis.algorithms.matrix.tictactoe;

// 1275. Find Winner on a Tic Tac Toe Game
//
// Input: moves[i] = [row, col] -- the i-th move on a 3 x 3 board. Player A makes the moves at even
// indexes (0, 2, 4, ...), player B the moves at odd indexes. Returns "A" or "B" for the winner,
// "Draw" if all 9 cells are filled with no winner, "Pending" otherwise. On LeetCode:
// 1 <= moves.length <= 9, moves[i].length == 2, 0 <= row, col <= 2, no cell is played twice,
// and the moves follow the rules, so none comes after a win.
//
// The one idea: the game stops at a win, so only the player who moved last can have won. Walk back
// over that player's moves only -- the last one, then every second one before it -- and count
// their marks on each of the 8 lines: counts[0..2] the rows, counts[3..5] the columns, counts[6]
// the diagonal (row == col), counts[7] the anti-diagonal (row + col == 2). A line reaching 3 is a
// win, and the index's parity says whose. With no win, 9 moves is a draw and fewer is pending.
//
// Time: O(1) -- at most 5 moves read, 8 counters.
// Space: O(1) -- the 8 counters.

public final class TicTacToeGameWinner {

	public String winner(int[][] moves) {
		int n = 3;
		int total = moves.length;
		int[] counts = new int[8]; // COUNTERS: the last mover's marks on each of the 8 lines
		for (int index = total - 1; index >= 0; index -= 2) { // WALK: the last mover's moves only
			int row = moves[index][0];
			int col = moves[index][1];
			counts[row]++; // ROW
			counts[col + n]++; // COLUMN
			if (row == col) { // DIAGONAL
				counts[6]++;
			}
			if (row + col == 2) { // ANTI-DIAGONAL
				counts[7]++;
			}
			boolean isRow = counts[row] == n; // WIN: a line through this cell is full
			boolean isCol = counts[col + n] == n;
			boolean isDiag = counts[6] == n;
			boolean isAntiDiag = counts[7] == n;
			if (isRow || isCol || isDiag || isAntiDiag) {
				return index % 2 == 0 ? "A" : "B"; // WHO: even index -> A
			}
		}
		return total == 9 ? "Draw" : "Pending"; // NO WIN: full board, or moves still to come
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
