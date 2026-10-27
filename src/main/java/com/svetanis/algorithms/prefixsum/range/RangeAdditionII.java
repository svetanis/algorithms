package com.svetanis.algorithms.prefixsum.range;

// 598. Range Addition II
//
// Input: a grid of rows x cols cells (m x n on LeetCode), all 0, and operations, each [a, b],
// which adds 1 to every cell [x][y] with 0 <= x < a and 0 <= y < b -- the a x b rectangle in the
// top-left corner. Returns how many cells hold the largest value after all the operations. On
// LeetCode: 1 <= m, n <= 4 * 10^4, 0 <= operations.length <= 10^4, 1 <= a <= m, 1 <= b <= n;
// the answer is at most 1.6 * 10^9 and fits in an int.
//
// The one idea: every operation covers the corner cell [0][0], so [0][0] ends with the largest
// value, one per operation. A cell reaches that value only if every operation covers it, which
// means it lies within the smallest a and the smallest b of all the operations. So the answer is
// (smallest a) * (smallest b); with no operations every cell is 0, and all rows * cols of them
// tie. No grid is built and no prefix sum is needed.
//
// Time: O(k) -- one pass over the k operations.
// Space: O(1) -- the two running minimums, kept in the parameters.
//
// Siblings: none.

public final class RangeAdditionII {

	public static int maxCount(int rows, int cols, int[][] operations) {
		for (int[] operation : operations) {
			rows = Math.min(rows, operation[0]); // SHRINK to the rows every operation covers
			cols = Math.min(cols, operation[1]); // SHRINK to the columns every operation covers
		}
		return rows * cols; // COUNT: the rectangle every operation covers
	}

	public static void main(String[] args) {
		int[][] matrix = { //
				{ 2, 2 }, //
				{ 3, 3 } };//
		System.out.println(maxCount(3, 3, matrix)); // 4

		int[][] matrix1 = { //
				{ 2, 2 }, //
				{ 3, 3 }, //
				{ 3, 3 }, //
				{ 3, 3 }, //
				{ 2, 2 }, //
				{ 3, 3 }, //
				{ 3, 3 }, //
				{ 3, 3 }, //
				{ 2, 2 }, //
				{ 3, 3 }, //
				{ 3, 3 }, //
				{ 3, 3 } };//
		System.out.println(maxCount(3, 3, matrix1)); // 4

		int[][] matrix2 = {};//
		System.out.println(maxCount(3, 3, matrix2)); // 9
	}
}
