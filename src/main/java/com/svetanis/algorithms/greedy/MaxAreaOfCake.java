package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
//
// Input: h and w -- the height and width of a rectangular cake; hcuts[i] -- the distance from the
// top edge to horizontal cut i; vcuts[j] -- the distance from the left edge to vertical cut j.
// Returns the area of the largest piece after every cut, modulo 10^9 + 7. On LeetCode:
// 2 <= h, w <= 10^9, 1 <= hcuts.length <= min(h - 1, 10^5), 1 <= vcuts.length <= min(w - 1, 10^5),
// 1 <= hcuts[i] < h, 1 <= vcuts[j] < w, the hcuts all different, the vcuts all different.
//
// The one idea: the horizontal cuts slice the cake into strips across, and the vertical cuts into
// strips down; every piece is one strip across meeting one strip down, and every pair meets. A
// piece's height depends only on the horizontal cuts and its width only on the vertical cuts, so
// the largest piece is the tallest strip times the widest strip. After sorting, a strip is the gap
// between two neighbouring cuts, and the edges of the cake count as cuts at 0 and h (0 and w).
//
// Time: O(n log n + m log m) -- n = hcuts.length, m = vcuts.length: the two sorts; each gap scan
// is one pass.
// Space: O(1) -- beyond the sorts, which work in place: both cut arrays come back sorted.
// Each gap is below 10^9, so the product is below 10^18 and is taken in a long before the modulo.

public final class MaxAreaOfCake {

	private static final int MOD = (int) 1e9 + 7;

	public static int maxArea(int h, int w, int[] hcuts, int[] vcuts) {
		Arrays.sort(hcuts); // SORT: neighbouring cuts become neighbours
		Arrays.sort(vcuts);
		int n = hcuts.length;
		int m = vcuts.length;
		long maxHDist = Math.max(hcuts[0], h - hcuts[n - 1]); // EDGES: the strips at the top and bottom
		long maxVDist = Math.max(vcuts[0], w - vcuts[m - 1]); // EDGES: the strips at the left and right
		for (int i = 1; i < n; i++) {
			maxHDist = Math.max(maxHDist, hcuts[i] - hcuts[i - 1]); // GAP between neighbouring cuts
		}
		for (int i = 1; i < m; i++) {
			maxVDist = Math.max(maxVDist, vcuts[i] - vcuts[i - 1]); // GAP between neighbouring cuts
		}
		long maxArea = (maxHDist * maxVDist) % MOD; // MULTIPLY in a long, then the modulo
		return (int) maxArea; // RETURN: below 10^9 + 7, fits an int
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
