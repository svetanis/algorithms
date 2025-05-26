package com.svetanis.algorithms.search.binary.matrix;

// 1198. Find Smallest Common Element in All Rows

public final class SmallestCommonElement {
	// Time Complexity: O(n * m)

	public static int sce(int[][] matrix) {
		int[] counts = new int[10001];
		for (int[] row : matrix) {
			for (int element : row) {
				counts[element]++;
				if (counts[element] == matrix.length) {
					return element;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[][] m1 = { { 1, 2, 3, 4 }, { 1, 3, 5, 7 }, { 1, 6, 8, 9 } };
		System.out.println(sce(m1)); // 1

		int[][] m2 = { { 1, 2, 3, 4, 5 }, { 2, 4, 5, 8, 10 }, { 3, 5, 7, 9, 11 }, { 1, 3, 5, 7, 9 } };
		System.out.println(sce(m2)); // 5
	}
}