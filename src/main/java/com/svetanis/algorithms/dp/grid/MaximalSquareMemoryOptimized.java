package com.svetanis.algorithms.dp.grid;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;

// 221. Maximal Square

public final class MaximalSquareMemoryOptimized {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int mss(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int max = MIN_VALUE;
		int[] prev = new int[n];
		for (int r = 0; r < m; r++) {
			int[] curr = new int[n];
			for (int c = 0; c < n; c++) {
				if (r == 0 || c == 0) {
					curr[c] = grid[r][c] - '0';
				} else {
					if (grid[r][c] == '1') {
						int top = prev[c];
						int left = curr[c - 1];
						int diag = prev[c - 1];
						curr[c] = 1 + min(min(top, left), diag);
					}
				}
				max = max(max, curr[c]);
			}
			prev = curr;
		}
		return max * max;
	}

	public static void main(String[] args) {
		char[][] g1 = { { '1', '0', '1', '0', '0' }, //
				{ '1', '0', '1', '1', '1' }, //
				{ '1', '1', '1', '1', '1' }, //
				{ '1', '0', '0', '1', '0' } };
		System.out.println(mss(g1)); // 4
		char[][] g2 = { { '0', '1' }, { '1', '0' } };
		System.out.println(mss(g2)); // 1
		char[][] g3 = { { '0' } };
		System.out.println(mss(g3)); // 0
	}
}
