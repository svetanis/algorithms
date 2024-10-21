package com.svetanis.algorithms.search.duplicate;

import static java.lang.Math.abs;

// 287. Find the Duplicate Number

// given an array of n + 1 integers in
// the range from 1 to n inclusive
// there is only one repeated number in nums 
// find this repeated number

public final class DuplicateInPlaceInversion {
	// Time Complexity: O(n)

	public static int duplicate(int[] a) {
		int dup = -1;
		for (int i = 0; i < a.length; i++) {
			int cur = abs(a[i]);
			if (a[cur] < 1) {
				dup = cur;
				break;
			}
			a[cur] *= -1;
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = abs(a[i]);
		}
		return dup;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 4, 2, 2 };
		System.out.println(duplicate(a1)); // 2

		int[] a2 = { 3, 1, 3, 4, 2 };
		System.out.println(duplicate(a2)); // 3

		int[] a3 = { 3, 3, 3, 3, 3 };
		System.out.println(duplicate(a3)); // 3

		int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10 };
		System.out.println(duplicate(a4)); // 7

		int[] a5 = { 2, 2, 2, 2, 2 };
		System.out.println(duplicate(a5)); // 2

		int[] a6 = { 1, 1, 1, 1, 1 };
		System.out.println(duplicate(a6)); // 1
	}
}
