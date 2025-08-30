package com.svetanis.algorithms.search.binary.matrix;

import java.util.Arrays;
import java.util.List;

// 1428. Leftmost Column with at Least a One

public final class LeftmostColumn {
	// Time Complexity: O(rows * log(cols))
	// Space Complexity: O(1)

	public int lmc(BinaryMatrix bm) {
		int min = Integer.MAX_VALUE;
		List<Integer> dimensions = bm.dimensions();
		int rows = dimensions.get(0);
		int cols = dimensions.get(1);
		for (int row = 0; row < rows; row++) {
			int left = 0;
			int right = cols - 1;
			while (left < right) {
				int mid = left + (right - left) / 2;
				if (bm.get(row, mid) == 1) {
					right = mid;
				} else {
					left = mid + 1;
				}
			}
			if (bm.get(row, left) == 1) {
				min = Math.min(min, left);
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 0, 0, 0, 1 }, //
				{ 0, 1, 1, 1 }, //
				{ 0, 0, 1, 1 }, //
				{ 0, 0, 0, 0 } };//
		BinaryMatrix bm = new BinaryMatrix(matrix);
		LeftmostColumn lmc = new LeftmostColumn();
		System.out.println(lmc.lmc(bm));
	}

	private static class BinaryMatrix {

		private int[][] matrix;

		public BinaryMatrix(int[][] matrix) {
			this.matrix = matrix;
		}

		public int get(int row, int col) {
			return matrix[row][col];
		}

		public List<Integer> dimensions() {
			return Arrays.asList(matrix.length, matrix[0].length);
		}
	}
}
