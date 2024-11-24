package com.svetanis.algorithms.prefixsum;

// 303. Range Sum Query - Immutable

public final class RangeSumQuerySubmit {
	// Query Time Complexity: O(n)
	// Space Complexity: O(n)

	public RangeSumQuerySubmit(int[] nums) {
		this.prefix = prefix(nums);
	}

	private int[] prefix;

	public int sumRange(int left, int right) {
		return prefix[right + 1] - prefix[left];
	}

	private static int[] prefix(int[] nums) {
		int n = nums.length;
		int[] prefix = new int[n + 1];
		for (int i = 0; i < n; i++) {
			prefix[i + 1] = prefix[i] + nums[i];
		}
		return prefix;
	}

	public static void main(String[] args) {
		int[] a = { -2, 0, 3, -5, 2, -1 };
		RangeSumQuerySubmit rsq = new RangeSumQuerySubmit(a);
		System.out.println(rsq.sumRange(0, 2)); // 1
		System.out.println(rsq.sumRange(2, 5)); // -1
		System.out.println(rsq.sumRange(0, 5)); // -3
	}
}
