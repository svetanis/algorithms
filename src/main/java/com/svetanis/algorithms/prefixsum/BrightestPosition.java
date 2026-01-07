package com.svetanis.algorithms.prefixsum;

import java.util.TreeMap;

// 2021. Brightest Position on Street

public final class BrightestPosition {
	// Time complexity: O(n log n)
	// Space complexity: O(n)

	public static int brightestPosition(int[][] lights) {
		// track changes in brightness (diff array)
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int[] light : lights) {
			int center = light[0];
			int range = light[1];
			int left = center - range;
			int right = center + range + 1;
			map.merge(left, 1, Integer::sum);
			map.merge(right, -1, Integer::sum);
		}
		int max = 0;
		int curr = 0;
		int point = 0;
		for (int position : map.keySet()) {
			curr += map.get(position);
			if (curr > max) {
				max = curr;
				point = position;
			}
		}
		return point;
	}

	public static void main(String[] args) {
		int[][] lights = { { 5, 3 }, { 7, 2 } };
		System.out.println(brightestPosition(lights)); // 5
	}
}
