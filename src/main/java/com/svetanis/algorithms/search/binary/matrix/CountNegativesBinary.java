package com.svetanis.algorithms.search.binary.matrix;

// given m x n matrix grid which is 
// sorted in non-increasing order
// both row-wise and column-wise,
// count negative numbers in grid

public final class CountNegativesBinary {
	// Time Complexity: O(n * log m)

	public static int countDescending(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int count = 0;
		for (int i = 0; i < n; i++) {
			// no negative numbers in the row
			if (matrix[i][m - 1] > 0) {
				continue;
			}
			// all numbers in the row are negative
			if (matrix[i][0] < 0) {
				count += (n - i) * m;
				break;
			}

			int left = 0;
			int right = m - 1;
			while (left <= right) {
				int mid = left + (right - left) / 2;
				if (matrix[i][mid] < 0) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			count += m - left;
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] m1 = { { 4, 3, 2, -1 }, { 3, 2, 1, -1 }, { 1, 1, -1, -2 }, { -1, -1, -2, -3 } };
		System.out.println(countDescending(m1));

		int[][] m2 = { { 3, 2 }, { 1, 0 } };
		System.out.println(countDescending(m2));

		int[][] m3 = { { 5, 4, 2, 1 }, { 3, 2, 1, -1 }, { 2, -1, -2, -3 }, { 1, -2, -3, -5 }, };
		System.out.println(countDescending(m3));
	}
}