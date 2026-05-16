package com.svetanis.algorithms.math.geometry;

// 892. Surface Area of 3D Shapes

public class SurfaceArea3D {
	// Time Complexity: O(n^2)

	public static int surfaceArea(int[][] g) {
		int area = 0;
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				if (g[i][j] > 0) {
					area += 2 + 4 * g[i][j];
					if (j > 0) {
						area -= Math.min(g[i][j - 1], g[i][j]) * 2;
					}
					if (i > 0) {
						area -= Math.min(g[i - 1][j], g[i][j]) * 2;
					}
				}
			}
		}
		return area;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 1, 2 }, { 3, 4 } };
		System.out.println(surfaceArea(g1)); // 34

		int[][] g2 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		System.out.println(surfaceArea(g2)); // 32

		int[][] g3 = { { 2, 2, 2 }, { 2, 1, 2 }, { 2, 2, 2 } };
		System.out.println(surfaceArea(g3)); // 46
	}
}
