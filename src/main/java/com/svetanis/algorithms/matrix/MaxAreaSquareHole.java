package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 2943. Maximize Area of Square Hole in Grid

public final class MaxAreaSquareHole {
	// Time Complexity: O(h * log h + v * log v)
	// Space Complexity: O(log h + log v)

	public static int maxSquare(int n, int m, 
			int[] hBars, int[] vBars) {
		int hmax = gapSize(hBars);
		int vmax = gapSize(vBars);
		int min = Math.min(hmax, vmax);
		return min * min;
	}

	private static int gapSize(int[] bars) {
		Arrays.sort(bars);
		int max = 1;
		int curr = 1;
		for (int i = 1; i < bars.length; i++) {
			if (bars[i] == bars[i - 1] + 1) {
				curr += 1;
			} else {
				max = Math.max(max, curr);
				curr = 1;
			}
		}
		max = Math.max(max, curr);
		return max + 1;
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
