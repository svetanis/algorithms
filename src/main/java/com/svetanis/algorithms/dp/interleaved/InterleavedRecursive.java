package com.svetanis.algorithms.dp.interleaved;

// 97. Interleaving String

// Given two strings str1 and str2, 
// write a function that prints all 
// interleavings of the given two strings.

public final class InterleavedRecursive {
	// Time Complexity: O(2^(n + m))
	// Space Complexity: O(n + m)

	public static boolean isInterleaved(String s1, String s2, String s) {
		return isInterleaved(s1, s2, s, 0, 0, 0);
	}

	private static boolean isInterleaved(String x, String y, String s, int i, int j, int k) {
		int n = x.length();
		int m = y.length();
		int p = s.length();

		if (i == n && j == m && k == p) {
			return true;
		}
		if (k == p) {
			return false;
		}

		boolean interleaved = false;
		if (i < n && x.charAt(i) == s.charAt(k)) {
			interleaved = interleaved || isInterleaved(x, y, s, i + 1, j, k + 1);
		}
		if (j < m && y.charAt(j) == s.charAt(k)) {
			interleaved = interleaved || isInterleaved(x, y, s, i, j + 1, k + 1);
		}
		return interleaved;
	}

	public static void main(String[] args) {
		System.out.println(isInterleaved("XXY", "XXZ", "XXZXXXY")); // false
		System.out.println(isInterleaved("XY", "WZ", "WZXY")); // true
		System.out.println(isInterleaved("XY", "X", "XXY")); // true
		System.out.println(isInterleaved("YX", "X", "XXY")); // false
		System.out.println(isInterleaved("XXY", "XXZ", "XXXXZY")); // true
	}
}
