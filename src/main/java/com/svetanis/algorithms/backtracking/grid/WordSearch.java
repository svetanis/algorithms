package com.svetanis.algorithms.backtracking.grid;

import java.util.Arrays;
import java.util.List;

// 79. Word Search (the version that takes the board as a list of row strings and answers 1 or 0)
//
// Input: a board as a list of equal-length strings (the rows), and a word s. On LeetCode:
// 1 <= m, n <= 6 and 1 <= s.length <= 15, letters only (upper or lower case).
// Returns: 1 if s can be traced on the board, 0 if not. The path moves from a cell to a
// neighbour up, down, left or right, spells s letter by letter, and uses each cell at most once.
//
// The one idea: try every cell as the first letter, and from a matching cell try each of its four
// neighbours for the next letter. A cell on the current path must not be used again, so it is
// overwritten with '+' (a character that never matches a letter) while the search is below it,
// and the letter is put back if every continuation fails -- this is backtracking, and the board
// itself is the visited set. The search works on a copy of the rows, so the caller's list is
// untouched. When the last letter matches, the answer is found.
//
// Time: O(m * n * 3^L), L = s.length -- every cell can start a path, and after the first step only
// three neighbours are open (the cell just left is marked). All four neighbours are always
// explored, even after one of them has already found the word, so a success costs the same as a
// failure for that start cell.
// Space: O(m * n + L) -- the copy of the board and the recursion at most L deep.
//
// Siblings: WordSearchSubmit is the LeetCode shape (char[][] in, boolean out) that stops at the
// first neighbour that succeeds, and that writes into the caller's board and puts it back.

public final class WordSearch {

	public static int wordSearch(List<String> list, String s) {
		int n = list.size();
		int m = list.get(0).length();
		char[][] board = init(list);
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) { // START: every cell may hold the first letter
				if (dfs(board, s, row, col, 0)) {
					return 1; // RETURN: the word was found
				}
			}
		}
		return 0; // RETURN: no start cell led to the word
	}

	private static boolean dfs(char[][] board, String s, int row, int col, int index) {
		int len = s.length();
		if (!valid(board, row, col) || index > len) {
			return false; // SKIP: off the board
		}
		if (board[row][col] == s.charAt(index)) { // MATCH: this cell holds letter index
			char c = board[row][col];
			board[row][col] = '+'; // MARK: on the path, so it cannot be used again
			if (index == len - 1) {
				return true; // RETURN: the last letter matched
			}
			boolean right = dfs(board, s, row, col + 1, index + 1); // RECURSE: the four neighbours
			boolean left = dfs(board, s, row, col - 1, index + 1);
			boolean up = dfs(board, s, row - 1, col, index + 1);
			boolean down = dfs(board, s, row + 1, col, index + 1);
			if (right || left || up || down) {
				return true; // RETURN: some neighbour finished the word
			}
			board[row][col] = c; // UNDO: this cell leads nowhere, so free it for other paths
		}
		return false;
	}

	private static boolean valid(char[][] board, int row, int col) {
		int n = board.length;
		int m = board[0].length;
		boolean one = row >= 0 && row < n; // row number is in range
		boolean two = col >= 0 && col < m; // col number is in range
		return one && two; // RETURN: inside the board
	}

	// a copy of the rows as a grid of characters
	private static char[][] init(List<String> list) {
		int n = list.size();
		int m = list.get(0).length();
		char[][] grid = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = list.get(i).charAt(j); // COPY: one letter
			}
		}
		return grid; // RETURN: the copy
	}

	public static void main(String[] args) {
		List<String> list = Arrays.asList("ABCE", "SFCS", "ADEE");//
		System.out.println(wordSearch(list, "ABCCED"));// 1
		System.out.println(wordSearch(list, "SEE"));// 1
		System.out.println(wordSearch(list, "ABCB"));// 0
		System.out.println(wordSearch(list, "ABFSAB"));// 0
		System.out.println(wordSearch(list, "ABCD"));// 0
	}
}
