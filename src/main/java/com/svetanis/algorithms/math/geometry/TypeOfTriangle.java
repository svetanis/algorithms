package com.svetanis.algorithms.math.geometry;

import java.util.Arrays;

// 3024. Type of Triangle

public class TypeOfTriangle {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static String triangleType(int[] nums) {
		int a = nums[0];
		int b = nums[1];
		int c = nums[2];
		if (a + b <= c || a + c <= b || b + c <= a) {
			return "none";
		}
		if (a == b && b == c) {
			return "equilateral";
		}
		if (a == b || b == c || a == c) {
			return "isosceles";
		}
		return "scalene";
	}

	public static String triangleTypeSorted(int[] nums) {
		Arrays.sort(nums);
		int a = nums[0];
		int b = nums[1];
		int c = nums[2];
		if (a + b <= c) {
			return "none";
		}
		if (a == c) {
			return "equilateral";
		}
		if (a == b || b == c) {
			return "isosceles";
		}
		return "scalene";
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 3, 3 };
		System.out.println(triangleType(a1)); // equilateral

		int[] a2 = { 3, 4, 5 };
		System.out.println(triangleType(a2)); // scalene
	}
}
