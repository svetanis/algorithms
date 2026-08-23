package com.svetanis.algorithms.search.binary.math;

import java.util.Arrays;

// Cutting Wood

// You are given an array representing the heights of trees, and an int k,
// the total length of wood that needs to be cut.

// The woodcutting machine is set to a certain height H. It cuts off the top
// part of every tree taller than H; trees shorter than H are untouched.

// Determine the highest possible setting H so that the machine cuts at least
// k meters of wood.

// Constraints:
//  - H can never be set higher than the tallest tree in the array.
//  - It is always possible to obtain at least k meters of wood.
//  - There is at least one tree.

// Shape: maximize / last true. feasible(H) = "cutting at H yields >= k meters",
// which is true for small H and false for large H, so the loop keeps mid on the
// left and the mid rounds up.

public final class CuttingWood {
	// Time Complexity: O(n log n)

	public static int binary(int[] heights, int k) {
		int low = 0;
		int high = Arrays.stream(heights).max().getAsInt();
		while (low < high) {
			// low starts at 0 and high is a height, so the range can span the whole int
			// domain and (high - low + 1) would overflow. >>> recovers the wrapped sum.
			int mid = (low + high + 1) >>> 1;
			if (cutsEnoughWood(heights, mid, k)) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return high;
	}

	private static boolean cutsEnoughWood(int[] heights, int h, int k) {
		int total = 0;
		for (int height : heights) {
			if (height > h) {
				total += (height - h);
			}
		}
		return total >= k;
	}

	public static void main(String[] args) {
		int[] a = { 2, 6, 3, 8 };
		System.out.println(binary(a, 7)); // 3
	}
}
