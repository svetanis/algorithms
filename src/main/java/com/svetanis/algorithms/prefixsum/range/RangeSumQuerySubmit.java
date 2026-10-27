package com.svetanis.algorithms.prefixsum.range;

// 303. Range Sum Query - Immutable
//
// Input: an array nums, given once to the constructor, then queries sumRange(left, right), each
// returning nums[left] + ... + nums[right]. On LeetCode: 1 <= nums.length <= 10^4,
// -10^5 <= nums[i] <= 10^5, 0 <= left <= right < nums.length, at most 10^4 queries; every total
// fits in an int.
//
// The one idea: prefix[i] holds the total of the first i elements, nums[0] + ... + nums[i - 1],
// and prefix[0] = 0 for no elements. The range is the total of the first right + 1 elements minus
// the total of the first left: prefix[right + 1] - prefix[left], and the leading 0 means left == 0
// needs no case of its own. The class exists to keep the array between queries: it is built once,
// in the constructor, and every query after that is one subtraction.
//
// Time: O(n) once, in the constructor; O(1) per query -- two lookups.
// Space: O(n) -- the prefix array of n + 1.
//
// Siblings: RangeSumQuerySubmit2 stores the inclusive totals, prefix[i] = nums[0] + ... + nums[i],
// so it needs a left == 0 case, and an empty array throws in its constructor where this one does
// not. RangeSumQuery and RangeSumQueries ask the same question as static methods that rebuild the
// array on every call.

public final class RangeSumQuerySubmit {

	public RangeSumQuerySubmit(int[] nums) {
		this.prefix = prefix(nums); // BUILD once
	}

	private int[] prefix;

	public int sumRange(int left, int right) {
		return prefix[right + 1] - prefix[left]; // RANGE: first right + 1 elements, minus first left
	}

	private static int[] prefix(int[] nums) {
		int n = nums.length;
		int[] prefix = new int[n + 1]; // prefix[0] = 0: no elements
		for (int i = 0; i < n; i++) {
			prefix[i + 1] = prefix[i] + nums[i]; // EXTEND: one more element
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
