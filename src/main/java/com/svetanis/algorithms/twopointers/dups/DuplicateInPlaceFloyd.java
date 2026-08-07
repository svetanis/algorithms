package com.svetanis.algorithms.twopointers.dups;

// 287. Find the Duplicate Number

// Given an array of integers nums containing n + 1 integers
// where each integer is in the range [1, n] inclusive.

// There is only one repeated number in nums, return this repeated number.

// You must solve the problem without modifying
// the array nums and using only constant extra space.

// 1 <= n <= 105
// nums.length == n + 1
// 1 <= nums[i] <= n
// All the integers in nums appear only once except
// for precisely one integer which appears two or more times.

// Read the array as a linked list: node i points to node a[i].
// Every node has exactly one outgoing edge, so following the edges
// from index 0 must eventually repeat a node, which means the walk
// enters a cycle. Two indices holding the same value point to the
// same node, so that value is the node the cycle is entered at.
// Index 0 is never inside the cycle, because every value is >= 1
// and so no edge leads back to 0. That is what makes 0 a safe start.

public final class DuplicateInPlaceFloyd {
	// Time Complexity: O(n)
	// Space Complexity: O(1), and the array is not modified

	public static int duplicate(int[] a) {
		int meeting = meetingPoint(a);
		return cycleEntrance(a, meeting);
	}

	// Phase 1: a fast walker moving two edges per step and a slow one
	// moving one edge per step must meet somewhere inside the cycle.
	private static int meetingPoint(int[] a) {
		int slow = a[0];
		int fast = a[a[0]];
		while (slow != fast) {
			slow = a[slow];
			fast = a[a[fast]];
		}
		return slow;
	}

	// Phase 2: restart one walker at index 0 and move both one edge per
	// step. The distance from 0 to the entrance equals the distance from
	// the meeting point to the entrance, so they meet exactly there.
	private static int cycleEntrance(int[] a, int meeting) {
		int slow = 0;
		int fast = meeting;
		while (slow != fast) {
			slow = a[slow];
			fast = a[fast];
		}
		return slow;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 4, 2, 2 };
		System.out.println(duplicate(a1)); // 2

		int[] a2 = { 3, 1, 3, 4, 2 };
		System.out.println(duplicate(a2)); // 3

		int[] a3 = { 3, 3, 3, 3, 3 };
		System.out.println(duplicate(a3)); // 3

		int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10 };
		System.out.println(duplicate(a4)); // 7

		int[] a5 = { 2, 2, 2, 2, 2 };
		System.out.println(duplicate(a5)); // 2

		int[] a6 = { 1, 1, 1, 1, 1 };
		System.out.println(duplicate(a6)); // 1
	}
}
