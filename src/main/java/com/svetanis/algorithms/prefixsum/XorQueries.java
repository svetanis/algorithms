package com.svetanis.algorithms.prefixsum;

// 1310. XOR Queries of a Subarray
//
// Input: an array a (arr on LeetCode) and queries, each [left, right]. Returns, per query, the
// XOR of a[left] ^ a[left + 1] ^ ... ^ a[right]. On LeetCode: 1 <= a.length, queries.length <=
// 3 * 10^4, 1 <= a[i] <= 10^9, 0 <= left <= right < a.length.
//
// The one idea: a prefix sum with ^ in place of +. prefix[i] holds the XOR of the first i
// elements, a[0] ^ ... ^ a[i - 1], and prefix[0] = 0 for no elements. XOR undoes itself -- x ^ x
// is 0 -- so in prefix[right + 1] ^ prefix[left] the elements before left appear twice and cancel,
// leaving exactly a[left..right]. That plays the part subtraction plays for sums.
//
// Time: O(n + m) -- one pass to build, then constant work for each of the m queries.
// Space: O(n + m) -- the prefix array of n + 1 and the m answers.
//
// Siblings: none.

public final class XorQueries {

	public static int[] xorQueries(int[] a, int[][] queries) {
		int m = queries.length;
		int[] prefix = prefixXor(a); // BUILD once
		int[] result = new int[m];
		for (int i = 0; i < m; i++) {
			int left = queries[i][0];
			int right = queries[i][1];
			result[i] = prefix[right + 1] ^ prefix[left]; // CANCEL: the shared front appears twice
		}
		return result;
	}

	public static int[] prefixXor(int[] a) {
		int n = a.length;
		int[] prefix = new int[n + 1]; // prefix[0] = 0: no elements
		for (int i = 1; i <= n; i++) {
			prefix[i] = prefix[i - 1] ^ a[i - 1]; // EXTEND: one more element
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
		int[] a1 = { 1, 3, 4, 8 };
		int[][] q1 = { { 0, 1 }, { 1, 2 }, { 0, 3 }, { 3, 3 } };
		print(xorQueries(a1, q1)); // 2 7 14 8

		int[] a2 = { 4, 8, 2, 10 };
		int[][] q2 = { { 2, 3 }, { 1, 3 }, { 0, 0 }, { 0, 3 } };
		print(xorQueries(a2, q2)); // 8 0 4 4
	}
}
