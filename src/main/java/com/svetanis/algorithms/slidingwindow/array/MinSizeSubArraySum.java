package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// 209. Minimum Size Subarray Sum

// Given an array of positive integers and a number ‘S,’ 
// find the length of the smallest contiguous subarray 
// whose sum is greater than or equal to ‘S’. 
// Return 0 if no such subarray exists.
// Takes the numbers (int[] or List; 0s are fine, negatives are not) and
// the target S >= 1. An empty input returns 0.

// With no negative numbers, adding a number never lowers the sum and
// removing one never raises it. So for each right end, the window is
// shrunk while it STILL reaches the target, and measured inside that
// loop, before the left number leaves: that is the last moment the
// window is known to reach the target.

// Two overloads, the same loop, with the two ways of saying "nothing
// found": the int[] one starts min at n + 1, a length no window can
// have, and tests min <= n; the List one starts at Integer.MAX_VALUE
// and tests for it. Each start goes with its own test.

// Time: O(n) -- each index enters the window once and leaves at most once
// Space: O(1) -- the running sum, the best length and two indices

// Siblings:
//   MinSizeSubArraySumBinarySearch -- the follow-up, O(n log n): the
//     same fact read as "the running totals never go down", and a
//     binary search for each start's first end.
//   todo.slidingwindow.MinSizeSubArraySumAtLeastK (862) -- the same
//     question with negative numbers allowed; a shrinking window no
//     longer works, and a deque over prefix sums answers it.
//   LongestSubArrSumAtMostK -- the mirror: the LONGEST subarray whose
//     sum stays at most k, measured after the shrink.

public final class MinSizeSubArraySum {
	// Time complexity: O(n)

	public static int minSum(int[] a, int target) {
		int sum = 0;
		int left = 0;
		int n = a.length;
		int min = n + 1;
		for (int right = 0; right < n; right++) {
			sum += a[right]; // ADD
			while (left < n && sum >= target) { // SHRINK: while it still reaches the target
				min = min(min, right - left + 1); // MEASURE: before the left number leaves
				sum -= a[left];
				left++;
			}
		}
		return min <= n ? min : 0; // RETURN: n + 1 was never beaten, so no window
	}

	public static int minSum(int target, List<Integer> list) {
		int sum = 0;
		int left = 0;
		int n = list.size();
		int min = Integer.MAX_VALUE;
		for (int right = 0; right < n; right++) {
			sum += list.get(right); // ADD
			while (sum >= target && left < n) { // SHRINK: while it still reaches the target
				min = min(min, right - left + 1); // MEASURE: before the left number leaves
				sum -= list.get(left);
				left++;
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min; // RETURN: MAX_VALUE was never beaten, so no window
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, 1, 2, 4, 3 };
		int[] a2 = { 1, 4, 4 };
		int[] a3 = { 1, 1, 1, 1, 1, 1, 1, 1 };
		System.out.println(minSum(a1, 7)); // 2
		System.out.println(minSum(a2, 4)); // 1
		System.out.println(minSum(a3, 11)); // 0

		System.out.println(minSum(51, asList(1, 4, 45, 6, 0, 19))); // 2 -- 45 + 6
		System.out.println(minSum(9, asList(1, 10, 5, 2, 7))); // 1
		System.out.println(minSum(280, asList(1, 11, 100, 1, 0, 200, 3, 2, 1, 250))); // 4
		System.out.println(minSum(10, asList(1, 4, 1, 7, 3, 0, 2, 5))); // 2
	}
}
