package com.svetanis.algorithms.math.geometry;

import java.util.HashMap;
import java.util.Map;

// 149. Max Points on a Line

public final class MaxPointsOnLine149 {
	// Time Complexity: O(n^2 * log(min(dx,dy))
	// Space Complexity: O(n^2)

	public static int maxPoints(int[][] grid) {
		int max = 1;
		int n = grid.length;
		for (int i = 0; i < n; i++) {
			int x1 = grid[i][0];
			int y1 = grid[i][1];
			Map<String, Integer> map = new HashMap<>();
			for (int j = i + 1; j < n; j++) {
				int x2 = grid[j][0];
				int y2 = grid[j][1];
				int dx = x2 - x1;
				int dy = y2 - y1;
				int gcd = gcd(dx, dy);
				String slope = (dx / gcd) + "." + (dy / gcd);
				map.put(slope, map.getOrDefault(slope, 0) + 1);
				max = Math.max(max, map.get(slope) + 1);
			}
		}
		return max;
	}

	private static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 1 }, { 2, 2 }, { 3, 3 } };
		System.out.println(maxPoints(grid1)); // 3

		int[][] grid2 = { { 1, 1 }, { 3, 2 }, { 5, 3 }, { 4, 1 }, { 2, 3 }, { 1, 4 } };
		System.out.println(maxPoints(grid2)); // 4
	}
}