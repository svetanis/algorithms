package com.svetanis.algorithms.prefixsum.difference;

// 1893. Check if All the Integers in a Range Are Covered
//
// Input: ranges[i] = [start, end] -- covers every whole number from start to end, both ends
// included; left and right. Returns true when every whole number from left to right, both ends
// included, is covered by at least one range. On LeetCode: 1 <= ranges.length <= 50,
// 1 <= start <= end <= 50, 1 <= left <= right <= 50. The mark array has 52 cells, so the code
// works for every number from 0 to 50, where end + 1 still fits.
//
// The one idea: one cell a[p] holds the change in the number of ranges covering p: +1 where a
// range starts, -1 at end + 1, because end itself is still covered. Adding the cells from left to
// right gives how many ranges cover each number; the answer is false at the first number between
// left and right that no range covers.
//
// Time: O(n + 52) -- two marks per range, then one pass over the 52 cells.
// Space: O(52) -- the mark array, whatever the number of ranges.

public final class RangeCoverage {

	public static boolean covered(int[][] ranges, int left, int right) {
		int[] deltas = deltas(ranges); // MARKS
		int coverage = 0;
		for (int i = 0; i < deltas.length; i++) { // WALK
			coverage += deltas[i]; // ranges covering i
			if (i >= left && i <= right && coverage == 0) { // FAIL: in [left, right], and uncovered
				return false;
			}
		}
		return true; // RETURN: every number in [left, right] is covered
	}

	private static int[] deltas(int[][] ranges) {
		int[] a = new int[52]; // one cell per number 0 to 51
		for (int[] range : ranges) {
			int start = range[0];
			int end = range[1];
			a[start] += 1; // START
			a[end + 1] -= 1; // STOP: end is still covered; the range stops one number later
		}
		return a; // RETURN
	}

	public static void main(String[] args) {
		int[][] ranges1 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		System.out.println(covered(ranges1, 2, 5)); // true

		int[][] ranges2 = { { 1, 10 }, { 10, 20 } };
		System.out.println(covered(ranges2, 21, 21)); // false
	}
}
