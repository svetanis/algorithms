package com.svetanis.algorithms.string;

// 522. Longest Uncommon Subsequence II

public final class LongestUncommonSubsequence {
	// Time Complexity: O(n^2 * m)
	// Space Complexity: O(1)

	public static int lus(String[] a) {
		int n = a.length;
		int max = -1;
		for (int i = 0, j = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (j == i) {
					continue;
				}
				if (isSubseq(a[i], a[j])) {
					break;
				}
			}
			if (j == n) {
				max = Math.max(max, a[i].length());
			}
		}
		return max;
	}

	private static boolean isSubseq(String s, String t) {
		int n = s.length();
		int m = t.length();
		if (n == 0) {
			return true;
		}
		if (m == 0) {
			return false;
		}
		int index = 0;
		for (int i = 0; i < m && index < n; i++) {
			if (s.charAt(index) == t.charAt(i)) {
				index++;
			}
		}
		return index == n;
	}

	public static void main(String[] args) {
		String[] a1 = { "aba", "cdc", "eae" };
		System.out.println(lus(a1)); // 3
		String[] a2 = { "aaa", "aaa", "aa" };
		System.out.println(lus(a2)); // -1
	}
}