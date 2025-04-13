package com.svetanis.algorithms.prefixsum;

import com.svetanis.java.base.utils.Print;

// 1310. XOR Queries of a Subarray

public final class XorQueries {
	// Time Complexity: O(n + m)
	// Space Complexity: O(n + m)

	public static int[] xorQueries(int[] a, int[][] queries) {
		int m = queries.length;
		int[] prefix = prefixXor(a);
		int[] result = new int[m];
		for (int i = 0; i < m; i++) {
			int left = queries[i][0];
			int right = queries[i][1];
			result[i] = prefix[right + 1] ^ prefix[left];
		}
		return result;
	}

	public static int[] prefixXor(int[] a) {
		int n = a.length;
		int[] prefix = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			prefix[i] = prefix[i - 1] ^ a[i - 1];
		}
		return prefix;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 4, 8 };
		int[][] q1 = { { 0, 1 }, { 1, 2 }, { 0, 3 }, { 3, 3 } };
		Print.print(xorQueries(a1, q1)); // 2,7,14,8

		int[] a2 = { 4, 8, 2, 10 };
		int[][] q2 = { { 2, 3 }, { 1, 3 }, { 0, 0 }, { 0, 3 } };
		Print.print(xorQueries(a2, q2)); // 8,0,4,4
	}
}
