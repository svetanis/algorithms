package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 1074. Number of Submatrices That Sum to Target

public final class CountSubMatricesSumToTarget {
	// Time complexity: O(n^2 * m^2)
	// Space complexity: O(n)

	public static int blockSum(int[][] grid, int target) {
		int rows = grid.length;
		int cols = grid[0].length;
		int count = 0;
		for (int i = 0; i < rows; i++) {
			int[] sum = new int[cols];
			for (int j = i; j < rows; j++) {
				// cumulative sum for each column in submatrix
				for (int col = 0; col < cols; col++) {
					sum[col] += grid[j][col];
				}
				count += count(sum, target);
			}
		}
		return count;
	}

	private static int count(int[] a, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		int sum = 0;
		int count = 0;
		for (int num : a) {
			sum += num;
			count += map.getOrDefault(sum - target, 0);
			map.merge(sum, 1, Integer::sum);
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 1, 0 } };
		System.out.println(blockSum(g1, 0)); // 4
		int[][] g2 = { { 1, -1 }, { -1, 1 } };
		System.out.println(blockSum(g2, 0)); // 5
		int[][] g3 = { { 904 } };
		System.out.println(blockSum(g3, 0)); // 0
	}
}
