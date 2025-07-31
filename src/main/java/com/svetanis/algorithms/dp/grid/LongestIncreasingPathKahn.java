package com.svetanis.algorithms.dp.grid;

import java.util.ArrayDeque;
import java.util.Deque;

// 329. Longest Increasing Path in a Matrix

public final class LongestIncreasingPathKahn {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	private static final int[] dx = { 1, -1, 0, 0 };
	private static final int[] dy = { 0, 0, 1, -1 };

	public static int lip(int[][] grid) {
		int[][] inDegree = inDegree(grid);
		Deque<int[]> dq = initQueue(inDegree);
		int max = 0;
		while (!dq.isEmpty()) {
			max++;
			int size = dq.size();
			for (int i = 0; i < size; i++) {
				int[] cell = dq.poll();
				int row = cell[0];
				int col = cell[1];
				for (int k = 0; k < 4; k++) {
					int x = row + dx[k];
					int y = col + dy[k];
					if (valid(grid, x, y) && grid[x][y] > grid[row][col]) {
						inDegree[x][y]--;
						if (inDegree[x][y] == 0) {
							dq.offer(new int[] { x, y });
						}
					}
				}
			}
		}
		return max;
	}

	private static Deque<int[]> initQueue(int[][] inDegree) {
		int n = inDegree.length;
		int m = inDegree[0].length;
		Deque<int[]> dq = new ArrayDeque<>();
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				if (inDegree[row][col] == 0) {
					dq.offer(new int[] { row, col });
				}
			}
		}
		return dq;
	}

	private static int[][] inDegree(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] inDegree = new int[n][m];
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				for (int k = 0; k < 4; k++) {
					int x = row + dx[k];
					int y = col + dy[k];
					if (valid(grid, x, y) && grid[x][y] > grid[row][col]) {
						inDegree[x][y]++;
					}
				}
			}
		}
		return inDegree;
	}

	private static boolean valid(int[][] grid, int row, int col) {
		boolean one = row >= 0 && row < grid.length;
		boolean two = col >= 0 && col < grid[0].length;
		return one && two;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
		System.out.println(lip(g1)); // 4
		int[][] g2 = { { 3, 4, 5 }, { 3, 2, 6 }, { 2, 2, 1 } };
		System.out.println(lip(g2)); // 4
		int[][] g3 = { { 1 } };
		System.out.println(lip(g3)); // 1
	}
}
