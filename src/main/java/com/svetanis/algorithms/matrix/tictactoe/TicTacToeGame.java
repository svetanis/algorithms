package com.svetanis.algorithms.matrix.tictactoe;

// 348. Design Tic-Tac-Toe
//
// Input: TicTacToeGame(n) starts an empty n x n board for players 1 and 2. move(row, col, player)
// puts player's mark on the empty cell (row, col) and returns player if that move fills a whole
// row, column or one of the two diagonals with player's marks, otherwise 0. On LeetCode:
// 2 <= n <= 100, player is 1 or 2, 0 <= row, col < n, each (row, col) is played once, at most
// n^2 calls, and no move comes after a win.
//
// The one idea: a player wins when one line holds n of their marks. There are 2n + 2 lines: n
// rows, n columns, the diagonal (row == col) and the anti-diagonal (row + col == n - 1). One
// counter holds how many marks one player has on one line: moves[p - 1][row] for row row,
// moves[p - 1][n + col] for column col, moves[p - 1][2n] for the diagonal, moves[p - 1][2n + 1] for
// the anti-diagonal. A move adds 1 to the counters of the lines through its cell -- at most four --
// and wins when one of them reaches n. The board itself is never stored: a line holding even one
// mark of the other player can never reach n for this one, because each cell is played once.
//
// Time: O(1) per move -- at most four counters change and four are read.
// Space: O(n) -- 2 * (2n + 2) counters.

public final class TicTacToeGame {

	private int size;
	private int[][] moves; // COUNTERS: [player - 1][line] -- that player's marks on that line

	public TicTacToeGame(int n) {
		this.size = n;
		this.moves = new int[2][2 * n + 2]; // LINES: n rows, n columns, 2 diagonals
	}

	public int move(int row, int col, int player) {
		moves[player - 1][row]++; // ROW
		moves[player - 1][col + size]++; // COLUMN
		if (row == col) { // DIAGONAL: top-left to bottom-right
			moves[player - 1][size * 2]++;
		}
		if (row + col == size - 1) { // ANTI-DIAGONAL: top-right to bottom-left
			moves[player - 1][size * 2 + 1]++;
		}
		boolean isRow = moves[player - 1][row] == size; // WIN: a line through this cell is full
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
		System.out.println(ttg.move(0, 0, 1)); // 0
		System.out.println(ttg.move(1, 1, 2)); // 0
		System.out.println(ttg.move(1, 0, 1)); // 0
		System.out.println(ttg.move(2, 2, 2)); // 0
		System.out.println(ttg.move(2, 0, 1)); // 1 -- column 0 is full
	}
}
