package com.svetanis.algorithms.prefixsum;

import com.svetanis.java.base.utils.Print;

// 2575. Find the Divisibility Array of a String

public final class DivisibilityArray {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] divisibilityArray(String word, int m) {
		long num = 0;
		int n = word.length();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			num = (num * 10 + (word.charAt(i) - '0')) % m;
			a[i] = num == 0 ? 1 : 0;
		}
		return a;
	}

	public static void main(String[] args) {
		Print.print(divisibilityArray("998244353", 3)); // 1 1 0 0 0 1 1 0 0
		Print.print(divisibilityArray("1010", 10)); // 0 1 0 1
	}
}
