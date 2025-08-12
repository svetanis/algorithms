package com.svetanis.algorithms.twopointers;

import static java.lang.String.valueOf;

// 2562. Find the Array Concatenation Value

public final class ArrayConcatenationValue {
	// Time Complexity: O(n log m)
	// Space Complexity: O(log m)

	public static long dividePlayers(int[] a) {
		int left = 0;
		int right = a.length - 1;
		long total = 0;
		while (left < right) {
			String concat = valueOf(a[left]) + valueOf(a[right]);
			total += Long.parseLong(concat);
			left++;
			right--;
		}
		// total += (left == right) ? a[left] : 0;
		if (left == right) {
			total += a[left];
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 7, 52, 2, 4 };
		System.out.println(dividePlayers(a1)); // 596

		int[] a2 = { 5, 14, 13, 8, 12 };
		System.out.println(dividePlayers(a2)); // 673
	}
}
