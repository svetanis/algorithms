package com.svetanis.algorithms.prefixsum;

// 2256. Minimum Average Difference

public final class MinAverageDifference {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minAverageDiff(int[] nums) {
		long prefix = 0;
		long suffix = suffix(nums);
		long min = Long.MAX_VALUE;
		int n = nums.length;
		int index = 0;
		for (int i = 0; i < n; i++) {
			int num = nums[i];
			prefix += num;
			suffix -= num;
			long left = prefix / (i + 1);
			int size = n - 1 - i;
			long right = size == 0 ? 0 : suffix / size;
			long diff = Math.abs(left - right);
			if (diff < min) {
				min = diff;
				index = i;
			}
		}
		return index;
	}

	private static long suffix(int[] nums) {
		long sum = 0;
		for (int num : nums) {
			sum += num;
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 5, 3, 9, 5, 3 };
		System.out.println(minAverageDiff(a1)); // 3
		int[] a2 = { 0 };
		System.out.println(minAverageDiff(a2)); // 0
	}
}