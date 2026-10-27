package com.svetanis.algorithms.prefixsum;

// 1413. Minimum Value to Get Positive Step by Step Sum
//
// Input: an array a (nums on LeetCode). Start from a positive whole number, the start value, and
// add the elements of a one at a time, left to right. Returns the smallest positive start value
// for which the total never drops below 1 at any step. On LeetCode: 1 <= a.length <= 100,
// -100 <= a[i] <= 100.
//
// The one idea: the total after each step is (start value) + (running sum of a so far). Only the
// lowest running sum, min, matters: if the total is at least 1 there, it is at least 1
// everywhere. So the start value must be at least 1 - min. The start value must also be positive,
// so when every running sum is already above 0, the answer is 1.
//
// Time: O(n) -- one pass.
// Space: O(1) -- the running sum and its lowest value.
//
// Siblings: none.

public final class MinStartValue {

	public static int minStartValue(int[] a) {
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for (int num : a) {
			sum += num; // RUNNING SUM after this step
			min = Math.min(min, sum); // LOWEST running sum so far
		}
		return Math.max(1, 1 - min); // LIFT the lowest total to 1, and never start below 1
	}

	public static void main(String[] args) {
		int[] a1 = { -3, 2, -3, 4, 2 };
		System.out.println(minStartValue(a1)); // 5

		int[] a2 = { 1, 2 };
		System.out.println(minStartValue(a2)); // 1

		int[] a3 = { 1, -2, -3 };
		System.out.println(minStartValue(a3)); // 5
	}
}
