package com.svetanis.algorithms.prefixsum;

// Suffix sum array -- not a LeetCode problem; a teaching helper for this package
//
// Input: a non-empty array a. Returns suffix, where suffix[i] is a[i] + a[i + 1] + ... + a[n - 1]
// -- the total from index i to the end, so suffix has the same length as a. An empty array throws
// ArrayIndexOutOfBoundsException at suffix[n - 1] = a[n - 1]. The totals are ints and wrap past
// 2^31.
//
// The one idea: the mirror of a prefix sum. Each total is the one after it plus one more element,
// suffix[i] = suffix[i + 1] + a[i], so a single right-to-left pass builds them all, starting from
// the last element alone.
//
// Time: O(n) -- one pass.
// Space: O(n) -- the returned array.
//
// Siblings: PrefixSumArray builds the same totals from the left. SuffixProductArray is this build
// with * in place of +.

public final class SuffixSumArray {

	public static int[] suffixSum(int[] a) {
		int n = a.length;
		int[] suffix = new int[n];
		suffix[n - 1] = a[n - 1]; // LAST total: a[n - 1] alone
		for (int i = n - 2; i >= 0; i--) { // WALK right to left
			suffix[i] = suffix[i + 1] + a[i]; // EXTEND: the total after, plus a[i]
		}
		return suffix;
	}

	// PRINT: each value followed by a space, then a line break
	private static void print(int[] a) {
		for (int value : a) {
			System.out.printf("%d ", value);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 20, 10, 5, 15 };
		print(suffixSum(a1)); // 60 50 30 20 15

		int[] a2 = { 10, 4, 16, 20 };
		print(suffixSum(a2)); // 50 40 36 20
	}
}
