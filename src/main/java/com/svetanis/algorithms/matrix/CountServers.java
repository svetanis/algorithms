package com.svetanis.algorithms.matrix;

// 1267. Count Servers that Communicate
//
// Input: grid -- n rows and m columns; a cell holds 1 (a server) or 0 (empty). Two servers
// communicate when they are in the same row or the same column. Returns the number of servers
// that communicate with at least one other server. On LeetCode: 1 <= rows, columns <= 250,
// grid[i][j] is 0 or 1.
//
// The one idea: one cell holds a server or nothing. A server communicates exactly when its row
// holds another server or its column does -- that is, when the server count of its row or of its
// column is more than 1, since each count includes the server itself. So the first pass counts
// the servers of every row and of every column, and the second pass counts the servers whose row
// count or column count is at least 2.
//
// Time: O(n * m) -- two passes over the grid.
// Space: O(n + m) -- one count per row and per column.

public final class CountServers {

	public static int countServers(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[] row = new int[n]; // row[i]: servers in row i
		int[] col = new int[m]; // col[j]: servers in column j
		for(int j = 0; j < m; j++) {
			for(int i = 0; i < n; i++) {
				if(grid[i][j] == 1) {
					row[i]++; // COUNT the server in its row
					col[j]++; // and in its column
				}
			}
		}

		int count = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(grid[i][j] == 1 && (row[i] > 1 || col[j] > 1)) { // SHARES a row or a column
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
