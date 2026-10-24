package com.svetanis.algorithms.greedy;

// CSES: Increasing Array
//
// Input: a -- an array of n integers. One move adds 1 to any one element. Returns the fewest moves
// that make the array non-decreasing: every element at least as large as the one before it. On
// CSES: 1 <= n <= 2 * 10^5, 1 <= a[i] <= 10^9.
//
// The one idea: walk left to right. An element smaller than the one before it must be raised to
// at least that value, and raising it any higher costs more now and only forces the elements
// after it higher as well. So it is raised exactly to the value before it. The elements to its
// left are already in order and never change again, so no move is ever undone.
//
// Time: O(n) -- one pass.
// Space: O(1) -- the raised values are written into the caller's array, which comes back
// non-decreasing.
// The answer can reach (2 * 10^5 - 1) * (10^9 - 1), about 2 * 10^14, so it is a long.

public final class IncreasingArray {

	public static long minNumOfMoves(long[] a) {
		long count = 0;
		for (int i = 1; i < a.length; i++) { // WALK: left to right
			if (a[i - 1] > a[i]) { // CHECK: smaller than the element before it
				count += (a[i - 1] - a[i]); // COUNT: one move per unit raised
				a[i] = a[i - 1]; // RAISE it exactly to the element before it
			}
		}
		return count; // RETURN: moves
	}

	public static void main(String[] args) {
		long[] a1 = { 3, 2, 5, 1, 7 };
		System.out.println(minNumOfMoves(a1)); // 5

		long[] a2 = { 1, 2, 3 };
		System.out.println(minNumOfMoves(a2)); // 0
	}
}
