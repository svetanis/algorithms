package com.svetanis.algorithms.prefixsum;

import static com.svetanis.java.base.utils.Print.print;

// 2055. Plates between candles

public final class PlatesBetweenCandles {
	// Query Time Complexity: O(n + q)
	// Space Complexity: O(n + q)

	public static int[] platesBetweenCandles(String s, int[][] queries) {
		int n = queries.length;
		int[] prefix = prefix(s);
		int[] left = left(s);
		int[] right = right(s);
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			int start = right[queries[i][0]];
			int end = left[queries[i][1]];
			if (start >= 0 && end >= 0 && start < end) {
				a[i] = prefix[end] - prefix[start + 1];
			}
		}
		return a;
	}

	private static int[] left(String s) {
		int n = s.length();
		int[] a = new int[n + 1];
		int prev = -1;
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '|') {
				prev = i;
			}
			a[i] = prev;
		}
		return a;
	}

	private static int[] right(String s) {
		int n = s.length();
		int[] a = new int[n + 1];
		int next = -1;
		for (int i = n - 1; i >= 0; i--) {
			if (s.charAt(i) == '|') {
				next = i;
			}
			a[i] = next;
		}
		return a;
	}

	private static int[] prefix(String s) {
		int n = s.length();
		int[] prefix = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int plate = s.charAt(i) == '*' ? 1 : 0;
			prefix[i + 1] = prefix[i] + plate;
		}
		return prefix;
	}

	public static void main(String[] args) {
		String s1 = "**|**|***|";
		String s2 = "***|**|*****|**||**|*";
		int[][] q1 = { { 2, 5 }, { 5, 9 } };
		int[][] q2 = { { 1, 17 }, { 4, 5 }, { 14, 17 }, { 5, 11 }, { 15, 16 } };
		print(platesBetweenCandles(s1, q1)); // [2,3]
		print(platesBetweenCandles(s2, q2)); // [9,0,0,0,0]
	}
}
