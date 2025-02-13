package com.svetanis.algorithms.matrix;

// 598. Range Addition II

public final class RangeAdditionII {

	public static int maxCount(int rows, int cols, int[][] operations) {
		for (int[] operation : operations) {
			rows = Math.min(rows, operation[0]);
			cols = Math.min(cols, operation[1]);
		}
		return rows * cols;
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
