package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 456. 132 Pattern
//
// Are there positions i < j < k with nums[i] < nums[k] < nums[j]? Low first, then high, then
// a middle value between them.
// nums is an int array; return true when such positions exist, false otherwise.
//
// Scan from the right. When nums[j] pops smaller values off the stack, each popped value has
// a larger value, nums[j], to its left, so it can be the middle value. Keep the largest such
// middle; any value further left that is below it is the low, and completes the pattern.

public final class Find132Pattern {
	// Time Complexity: O(n), every value is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static boolean find(int[] nums) {
		if (nums.length < 3) { // EARLY: fewer than three values cannot hold a pattern
			return false;
		}
		int middle = Integer.MIN_VALUE; // START: the largest value found with a larger value to its left
		Deque<Integer> stack = new ArrayDeque<>(); // values to the right, smallest on top
		for (int i = nums.length - 1; i >= 0; i--) { // WALK: right to left, nums[i] arrives
			if (nums[i] < middle) { // LOW: below the middle, so nums[i] completes the pattern
				return true; // FOUND: nums[i] is the low
			}
			while (!stack.isEmpty() && stack.peek() < nums[i]) { // HIGH: nums[i] is larger than the top
				middle = stack.pop(); // MIDDLE: popped smallest first, so the last one popped is the largest
			}
			stack.push(nums[i]); // PUSH: nums[i] may be the high for a value further left
		}
		return false; // RETURN: no low found below a middle
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4 };
		System.out.println(find(a1)); // false
		int[] a2 = { 3, 1, 4, 2 };
		System.out.println(find(a2)); // true
		int[] a3 = { -1, 3, 2, 0 };
		System.out.println(find(a3)); // true
		int[] a4 = { 1, 0, 1, -4, -3 };
		System.out.println(find(a4)); // false
	}
}
