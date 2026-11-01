package com.svetanis.algorithms.backtracking.queens;

// 52. N-Queens II
//
// the N queens puzzle is the problem of placing N chess queens
// on an NxN chessboard so that no two queens threaten each other
// thus, a solution requires that no two queens share the same
// row, col, or diagonal
//
// Input: n, the side of the board (1 <= n <= 9 on LeetCode). Returns how many different ways n
// queens can be placed so that no two attack each other (1 for n = 1, 0 for n = 2 and 3, 2 for
// n = 4, 92 for n = 8).
//
// The one idea: two queens cannot share a row, so each row holds exactly one queen. The search
// fills the rows from the top: one call places one row's queen, trying every column, and a
// column is allowed when no queen already placed sits in it or on either diagonal through
// (row, col). Three boolean arrays answer that in one lookup each: colTaken[col] says the column
// is taken; sumDiagonalTaken[row + col] says the diagonal that rises to the right is taken (every
// square on it has the same row + col, 0 .. 2n - 2); diffDiagonalTaken[row - col + n - 1] says
// the diagonal that falls to the right is taken (every square on it has the same row - col, which
// runs from -(n - 1) to n - 1, so + n - 1 shifts it to 0 .. 2n - 2). A queen is placed by setting
// its three flags and taken off again on the way out. Reaching row n adds one to the count -- no
// board is kept, because nothing is printed.
//
// Time: O(n * n!) -- at most n! / (n - k)! placements reach row k (about e * n! in all), each
// trying n columns at O(1).
// Space: O(n) -- the three flag arrays and the recursion depth.
//
// Siblings: NQueensII counts by scanning every queen above for each try, O(n) per check.
// NQueensBacktracking lists the boards (LC 51) with the same flags.

public final class NQueensIIBacktracking {

	private int count;
	private boolean[] colTaken; // index: col
	private boolean[] sumDiagonalTaken; // rises to the right, index: row + col
	private boolean[] diffDiagonalTaken; // falls to the right, index: row - col + n - 1

	public int queen(int n) {
		// PREPARE: the count and the three "taken" arrays -- no board, only the count is returned
		this.count = 0;
		this.colTaken = new boolean[n];
		this.sumDiagonalTaken = new boolean[2 * n - 1];
		this.diffDiagonalTaken = new boolean[2 * n - 1];
		dfs(0, n); // SEARCH: from row 0
		return count;
	}

	private void dfs(int row, int n) {
		if (row == n) { // RECORD: rows 0 .. n - 1 all hold a queen, one more board
			count++;
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
			dfs(row + 1, n); // GO: the next row
			colTaken[col] = false; // REMOVE: three changes above, so three undos
			sumDiagonalTaken[sumDiagonal] = false; // REMOVE
			diffDiagonalTaken[diffDiagonal] = false; // REMOVE
		}
	}

	public static void main(String[] args) {
		NQueensIIBacktracking nqb1 = new NQueensIIBacktracking();
		System.out.println(nqb1.queen(4)); // 2
		NQueensIIBacktracking nqb2 = new NQueensIIBacktracking();
		System.out.println(nqb2.queen(1)); // 1
	}
}
