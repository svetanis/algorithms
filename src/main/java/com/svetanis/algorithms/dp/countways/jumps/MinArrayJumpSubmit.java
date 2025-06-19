package com.svetanis.algorithms.dp.countways.jumps;

// 45. Jump Game II

// Given an array of positive numbers,
// where each element represents the max number of jumps
// that can be made forward from that element,
// write a program to find the minimum number of jumps
// needed to reach the end of the array
// (starting from the first element).
// If an element is 0,
// then we cannot move through that element.

public final class MinArrayJumpSubmit {
	// Time Complexity: O(n)

	public static int count(int[] a) {
		int n = a.length;
		int jumps = 0; // jumps counter
		int jump = 0; // max reach for current position
		int max = 0; // max reach of the last jump
		for (int start = 0; start < n - 1; start++) {
			jump = Math.max(jump, start + a[start]);
			if (max == start) {
				jumps++;
				max = jump;
				if (jump >= n - 1) {
					break;
				}
			}
		}
		return jumps;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 1, 1, 4 };
		System.out.println(count(a1)); // 3

		int[] a2 = { 1, 1, 3, 6, 9, 3, 0, 1, 3 };
		System.out.println(count(a2)); // 4

		int[] a3 = { 2, 3, 1, 1, 4 };
		System.out.println(count(a3)); // 2

		int[] a4 = { 2, 3, 0, 1, 4 };
		System.out.println(count(a4)); // 2
	}
}
