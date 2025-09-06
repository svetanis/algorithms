package com.svetanis.algorithms.math.geometry;

// 3000. Maximum Area of Longest Diagonal Rectangle

public class MaxAreaLongestDiagonalRectangle {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int maxArea(int[][] dimensions) {
		int maxArea = 0;
		int maxDiag = 0;
		for (int[] dimension : dimensions) {
			int len = dimension[0];
			int wid = dimension[1];
			int area = len * wid;
			int diag = len * len + wid * wid;
			if (diag > maxDiag) {
				maxDiag = diag;
				maxArea = area;
			} else if (diag == maxDiag) {
				maxArea = Math.max(maxArea, area);
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		int[][] d1 = { { 9, 3 }, { 8, 6 } };
		System.out.println(maxArea(d1)); // 48

		int[][] d2 = { { 3, 4 }, { 4, 3 } };
		System.out.println(maxArea(d2)); // 12

		int[][] d3 = { { 6, 5 }, { 8, 6 }, { 2, 10 }, { 8, 1 }, { 9, 2 }, { 3, 5 }, { 3, 5 } };
		System.out.println(maxArea(d3)); // 20
	}
}
