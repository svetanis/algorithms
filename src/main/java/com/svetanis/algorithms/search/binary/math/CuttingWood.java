package com.svetanis.algorithms.search.binary.math;

import java.util.Arrays;

// Cutting Wood

// Determine the highest possible 
// setting of the woodcutter so that 
// it cuts at least k meters of wood

public final class CuttingWood {
	// Time Complexity: O(n log n)

	public static int binary(int[] heights, int k) {
		int low = 0;
		int high = Arrays.stream(heights).max().getAsInt();
		while (low < high) {
			int mid = low + (high - low) / 2 + 1;
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
