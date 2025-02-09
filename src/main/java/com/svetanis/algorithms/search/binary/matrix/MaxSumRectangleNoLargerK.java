package com.svetanis.algorithms.search.binary.matrix;

import java.util.TreeSet;

// 363. Max Sum of Rectangle No Larger Than K

public final class MaxSumRectangleNoLargerK {
	// Time Complexity: O(n^2 * m^2 log m)
	// Space Complexity: O(m)

	public static int search(int[][] matrix, int target) {
		final int infinity = Integer.MAX_VALUE;
		int max = -infinity;
		int n = matrix.length;
		int m = matrix[0].length;
		for (int start = 0; start < n; start++) {
			int[] colSum = new int[m];
			for (int end = start; end < n; end++) {
				for (int col = 0; col < m; col++) {
					colSum[col] += matrix[end][col];
				}
				int sum = 0;
				TreeSet<Integer> set = new TreeSet<>();
				set.add(0);
				for (int s : colSum) {
					sum += s;
					Integer min = set.ceiling(sum - target);
					if (min != null) {
						max = Math.max(max, sum - min);
					}
					set.add(sum);
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[][] matrix = { //
				{ 1, 0, 1 }, //
				{ 0, -2, 3 } };//
		System.out.println(search(matrix, 2)); // 2
		int[][] matrix1 = { //
				{ 2, 2, -1 }, //
				{ 0, -2, 3 } };//
		System.out.println(search(matrix1, 3)); // 3
		int[][] matrix2 = { //
				{ 1, 0, 1 }, //
				{ 0, -2, 3 }, //
				{ 1, 2, -1 } };//
		System.out.println(search(matrix2, 8)); // 4
		int[][] matrix3 = { //
				{ 2, 2, -1 } };//
		System.out.println(search(matrix3, 0)); // -1
		int[][] matrix4 = { //
				{ 5, -4, -3, 4 }, //
				{ -3, -4, 4, 5 }, //
				{ 5, 1, 5, -4 } };//
		System.out.println(search(matrix4, 10)); // 10
	}
}
