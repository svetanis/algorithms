package com.svetanis.algorithms.math;

import java.util.List;

// 1276. Number of Burgers with No Waste of Ingredients

// 4 * jumbo + 2 * small = tomatoSlices
// jumbo + small = cheeseSlices

public final class CountBurgers {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static List<Integer> burgers(int tslices, int cslices) {
		int tdiff = 4 * cslices - tslices;
		int small = tdiff / 2;
		int jumbo = cslices - small;
		if (tdiff % 2 != 0 || small < 0 || jumbo < 0) {
			return List.of();
		}
		return List.of(jumbo, small);
	}

	public static void main(String[] args) {
		System.out.println(burgers(16, 7)); // 1 6
		System.out.println(burgers(17, 4));
		System.out.println(burgers(4, 17));
	}
}