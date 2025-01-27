package com.svetanis.algorithms.prefixsum;

// 42. Trapping Rain Water

// Given n non-negative integers representing an elevation map 
// where the width of each bar is 1, 
// compute how much water it is able to trap after raining

public final class TrappingWaterSpaceEfficient {
	// Time Complexity: O(n)

	public static int trapWater(int[] a) {
		int n = a.length;
		int total = 0;
		int left = 0;
		int right = n - 1;
		int maxLeft = a[left];
		int maxRight = a[right];
		while (left < right) {
			if (a[left] <= a[right]) {
				maxLeft = Math.max(maxLeft, a[left]);
				total += maxLeft - a[left];
				left++;
			} else {
				maxRight = Math.max(maxRight, a[right]);
				total += maxRight - a[right];
				right--;
			}
		}
		return total;
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
	}
}