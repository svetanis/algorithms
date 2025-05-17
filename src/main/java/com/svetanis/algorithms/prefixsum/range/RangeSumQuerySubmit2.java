package com.svetanis.algorithms.prefixsum.range;

// 303. Range Sum Query - Immutable

public final class RangeSumQuerySubmit2 {
	// Query Time Complexity: O(n)
	// Space Complexity: O(n)

	public RangeSumQuerySubmit2(int[] nums) {
		this.prefix = prefix(nums);
	}

	private int[] prefix;

	public int sumRange(int left, int right) {
		if (left == 0) {
			return prefix[right];
		}
		return prefix[right] - prefix[left - 1];
	}

	private static int[] prefix(int[] nums) {
		int n = nums.length;
		int[] prefix = new int[n];
		prefix[0] = nums[0];
		for (int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] + nums[i];
		}
		return prefix;
	}

	public static void main(String[] args) {
		int[] a = { -2, 0, 3, -5, 2, -1 };
		RangeSumQuerySubmit2 rsq = new RangeSumQuerySubmit2(a);
		System.out.println(rsq.sumRange(0, 2)); // 1
		System.out.println(rsq.sumRange(2, 5)); // -1
		System.out.println(rsq.sumRange(0, 5)); // -3
	}
}
