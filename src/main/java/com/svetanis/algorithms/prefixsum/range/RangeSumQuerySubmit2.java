package com.svetanis.algorithms.prefixsum.range;

// 303. Range Sum Query - Immutable
//
// Input: an array nums, given once to the constructor, then queries sumRange(left, right), each
// returning nums[left] + ... + nums[right]. On LeetCode: 1 <= nums.length <= 10^4,
// -10^5 <= nums[i] <= 10^5, 0 <= left <= right < nums.length, at most 10^4 queries; every total
// fits in an int. An empty array is outside that range and throws ArrayIndexOutOfBoundsException
// in the constructor, at prefix[0] = nums[0].
//
// The one idea: prefix[i] holds the total up to and including index i, nums[0] + ... + nums[i].
// The range is the total up to right minus the total of everything before left:
// prefix[right] - prefix[left - 1]. Nothing comes before index 0, so left == 0 is its own case,
// prefix[right]. The array is built once, in the constructor, and every query after that is one
// subtraction.
//
// Time: O(n) once, in the constructor; O(1) per query -- at most two lookups.
// Space: O(n) -- the prefix array.
//
// Siblings: RangeSumQuerySubmit puts a 0 in front -- prefix[i] is the total of the first i
// elements -- so it needs no left == 0 case and accepts an empty array. RangeSumQuery and
// RangeSumQueries ask the same question as static methods that rebuild the array on every call.

public final class RangeSumQuerySubmit2 {

	public RangeSumQuerySubmit2(int[] nums) {
		this.prefix = prefix(nums); // BUILD once
	}

	private int[] prefix;

	public int sumRange(int left, int right) {
		if (left == 0) {
			return prefix[right]; // FROM 0: nothing before index 0 to take away
		}
		return prefix[right] - prefix[left - 1]; // RANGE: up to right, minus everything before left
	}

	private static int[] prefix(int[] nums) {
		int n = nums.length;
		int[] prefix = new int[n];
		prefix[0] = nums[0]; // FIRST total: nums[0] alone
		for (int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] + nums[i]; // EXTEND: the total before, plus nums[i]
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
