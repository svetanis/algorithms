package com.svetanis.algorithms.prefixsum;

// 2256. Minimum Average Difference
//
// Input: an array nums of n integers. The average difference at index i is the absolute difference
// between the average of the first i + 1 elements and the average of the last n - i - 1 elements,
// each average rounded down to a whole number; the average of no elements is 0. Returns the index
// with the smallest average difference, and the smallest such index on a tie. On LeetCode:
// 1 <= n <= 10^5, 0 <= nums[i] <= 10^5.
//
// The one idea: at index i the array is cut in two -- a left part, nums[0..i], and a right part,
// everything after i. The left part's total is a running sum that grows by nums[i]; the right
// part's total is the whole array's total minus the left part's, so it shrinks by the same nums[i].
// Both totals reach 10^5 * 10^5 = 10^10, past the int range, so both are long. Dividing each total
// by its part's size gives the two rounded-down averages; the right part is empty at the last
// index, and its average is then 0 by the rule above.
//
// Time: O(n) -- one pass for the whole total, one for the cuts.
// Space: O(1) -- two running totals and the best so far.
//
// Siblings: none.

public final class MinAverageDifference {

	public static int minAverageDiff(int[] nums) {
		long prefix = 0; // LEFT TOTAL: nums[0..i]
		long suffix = suffix(nums); // RIGHT TOTAL: starts as the whole array
		long min = Long.MAX_VALUE;
		int n = nums.length;
		int index = 0;
		for (int i = 0; i < n; i++) { // CUT after index i
			int num = nums[i];
			prefix += num; // MOVE nums[i] from the right part to the left part
			suffix -= num;
			long left = prefix / (i + 1); // AVERAGE of i + 1 elements, rounded down
			int size = n - 1 - i;
			long right = size == 0 ? 0 : suffix / size; // EMPTY right part: average 0
			long diff = Math.abs(left - right);
			if (diff < min) { // STRICT: a tie keeps the smaller index
				min = diff;
				index = i;
			}
		}
		return index;
	}

	// the total of the whole array, as a long
	private static long suffix(int[] nums) {
		long sum = 0;
		for (int num : nums) {
			sum += num; // TOTAL
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