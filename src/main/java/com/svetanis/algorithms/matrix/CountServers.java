package com.svetanis.algorithms.matrix;

// 1267. Count Servers that Communicate

public final class CountServers {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int countServers(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[] row = new int[n];
		int[] col = new int[m];
		for(int j = 0; j < m; j++) {
			for(int i = 0; i < n; i++) {
				if(grid[i][j] == 1) {
					row[i]++;
					col[j]++;
				}
			}
		}

		int count = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(grid[i][j] == 1 && (row[i] > 1 || col[j] > 1)) {
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] agrs) {
		int[][] g1 = { { 1, 0 }, { 0, 1 } };
		System.out.println(countServers(g1)); // 0

		int[][] g2 = { { 1, 0 }, { 1, 1 } };
		System.out.println(countServers(g2)); // 3

		int[][] g3 = { { 1, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
		System.out.println(countServers(g3)); // 4
	}
}
