package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 1074. Number of Submatrices That Sum to Target
//
// Input: a grid of integers and a target. Returns the number of submatrices -- rectangles of
// cells -- whose cells add up to target. On LeetCode: 1 <= rows, cols <= 100,
// -1000 <= grid[i][j] <= 1000, -10^8 <= target <= 10^8. An empty grid is outside that range and
// throws ArrayIndexOutOfBoundsException at grid[0].
//
// The one idea: fix the top row i and the bottom row j of a rectangle. Then sum[col] holds the
// total of column col from row i down to row j, and the rectangle with columns c1 .. c2 sums to
// sum[c1] + ... + sum[c2]. That turns the 2D question into the 1D one for this band of rows: how
// many runs of the array sum add up to target -- the same running-sum map as 560 (Subarray Sum
// Equals K), done in count. Moving j down one row adds one row to each sum[col], so the band's
// array is updated rather than rebuilt.
//
// Time: O(rows^2 * cols) -- rows(rows + 1) / 2 pairs of top and bottom rows, each paying cols
//   steps to add a row and cols steps to count
// Space: O(cols) -- the band's array and the count's map of at most cols + 1 running sums
//
// The largest cell total is 100 * 100 * 1000 = 10^7, so every sum fits in an int.

public final class CountSubMatricesSumToTarget {

	public static int blockSum(int[][] grid, int target) {
		int rows = grid.length;
		int cols = grid[0].length;
		int count = 0;
		for (int i = 0; i < rows; i++) { // TOP row of the band
			int[] sum = new int[cols]; // BAND: sum[col] = column col from row i to row j
			for (int j = i; j < rows; j++) { // BOTTOM row of the band
				for (int col = 0; col < cols; col++) {
					sum[col] += grid[j][col]; // EXTEND the band down by row j
				}
				count += count(sum, target); // COUNT: the 1D question on this band
			}
		}
		return count;
	}

	private static int count(int[] a, int target) {
		Map<Integer, Integer> map = new HashMap<>(); // MAP: running sum -> how many prefixes had it
		map.put(0, 1); // SEED: the empty prefix
		int sum = 0;
		int count = 0;
		for (int num : a) {
			sum += num; // PREFIX: the total of the band's columns so far
			count += map.getOrDefault(sum - target, 0); // RECORD: one rectangle per such start
			map.merge(sum, 1, Integer::sum); // ADD after the lookup
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
