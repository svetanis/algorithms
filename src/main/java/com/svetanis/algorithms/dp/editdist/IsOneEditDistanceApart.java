package com.svetanis.algorithms.dp.editdist;

// 161. One Edit Distance

public final class IsOneEditDistanceApart {
	// Time complexity: O(n * m)

	public static boolean isOneDistApart(String s, String t) {
		int n = s.length();
		int m = t.length();
		if (n < m) {
			return isOneDistApart(t, s);
		}
		if (n - m > 1) {
			return false;
		}
		for (int i = 0; i < m; i++) {
			if (s.charAt(i) != t.charAt(i)) {
				if (n == m) {
					return s.substring(i + 1).equals(t.substring(i + 1));
				}
				return s.substring(i + 1).equals(t.substring(i));
			}
		}
		return n == m + 1;
	}

	public static void main(String[] args) {
		System.out.println(isOneDistApart("cat", "cut"));
	}
}
