package com.svetanis.algorithms.prefixsum;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 907. Sum of Subarray Minimums
//
// Input: an array a (arr on LeetCode). A subarray is a run of neighbouring elements, at least one
// long. Returns the sum of the smallest element of every subarray, modulo 10^9 + 7. On LeetCode:
// 1 <= a.length <= 3 * 10^4, 1 <= a[i] <= 3 * 10^4.
//
// The one idea: instead of visiting every subarray, ask of each element how many subarrays it is
// the smallest of, and add a[i] times that count. left[i] holds the nearest index to the left of i
// with a value smaller than a[i] (-1 if none), and right[i] the nearest index to the right with a
// value smaller or equal (n if none). Element i is the smallest of exactly the subarrays that start
// after left[i] and at or before i, and end at or after i and before right[i]: (i - left[i]) starts
// times (right[i] - i) ends. Strict on one side and not on the other, so a subarray whose smallest
// value appears twice is counted once, at its rightmost copy. Each bound array is filled by one walk
// with a stack of indices whose values increase from bottom to top: an index pops off when a value
// that is no larger arrives, and whatever is left on top is the nearest smaller one. Despite the
// package, no prefix sum is used.
//
// Each count is at most 15000 * 15001, which fits in an int; count * a[i] is a long.
//
// Time: O(n) -- each index is pushed and popped at most once per walk.
// Space: O(n) -- the two bound arrays and the stack.
//
// Siblings: none for this problem. todo.monotonicstack.SumOfSubarrayRanges (2104) uses the same
// count per element for the smallest and the largest values.

public final class SumSubArrMins {

	private static final int MOD = (int) 1e9 + 7;

	public int sumSubArrMins(int[] a) {
		int n = a.length;
		int[] left = leftPrefix(a); // BOUND: nearest smaller to the left
		int[] right = rightPrefix(a); // BOUND: nearest smaller-or-equal to the right
		long sum = 0;
		for (int i = 0; i < n; i++) {
			long count = (i - left[i]) * (right[i] - i) % MOD; // COUNT: subarrays where a[i] is the smallest
			sum = (sum + count * a[i]) % MOD; // ADD a[i] once per such subarray
		}
		return (int) sum;
	}

	private static int[] leftPrefix(int[] a) {
		int n = a.length;
		int[] left = new int[n];
		Arrays.fill(left, -1); // NONE smaller to the left: -1
		Deque<Integer> dq = new ArrayDeque<>(); // STACK of indices, values increasing upward
		for (int i = 0; i < n; i++) { // WALK left to right
			while (!dq.isEmpty() && a[dq.peek()] >= a[i]) {
				dq.pop(); // POP: no smaller than a[i], so never a bound for i or anything after it
			}
			if (!dq.isEmpty()) {
				left[i] = dq.peek(); // BOUND: the nearest strictly smaller value
			}
			dq.push(i);
		}
		return left;
	}

	private static int[] rightPrefix(int[] a) {
		int n = a.length;
		int[] right = new int[n];
		Arrays.fill(right, n); // NONE to the right: n
		Deque<Integer> dq = new ArrayDeque<>(); // STACK of indices, values increasing upward
		for (int i = n - 1; i >= 0; i--) { // WALK right to left
			while (!dq.isEmpty() && a[dq.peek()] > a[i]) {
				dq.pop(); // POP: strictly larger, so equal values stay as a bound
			}
			if (!dq.isEmpty()) {
				right[i] = dq.peek(); // BOUND: the nearest smaller-or-equal value
			}
			dq.push(i);
		}
		return right;
	}

	public static void main(String[] args) {
		SumSubArrMins ssm = new SumSubArrMins();
		int[] a1 = { 3, 1, 2, 4 };
		System.out.println(ssm.sumSubArrMins(a1)); // 17

		int[] a2 = { 11, 81, 94, 43, 3 };
		System.out.println(ssm.sumSubArrMins(a2)); // 444
	}
}
