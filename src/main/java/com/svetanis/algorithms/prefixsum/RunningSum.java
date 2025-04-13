package com.svetanis.algorithms.prefixsum;

import com.svetanis.java.base.utils.Print;

// 1480. Running Sum of 1d Array

public final class RunningSum {
	// Time complexity: O(n)

	public static int[] runningSum(int[] nums) {
		int n = nums.length;
		for (int i = 1; i < n; i++) {
			nums[i] += nums[i - 1];
		}
		return nums;
	}

	public static int[] prefix(int[] nums) {
		int n = nums.length;
		if (n <= 1) {
			return nums;
		}
		int[] prefix = new int[n];
		prefix[0] = nums[0];
		for (int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] + nums[i];
		}
		return prefix;
	}

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 3, 4 };
		Print.print(runningSum(nums1)); // 1,3,6,10

		int[] nums2 = { 1, 1, 1, 1, 1 };
		Print.print(runningSum(nums2)); // 1,2,3,4,5

		int[] nums3 = { 3, 1, 2, 10, 1 };
		Print.print(runningSum(nums3)); // 3,4,6,16,17
	}
}
