package com.svetanis.algorithms.search.binary.matrix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 1337. The K Weakest Rows in a Matrix

public final class KWeakestRows {
	// Time Complexity: O(m*log n + m * log m)
	// Space Complexity: O(m)

	public static int[] kWeakestRows(int[][] grid, int k) {
		int m = grid.length;
		int[] soldiers = new int[m];
		List<Integer> indexes = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			indexes.add(i);
			int ones = binary(grid[i]);
			soldiers[i] = ones;
		}
		indexes.sort(Comparator.comparingInt(index -> soldiers[index]));
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = indexes.get(i);
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