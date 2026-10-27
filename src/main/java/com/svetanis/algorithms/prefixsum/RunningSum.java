package com.svetanis.algorithms.prefixsum;

// 1480. Running Sum of 1d Array
//
// Input: an array nums. Returns the running sum: entry i is nums[0] + nums[1] + ... + nums[i]. On
// LeetCode: 1 <= nums.length <= 1000, -10^6 <= nums[i] <= 10^6, so a total stays within 10^9.
//
// The one idea: each total is the one before it plus one more element. runningSum overwrites nums
// in place, left to right: when nums[i] is reached, nums[i - 1] already holds the total up to
// i - 1, so nums[i] += nums[i - 1] makes it the total up to i. The caller's array is changed and
// returned. prefix builds the same totals into a new array and leaves nums alone -- except when
// nums has 0 or 1 elements, where it returns nums itself (the totals equal the elements).
//
// Time: O(n) -- one pass, in both methods.
// Space: O(1) extra for runningSum -- it writes into nums; O(n) for prefix -- the new array.
//
// Siblings: PrefixSumArray is the same build as prefix, without the early return for n <= 1.

public final class RunningSum {

	public static int[] runningSum(int[] nums) {
		int n = nums.length;
		for (int i = 1; i < n; i++) {
			nums[i] += nums[i - 1]; // IN PLACE: nums[i - 1] already holds the total up to i - 1
		}
		return nums; // the caller's array, now holding the totals
	}

	public static int[] prefix(int[] nums) {
		int n = nums.length;
		if (n <= 1) {
			return nums; // SHORT: the totals are the elements; the caller's own array comes back
		}
		int[] prefix = new int[n];
		prefix[0] = nums[0]; // FIRST total: nums[0] alone
		for (int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] + nums[i]; // EXTEND: the total before, plus nums[i]
		}
		return prefix;
	}

	// PRINT: each value followed by a space, then a line break
	private static void print(int[] a) {
		for (int value : a) {
			System.out.printf("%d ", value);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 3, 4 };
		print(runningSum(nums1)); // 1 3 6 10

		int[] nums2 = { 1, 1, 1, 1, 1 };
		print(runningSum(nums2)); // 1 2 3 4 5

		int[] nums3 = { 3, 1, 2, 10, 1 };
		print(runningSum(nums3)); // 3 4 6 16 17

		int[] nums4 = { 3, 1, 2, 10, 1 };
		print(prefix(nums4)); // 3 4 6 16 17
	}
}
