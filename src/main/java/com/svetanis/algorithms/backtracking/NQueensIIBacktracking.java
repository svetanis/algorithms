package com.svetanis.algorithms.backtracking;

// 52. N-Queens II

// the N queens puzzle is the problem of placing N chess queens
// on an NxN chessboard so that no two queens threaten each other
// thus, a solution requires that no two queens share the same
// row, col, or diagonal

public final class NQueensIIBacktracking {
	// Time Complexity: O(n!)
	// Space Complexity: O(n)

	private int count;
	private boolean[] columns;
	private boolean[] posDiag;
	private boolean[] negDiag;

	public int queen(int n) {
		this.count = 0;
		this.columns = new boolean[n];
		this.posDiag = new boolean[2 * n];
		this.negDiag = new boolean[2 * n];
		dfs(0, n);
		return count;
	}

	private void dfs(int row, int n) {
		if (row == n) {
			count++;
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
			// move on to the next row
			dfs(row + 1, n);
			// backtrack
			columns[col] = false;
			posDiag[pdi] = false;
			negDiag[ndi] = false;
		}
	}

	public static void main(String[] args) {
		NQueensIIBacktracking nqb1 = new NQueensIIBacktracking();
		System.out.println(nqb1.queen(4)); // 2
		NQueensIIBacktracking nqb2 = new NQueensIIBacktracking();
		System.out.println(nqb2.queen(1)); // 1
	}
}
