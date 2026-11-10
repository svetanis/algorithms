package com.svetanis.algorithms.matrix;

// 999. Available Captures for Rook
//
// Input: board -- an 8 x 8 chess board; each cell holds 'R' (the one white rook), 'B' (a white
// bishop), 'p' (a black pawn) or '.' (an empty square). Returns the number of pawns the rook
// attacks: the pawns it reaches by moving straight up, down, left or right, before any other
// piece or the edge of the board stops it. On LeetCode: board.length == board[i].length == 8,
// board[i][j] is one of 'R', '.', 'B', 'p', and exactly one cell holds 'R'.
//
// The one idea: one cell holds one piece or nothing. The rook looks along each of its four lines
// one cell at a time, and the first piece it meets ends that line: a pawn is a capture, a bishop
// is a wall, and nothing past either one can be reached. So each of the four directions adds 0
// or 1, and the answer is at most 4.
//
// Time: O(1) -- the board is always 8 x 8: at most 64 cells to find the rook, at most 14 cells
// along its four lines.
// Space: O(1) -- a counter and a position.

public final class AvailableCapturesForRook {

	private static final int[] DIR = { -1, 0, 1, 0, -1 }; // DIRECTIONS: (DIR[k], DIR[k + 1]) = up, right, down, left

	public static int rookCaptures(char[][] board) {
		int count = 0; // COUNT: pawns the rook attacks
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col] == 'R') { // FIND the rook
					for (int k = 0; k < 4; k++) { // LOOK along each of the four lines
						int x = row, y = col;
						int dx = DIR[k], dy = DIR[k + 1];
						while (safe(board, x + dx, y + dy)) { // STEP: on the board and not a bishop
							x += dx;
							y += dy;
							if (board[x][y] == 'p') { // CAPTURE: the first pawn on this line
								count++;
								break; // STOP: nothing past it can be reached
							}
						}
					}
					break; // DONE: leaves the column loop; no other row holds a rook
				}
			}
		}
		return count;
	}

	private static boolean safe(char[][] board, int x, int y) {
		boolean rows = x >= 0 && x < board.length;
		boolean cols = y >= 0 && y < board[0].length;
		return rows && cols && board[x][y] != 'B'; // BLOCK: a bishop ends the line like the edge
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
