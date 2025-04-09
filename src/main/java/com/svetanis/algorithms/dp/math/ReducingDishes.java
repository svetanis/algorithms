package com.svetanis.algorithms.dp.math;

import java.util.Arrays;

// 1402. Reducing Dishes

public final class ReducingDishes {
	// Time Complexity: O(n log n)

	public static int maxSatisfaction(int[] satisfaction) {
		Arrays.sort(satisfaction);
		int ltc = 0;
		int max = 0;
		for (int i = satisfaction.length - 1; i >= 0; i--) {
			ltc += satisfaction[i];
			if (ltc <= 0) {
				break;
			}
			max += ltc;
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { -1, -8, 0, 5, -9 };
		System.out.println(maxSatisfaction(a1)); // 14

		int[] a2 = { 4, 3, 2 };
		System.out.println(maxSatisfaction(a2)); // 20

		int[] a3 = { -1, -4, -5 };
		System.out.println(maxSatisfaction(a3)); // 0
	}
}
