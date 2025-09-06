package com.svetanis.algorithms.math.geometry;

import java.util.Arrays;

// 976. Largest Perimeter Triangle

public class LargestPerimeterTriangle {
	// Time Complexity: O(n log n)
	// Space Complexity: O(1)

	public static int largestPerimeter(int[] a) {
		Arrays.sort(a);
		for (int i = a.length - 1; i >= 2; i--) {
			int sum = a[i - 1] + a[i - 2];
			if (sum > a[i]) {
				int perimeter = sum + a[i];
				return perimeter;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 2 };
		System.out.println(largestPerimeter(a1)); // 5
		int[] a2 = { 1, 2, 1, 10 };
		System.out.println(largestPerimeter(a2)); // 0
	}
}
