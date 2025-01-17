package com.svetanis.algorithms.prefixsum;

// 42. Trapping Rain Water

// Given n non-negative integers representing an elevation map 
// where the width of each bar is 1, 
// compute how much water it is able to trap after raining.

public final class TrappingWater {
	// Time Complexity: O(n)

	public static int trapWater(int[] a) {
		int total = 0;
		int[] left = left(a);
		int[] right = right(a);
		for (int i = 0; i < a.length; i++) {
			total += Math.min(left[i], right[i]) - a[i];
		}
		return total;
	}

	// left[i] contains height of tallest bar
	// to the left of i'th bar including itself
	private static int[] left(int[] a) {
		int n = a.length;
		int[] left = new int[n];
		left[0] = a[0];
		for (int i = 1; i < n; i++) {
			left[i] = Math.max(left[i - 1], a[i]);
		}
		return left;
	}

	// right[i] contains height of tallest bar
	// to the right of i-th bar including itself
	private static int[] right(int[] a) {
		int n = a.length;
		int[] right = new int[n];
		right[n - 1] = a[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			right[i] = Math.max(right[i + 1], a[i]);
		}
		return right;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 0, 2 };
		System.out.println(trapWater(a1));

		int[] a2 = { 3, 0, 0, 2, 0, 4 };
		System.out.println(trapWater(a2));

		int[] a3 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(trapWater(a3));

		int[] a4 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		System.out.println(trapWater(a4));

		int[] a5 = { 4, 2, 0, 3, 2, 5 };
		System.out.println(trapWater(a5)); // 9

		int[] a6 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(trapWater(a6)); // 6
	}
}