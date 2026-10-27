package com.svetanis.algorithms.prefixsum;

// Prefix sum array -- not a LeetCode problem; a teaching helper for this package
//
// Input: a non-empty array a. Returns prefix, where prefix[i] is a[0] + a[1] + ... + a[i] -- the
// total up to and including index i, so prefix has the same length as a. An empty array throws
// ArrayIndexOutOfBoundsException at prefix[0] = a[0]. The totals are ints and wrap past 2^31.
//
// The one idea: each total is the one before it plus one more element, prefix[i] =
// prefix[i - 1] + a[i], so a single left-to-right pass builds them all. A range a[l..r] then sums
// to prefix[r] - prefix[l - 1], with l = 0 as its own case (prefix[r]), since nothing comes before
// index 0. The other convention used in this package puts a 0 in front -- an array of n + 1 with
// prefix[i] the total of a[0 .. i - 1] -- which removes that case.
//
// Time: O(n) -- one pass.
// Space: O(n) -- the returned array.
//
// Siblings: SuffixSumArray builds the same totals from the right. PrefixProductArray is this build
// with * in place of +. RunningSum (1480) is the same build, also done in place. RangeSumQueries
// calls prefixSum.

public final class PrefixSumArray {

	public static int[] prefixSum(int[] a) {
		int n = a.length;
		int[] prefix = new int[n];
		prefix[0] = a[0]; // FIRST total: a[0] alone
		for (int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] + a[i]; // EXTEND: the total before, plus a[i]
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
		int[] a1 = { 10, 20, 10, 5, 15 };
		print(prefixSum(a1)); // 10 30 40 45 60

		int[] a2 = { 10, 4, 16, 20 };
		print(prefixSum(a2)); // 10 14 30 50
	}
}
