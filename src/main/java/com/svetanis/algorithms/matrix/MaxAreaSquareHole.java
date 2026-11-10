package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 2943. Maximize Area of Square Hole in Grid
//
// Input: n, m -- a grid of n + 2 horizontal and m + 2 vertical bars, numbered from 1, making
// 1 x 1 cells; hBars and vBars -- the bars that may be removed, every other bar is fixed. Returns
// the largest area of a square hole after removing some of those bars. On LeetCode:
// 1 <= n, m <= 10^9, 1 <= hBars.length, vBars.length <= 100, 2 <= hBars[i] <= n + 1,
// 2 <= vBars[i] <= m + 1, the values of each array distinct.
//
// The one idea: one removed bar joins the two cells on either side of it. Removing k bars whose
// numbers follow one another, such as 4, 5, 6, joins k + 1 cells into one gap of height k + 1;
// bars whose numbers do not follow one another keep a fixed bar between them, and their gaps
// stay apart. So the tallest hole possible is the longest run of consecutive numbers in hBars,
// plus 1, and the widest is the same for vBars. A shorter side is always possible too, by
// removing fewer bars, so the largest square has side min(tallest, widest). Sorting the bars puts
// each run next to itself, and one walk measures the longest run.
//
// Time: O(h * log h + v * log v) -- the two sorts; each walk is linear.
// Space: O(log h + log v) -- sorts the caller's bar arrays in place.

public final class MaxAreaSquareHole {

	public static int maxSquare(int n, int m, 
			int[] hBars, int[] vBars) {
		int hmax = gapSize(hBars); // TALLEST hole
		int vmax = gapSize(vBars); // WIDEST hole
		int min = Math.min(hmax, vmax); // SIDE: a square fits the smaller of the two
		return min * min;
	}

	private static int gapSize(int[] bars) {
		Arrays.sort(bars); // SORT: each run of consecutive numbers side by side
		int max = 1;
		int curr = 1; // RUN: consecutive bars ending at bars[i]
		for (int i = 1; i < bars.length; i++) {
			if (bars[i] == bars[i - 1] + 1) {
				curr += 1; // EXTEND the run
			} else {
				max = Math.max(max, curr); // RECORD the run that just ended
				curr = 1;
			}
		}
		max = Math.max(max, curr); // RECORD the last run
		return max + 1; // GAP: k removed bars join k + 1 cells
	}

	public static void main(String[] agrs) {
		int[] hbars1 = { 2, 3 };
		int[] vbars2 = { 2 };
		System.out.println(maxSquare(2, 1, hbars1, vbars2)); // 4

		int[] hbars3 = { 2 };
		int[] vbars4 = { 2 };
		System.out.println(maxSquare(1, 1, hbars3, vbars4)); // 4

		int[] hbars5 = { 2, 3 };
		int[] vbars6 = { 2, 4 };
		System.out.println(maxSquare(2, 3, hbars5, vbars6)); // 4
	}
}
