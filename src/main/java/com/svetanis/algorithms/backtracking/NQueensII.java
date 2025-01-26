package com.svetanis.algorithms.backtracking;

import static java.lang.Math.abs;

import java.util.concurrent.atomic.AtomicInteger;

// 52. N-Queens II

// the N queens puzzle is the problem of placing N chess queens
// on an NxN chessboard so that no two queens threaten each other
// thus, a solution requires that no two queens share the same
// row, col, or diagonal

public final class NQueensII {
	// Time Complexity: O(n^n)

	public static int queen(int n) {
		AtomicInteger count = new AtomicInteger(0);
		int[] a = new int[n];
		dfs(0, count, a);
		return count.get();
	}

	private static void dfs(int row, AtomicInteger count, int[] a) {
		int n = a.length;
		if (row == n) {
			count.getAndIncrement();
			return;
		}
		for (int col = 0; col < n; col++) {
			if (valid(a, row, col)) {
				a[row] = col; // place queen
				dfs(row + 1, count, a);
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
