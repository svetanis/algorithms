package com.svetanis.algorithms.matrix;

// 1975. Maximum Matrix Sum
//
// Input: an n x n matrix g of integers. One operation picks two cells side by side (sharing an
// edge) and multiplies both by -1; it may be done any number of times. Returns the largest sum of
// all the cells that some sequence of operations reaches. On LeetCode: 2 <= n <= 250,
// -10^5 <= g[i][j] <= 10^5.
//
// The one idea: one cell holds a number whose size never changes -- an operation can only flip its
// sign. So no sum can beat every cell made non-negative: the sum of the absolute values. Flipping
// along a chain of neighbours carries a minus sign from any cell to any other, and two minus signs
// that meet on one cell cancel, so minus signs can be removed in pairs. What no operation changes is
// whether the number of negative cells is even or odd, because each operation flips two signs at
// once. With an even count every minus sign goes. With an odd count one must stay, and it goes on
// the cell with the smallest absolute value, which costs 2 * that value; a zero among the cells
// makes that cost 0.
//
// Time: O(n^2) -- one pass over the n * n cells.
// Space: O(1) -- a count, a minimum and a sum.

public final class MaxMatrixSum {

	public static long maxMatrixSum(int[][] g) {
		int count = 0; // COUNT: the negative cells -- only even or odd matters
		int min = Integer.MAX_VALUE; // MIN: smallest absolute value, where a last minus sign stays
		long sum = 0; // SUM: of absolute values; up to 6.25 * 10^9, past an int
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				int curr = g[i][j];
				if (curr < 0) { // FLIP: count the minus sign, keep the size
					count += 1;
					curr = -curr;
				}
				sum += curr;
				if (curr < min) {
					min = curr;
				}
			}
		}
		return count % 2 == 0 ? sum : sum - 2 * min; // RETURN: even -> all cancel; odd -> one stays on min
	}

	public static void main(String[] agrs) {
		int[][] m1 = { { 1, -1 }, { -1, 1 } };
		System.out.println(maxMatrixSum(m1)); // 4

		int[][] m2 = { { 1, 2, 3 }, { -1, -2, -3 }, { 1, 2, 3 } };
		System.out.println(maxMatrixSum(m2)); // 16
	}
}
