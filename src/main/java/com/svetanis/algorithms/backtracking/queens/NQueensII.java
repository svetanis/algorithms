package com.svetanis.algorithms.backtracking.queens;

import static java.lang.Math.abs;

import java.util.concurrent.atomic.AtomicInteger;

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
// The one idea: two queens cannot share a row, so each row holds exactly one queen, and a
// placement is just "which column in each row". The search fills the rows from the top: for row
// r it tries every column and keeps the ones where the new queen shares no column and no diagonal
// with the queens above (valid), then moves on to row r + 1. Two queens are on one diagonal
// exactly when their columns differ by as much as their rows do. Reaching row n adds one to the
// count.
//
// Time: O(n! * n^2) -- a queen in row k sits in a column no earlier row used, so at most
// n! / (n - k)! placements reach row k (about e * n! in all), and each tries n columns at O(n)
// per valid check.
// Space: O(n) -- the array and the recursion depth.
//
// Siblings: NQueensIIBacktracking counts with boolean arrays for the columns and diagonals, so
// each check is O(1). NQueen, NQueenPrint and NQueensBacktracking list the solutions (LC 51).

public final class NQueensII {

	public static int queen(int n) {
		AtomicInteger count = new AtomicInteger(0);
		int[] a = new int[n];
		dfs(0, count, a);
		return count.get();
	}

	private static void dfs(int row, AtomicInteger count, int[] a) {
		int n = a.length;
		if (row == n) { // BASE: every row has a queen, one more placement
			count.getAndIncrement();
			return;
		}
		for (int col = 0; col < n; col++) {
			if (valid(a, row, col)) { // TAKE: no queen above shares the column or a diagonal
				a[row] = col; // place queen
				dfs(row + 1, count, a); // WALK: on to the next row
			}
		}
	}

	// check if (r, c) is a valid spot for a queen by checking
	// if there is a queen in the same column or diagonal
	// no need to check it for queens in the same row
	private static boolean valid(int[] a, int r, int c) {
		for (int row = 0; row < r; row++) {
			int col = a[row];
			// check if rows have a queen in the same column
			if (c == col) {
				return false;
			}
			// check diagonals: if the distance between the
			// columns equals the distance between the rows,
			// then they're in the same diagonal
			int dx = r - row;
			int dy = abs(col - c);
			if (dy == dx) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(queen(4)); // 2
		System.out.println(queen(1)); // 1
	}
}
