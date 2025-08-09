package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 1029. Two City Scheduling

public final class TwoCityScheduling {
	// Time Complexity: O(n log n)

	public static int tcs(int[][] costs) {
		Arrays.sort(costs, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));
		int n = costs.length / 2;
		int total = 0;
		for (int i = 0; i < n; i++) {
			total += costs[i][0];
			total += costs[i + n][1];
		}
		return total;
	}

	public static void main(String[] args) {
		int[][] costs1 = { { 10, 20 }, { 30, 200 }, { 400, 50 }, { 30, 20 } };
		System.out.println(tcs(costs1)); // 110

		int[][] costs2 = { { 259, 770 }, { 448, 54 }, { 926, 667 }, { 184, 139 }, { 840, 118 }, { 577, 469 } };
		System.out.println(tcs(costs2)); // 1859

		int[][] costs3 = { { 515, 563 }, { 451, 713 }, { 537, 709 }, { 343, 819 }, { 855, 779 }, { 457, 60 }, { 650, 359 },
				{ 631, 42 } };
		System.out.println(tcs(costs3)); // 3086
	}
}
