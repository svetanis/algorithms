package com.svetanis.algorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 51. N-Queens

// the N queens puzzle is the problem of placing N chess queens
// on an NxN chessboard so that no two queens threaten each other
// thus, a solution requires that no two queens share the same
// row, col, or diagonal

public final class NQueensBacktracking {
	// Time Complexity: O(n!)
	// Space Complexity: O(n^2)

	private boolean[] columns;
	private boolean[] posDiag;
	private boolean[] negDiag;
	private List<List<String>> lists;

	public List<List<String>> queen(int n) {
		this.columns = new boolean[n];
		this.posDiag = new boolean[2 * n];
		this.negDiag = new boolean[2 * n];
		this.lists = new ArrayList<>();
		String[][] board = board(n);
		dfs(0, n, board);
		return lists;
	}

	private String[][] board(int n) {
		String[][] board = new String[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(board[i], ".");
		}
		return board;
	}

	private void dfs(int row, int n, String[][] board) {
		if (row == n) {
			lists.add(joined(board));
			return;
		}
		for (int col = 0; col < n; col++) {
			int pdi = row + col;
			int ndi = row - col + n;
			boolean visited = columns[col] || posDiag[pdi] || negDiag[ndi];
			if (visited) {
				continue;
			}
			// place the queen
			columns[col] = true;
			posDiag[pdi] = true;
			negDiag[ndi] = true;
			board[row][col] = "Q";
			// move on to the next row
			dfs(row + 1, n, board);
			// backtrack
			columns[col] = false;
			posDiag[pdi] = false;
			negDiag[ndi] = false;
			board[row][col] = ".";
		}
	}

	private List<String> joined(String[][] board) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			list.add(String.join("", board[i]));
		}
		return list;
	}

	public static void main(String[] args) {
		NQueensBacktracking nqb1 = new NQueensBacktracking();
		System.out.println(nqb1.queen(4)); // 2
		NQueensBacktracking nqb2 = new NQueensBacktracking();
		System.out.println(nqb2.queen(1)); // 1
	}
}
