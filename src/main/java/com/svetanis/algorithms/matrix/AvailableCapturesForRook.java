package com.svetanis.algorithms.matrix;

// 999. Available Captures for Rook

public final class AvailableCapturesForRook {
	// Time Complexity: O(n^2)
	// Space Complexity: O(1)

	private static final int[] DIR = { -1, 0, 1, 0, -1 };

	public static int rookCaptures(char[][] board) {
		int count = 0;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col] == 'R') {
					for (int k = 0; k < 4; k++) {
						int x = row, y = col;
						int dx = DIR[k], dy = DIR[k + 1];
						while (safe(board, x + dx, y + dy)) {
							x += dx;
							y += dy;
							if (board[x][y] == 'p') {
								count++;
								break;
							}
						}
					}
					break;
				}
			}
		}
		return count;
	}

	private static boolean safe(char[][] board, int x, int y) {
		boolean rows = x >= 0 && x < board.length;
		boolean cols = y >= 0 && y < board[0].length;
		return rows && cols && board[x][y] != 'B';
	}

	public static void main(String[] args) {
		char[][] board = { { '.', '.', '.', '.', '.', '.', '.', '.' }, //
				{ '.', '.', '.', 'p', '.', '.', '.', '.' }, //
				{ '.', '.', '.', 'R', '.', '.', '.', 'p' }, //
				{ '.', '.', '.', '.', '.', '.', '.', '.' }, //
				{ '.', '.', '.', '.', '.', '.', '.', '.' }, //
				{ '.', '.', '.', 'p', '.', '.', '.', '.' }, //
				{ '.', '.', '.', '.', '.', '.', '.', '.' }, //
				{ '.', '.', '.', '.', '.', '.', '.', '.' } };
		System.out.println(rookCaptures(board)); // 3
	}
}
