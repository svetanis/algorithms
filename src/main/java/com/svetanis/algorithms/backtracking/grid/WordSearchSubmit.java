package com.svetanis.algorithms.backtracking.grid;

// 79. Word Search
//
// Input: a board of letters (m rows, n columns) and a word s. On LeetCode: 1 <= m, n <= 6 and
// 1 <= s.length <= 15, letters only (upper or lower case).
// Returns: true if s can be traced on the board. The path moves from a cell to a neighbour up,
// down, left or right, spells s letter by letter, and uses each cell at most once.
//
// The one idea: try every cell as the first letter, and from a matching cell try each of its four
// neighbours for the next letter. A cell on the current path must not be used again, so it is
// overwritten with '0' (a character that never matches a letter) while the search is below it,
// and the letter is put back when the search leaves the cell -- whether it found the word or not,
// because the board belongs to the caller. This is backtracking, and the board itself is the
// visited set. The search stops at the first neighbour that finishes the word.
//
// Time: O(m * n * 3^L), L = s.length -- every cell can start a path, and after the first step only
// three neighbours are open (the cell just left is marked).
// Space: O(L) -- the recursion at most L deep; the board is reused, not copied.
//
// Siblings: WordSearch takes the rows as a list of strings, works on a copy and answers 1 or 0,
// and explores all four neighbours even after one has succeeded.

public final class WordSearchSubmit {

	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	public static boolean wordSearch(char[][] board, String s) {
		int rows = board.length;
		int cols = board[0].length;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) { // START: every cell may hold the first letter
				if (dfs(board, s, row, col, 0)) {
					return true; // RETURN: the word was found
				}
			}
		}
		return false; // RETURN: no start cell led to the word
	}

	private static boolean dfs(char[][] board, String s, int row, int col, int index) {
		int len = s.length();
		if (index == len - 1) {
			return board[row][col] == s.charAt(index); // BASE: the last letter decides
		}
		if (board[row][col] != s.charAt(index)) {
			return false; // SKIP: this cell does not hold letter index
		}
		char c = board[row][col];
		board[row][col] = '0'; // MARK: on the path, so it cannot be used again
		for (int k = 0; k < dx.length; k++) { // WALK: the four neighbours
			int x = row + dx[k];
			int y = col + dy[k];
			if (valid(board, x, y) && board[x][y] != '0') {
				if (dfs(board, s, x, y, index + 1)) {
					// restore on the way out of a SUCCESSFUL search too --
					// the board belongs to the caller, and leaving marks on
					// it makes the next search on the same board wrong
					board[row][col] = c; // UNDO: also on success
					return true;
				}
			}
		}
		board[row][col] = c; // UNDO: this cell leads nowhere, so free it for other paths
		return false;
	}

	private static boolean valid(char[][] board, int row, int col) {
		int n = board.length;
		int m = board[0].length;
		boolean one = row >= 0 && row < n; // row number is in range
		boolean two = col >= 0 && col < m; // col number is in range
		return one && two; // RETURN: inside the board
	}

	public static void main(String[] args) {
		char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		System.out.println(wordSearch(board, "ABCCED"));// true

		char[][] board2 = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		System.out.println(wordSearch(board2, "SEE"));// true

		char[][] board3 = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		System.out.println(wordSearch(board3, "ABCB"));// false

		char[][] board4 = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		System.out.println(wordSearch(board4, "ABFSAB"));// false

		char[][] board5 = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		System.out.println(wordSearch(board5, "ABCD"));// false
	}
}
