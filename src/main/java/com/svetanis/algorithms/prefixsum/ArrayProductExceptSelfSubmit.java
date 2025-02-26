package com.svetanis.algorithms.prefixsum;

import com.svetanis.java.base.utils.Print;

// 238. Product of Array Except Self

// given an integer array nums,
// return a product array such that
// product[i] equals to the 
// product of all the elements
// of the array except nums[i]

public final class ArrayProductExceptSelfSubmit {
	// Time Complexity: O(n)

	public static int[] pes(int[] nums) {
		int n = nums.length;
		int[] output = new int[n];
		output[0] = 1;
		for (int i = 1; i < n; i++) {
			output[i] = output[i - 1] * nums[i - 1];
		}
		int right = 1;
		for (int i = n - 1; i >= 0; i--) {
			output[i] *= right;
			right *= nums[i];
		}
		return output;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4 };
		Print.print(pes(a1)); // [24,12,8,6]
		int[] a2 = { -1, 1, 0, -3, 3 };
		Print.print(pes(a2)); // [0,0,9,0,0]
	}
}