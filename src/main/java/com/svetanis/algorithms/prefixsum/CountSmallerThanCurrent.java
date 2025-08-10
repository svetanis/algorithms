package com.svetanis.algorithms.prefixsum;

import com.svetanis.java.base.utils.Print;

// 1365. How Many Numbers Are Smaller Than the Current Number

public final class CountSmallerThanCurrent {
	// Time complexity: O(n + m)

	public static int[] smallerThanCurrent(int[] a) {
		int[] counts = count(a);
		for (int i = 1; i < counts.length; i++) {
			counts[i] += counts[i - 1];
		}
		int[] smaller = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			smaller[i] = counts[a[i]];
		}
		return smaller;
	}

	private static int[] count(int[] a) {
		int[] counts = new int[102];
		for (int num : a) {
			counts[num + 1]++;
		}
		return counts;
	}

	public static void main(String[] args) {
		int[] a1 = { 8, 1, 2, 2, 3 };
		Print.print(smallerThanCurrent(a1)); // 4 0 1 1 3
		int[] a2 = { 6, 5, 4, 8 };
		Print.print(smallerThanCurrent(a2)); // 2 1 0 3
		int[] a3 = { 7, 7, 7, 7 };
		Print.print(smallerThanCurrent(a3)); // 0 0 0 0
		int[] a4 = { 5, 0, 10, 0, 10, 6 };
		Print.print(smallerThanCurrent(a4)); // 2 0 4 0 4 3
	}
}
