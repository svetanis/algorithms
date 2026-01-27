package com.svetanis.algorithms.search.binary.matrix;

import java.util.PriorityQueue;

import com.svetanis.java.base.utils.Print;

// 1337. The K Weakest Rows in a Matrix

public final class KWeakestRowsPQ {
	// Time Complexity: O(m*log n + m * log m)
	// Space Complexity: O(m)

	public static int[] kWeakestRows(int[][] grid, int k) {
		int m = grid.length;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> 
		(a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]));
		for (int i = 0; i < m; i++) {
			int ones = binary(grid[i]);
			pq.offer(new int[] { i, ones });
			if (pq.size() > k) {
				pq.poll();
			}
		}
		int[] result = new int[k];
		while (!pq.isEmpty()) {
			result[--k] = pq.poll()[0];
		}
		return result;
	}

	private static int binary(int[] a) {
		int left = 0;
		int right = a.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (a[mid] == 0) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[][] m1 = { { 1, 1, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, //
				{ 1, 0, 0, 0, 0 }, { 1, 1, 0, 0, 0 }, { 1, 1, 1, 1, 1 } };
		Print.print(kWeakestRows(m1, 3)); // 2 0 3

		int[][] m2 = { { 1, 0, 0, 0 }, { 1, 1, 1, 1 }, { 1, 0, 0, 0 }, //
				{ 1, 0, 0, 0 }, };
		Print.print(kWeakestRows(m2, 2)); // 0 2
	}
}