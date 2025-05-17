package com.svetanis.algorithms.prefixsum.range;

// 1893. Check if All the Integers in a Range Are Covered

public final class RangeCoverage {
	// Time complexity: O(n)

	public static boolean covered(int[][] ranges, int left, int right) {
		int[] deltas = deltas(ranges);
		int coverage = 0;
		for (int i = 0; i < deltas.length; i++) {
			coverage += deltas[i];
			if (i >= left && i <= right && coverage == 0) {
				return false;
			}
		}
		return true;
	}

	private static int[] deltas(int[][] ranges) {
		int[] a = new int[52];
		for (int[] range : ranges) {
			int start = range[0];
			int end = range[1];
			a[start] += 1;
			a[end + 1] -= 1;
		}
		return a;
	}

	public static void main(String[] args) {
		int[][] ranges1 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		System.out.println(covered(ranges1, 2, 5)); // true

		int[][] ranges2 = { { 1, 10 }, { 10, 20 } };
		System.out.println(covered(ranges2, 21, 21)); // false
	}
}
