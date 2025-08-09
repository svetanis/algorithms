package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts

public final class MaxAreaOfCake {
	// Time Complexity: O(n log n)

	private static final int MOD = (int) 1e9 + 7;

	public static int maxArea(int h, int w, int[] hcuts, int[] vcuts) {
		Arrays.sort(hcuts);
		Arrays.sort(vcuts);
		int n = hcuts.length;
		int m = vcuts.length;
		long maxHDist = Math.max(hcuts[0], h - hcuts[n - 1]);
		long maxVDist = Math.max(vcuts[0], w - vcuts[m - 1]);
		for (int i = 1; i < n; i++) {
			maxHDist = Math.max(maxHDist, hcuts[i] - hcuts[i - 1]);
		}
		for (int i = 1; i < m; i++) {
			maxVDist = Math.max(maxVDist, vcuts[i] - vcuts[i - 1]);
		}
		long maxArea = (maxHDist * maxVDist) % MOD;
		return (int) maxArea;
	}

	public static void main(String[] args) {
		int[] hc1 = { 1, 2, 4 };
		int[] vc1 = { 1, 3 };
		System.out.println(maxArea(5, 4, hc1, vc1)); // 4

		int[] hc2 = { 3, 1 };
		int[] vc2 = { 1 };
		System.out.println(maxArea(5, 4, hc2, vc2)); // 6

		int[] hc3 = { 3 };
		int[] vc3 = { 3 };
		System.out.println(maxArea(5, 4, hc3, vc3)); // 9
	}
}
