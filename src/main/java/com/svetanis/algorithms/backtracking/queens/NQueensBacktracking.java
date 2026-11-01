package com.svetanis.algorithms.backtracking.queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 51. N-Queens
//
// the N queens puzzle is the problem of placing N chess queens
// on an NxN chessboard so that no two queens threaten each other
// thus, a solution requires that no two queens share the same
// row, col, or diagonal
//
// Input: n, the side of the board (1 <= n <= 9 on LeetCode). Returns every solution as a list of
// n strings, one per row, with 'Q' for the queen and '.' for an empty square. n = 2 and n = 3
// have no solution (an empty list); n = 4 has 2, n = 8 has 92.
//
// The one idea: two queens cannot share a row, so each row holds exactly one queen. The search
// fills the rows from the top: one call places one row's queen, trying every column, and a
// column is allowed when no queen already placed sits in it or on either diagonal through
// (row, col). Three boolean arrays answer that in one lookup each: colTaken[col] says the column
// is taken; sumDiagonalTaken[row + col] says the diagonal that rises to the right is taken (every
// square on it has the same row + col, 0 .. 2n - 2); diffDiagonalTaken[row - col + n - 1] says
// the diagonal that falls to the right is taken (every square on it has the same row - col, which
// runs from -(n - 1) to n - 1, so + n - 1 shifts it to 0 .. 2n - 2). A queen is placed by setting
// its three flags and writing a 'Q', and taken off again on the way out. Row n reached means n
// queens are placed, so the board is copied into the result as strings.
//
// Time: O(n * n!) -- at most n! / (n - k)! placements reach row k (about e * n! in all), each
// trying n columns at O(1); plus O(n^2) to copy each solution's board.
// Space: O(n^2) -- the board; the flags and the recursion are O(n), and the result is extra.
//
// Siblings: NQueen returns the solutions as lists of columns (checking each queen above in O(n)
// instead of with flags); NQueenPrint prints them; NQueensII and NQueensIIBacktracking count.

public final class NQueensBacktracking {

	private boolean[] colTaken; // index: col
	private boolean[] sumDiagonalTaken; // rises to the right, index: row + col
	private boolean[] diffDiagonalTaken; // falls to the right, index: row - col + n - 1
	private List<List<String>> lists;

	public List<List<String>> queen(int n) {
		// PREPARE: the three "taken" arrays, the result, and an n x n board of '.'
		this.colTaken = new boolean[n];
		this.sumDiagonalTaken = new boolean[2 * n - 1];
		this.diffDiagonalTaken = new boolean[2 * n - 1];
		this.lists = new ArrayList<>();
		char[][] board = board(n);
		dfs(0, n, board); // SEARCH: from row 0
		return lists;
	}

	private char[][] board(int n) {
		char[][] board = new char[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(board[i], '.');
		}
		return board;
	}

	private void dfs(int row, int n, char[][] board) {
		if (row == n) { // RECORD: rows 0 .. n - 1 all hold a queen
			lists.add(joined(board));
			return;
		}
		for (int col = 0; col < n; col++) { // CHOOSE: a column for this row's queen
			int sumDiagonal = row + col;
			int diffDiagonal = row - col + n - 1; // + n - 1: the smallest, -(n - 1), becomes 0
			boolean taken = colTaken[col] //
					|| sumDiagonalTaken[sumDiagonal] //
					|| diffDiagonalTaken[diffDiagonal];
			if (taken) { // SKIP: the column or a diagonal already has a queen
				continue;
			}
			colTaken[col] = true; // PLACE: the column
			sumDiagonalTaken[sumDiagonal] = true; // PLACE: the diagonal that rises to the right
			diffDiagonalTaken[diffDiagonal] = true; // PLACE: the diagonal that falls to the right
			board[row][col] = 'Q'; // PLACE: the queen on the board
			dfs(row + 1, n, board); // GO: the next row
			colTaken[col] = false; // REMOVE: four changes above, so four undos
			sumDiagonalTaken[sumDiagonal] = false; // REMOVE
			diffDiagonalTaken[diffDiagonal] = false; // REMOVE
			board[row][col] = '.'; // REMOVE
		}
	}

	// a fresh list per board: lists.add keeps the list object itself, not a copy of its strings
	private List<String> joined(char[][] board) {
		List<String> list = new ArrayList<>();
		for (char[] line : board) {
			list.add(new String(line)); // one row as a string
		}
		return list;
	}

	public static void main(String[] args) {
		NQueensBacktracking nqb1 = new NQueensBacktracking();
		System.out.println(nqb1.queen(4)); // [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
		NQueensBacktracking nqb2 = new NQueensBacktracking();
		System.out.println(nqb2.queen(1)); // [[Q]]
	}
}
