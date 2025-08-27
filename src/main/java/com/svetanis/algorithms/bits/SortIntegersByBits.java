package com.svetanis.algorithms.bits;

import java.util.Arrays;

import com.svetanis.java.base.utils.Print;

// 1356. Sort Integers by The Number of 1 Bits

public final class SortIntegersByBits {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int[] sortByBits(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int bits = Integer.bitCount(a[i]);
			a[i] += bits * 100000;
		}
		Arrays.sort(a);
		for (int i = 0; i < n; i++) {
			a[i] %= 100000;
		}
		return a;
	}

	public static void main(String args[]) {
		int[] a1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		Print.print(sortByBits(a1)); // 0,1,2,4,8,3,5,6,7
		int[] a2 = { 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1 };
		Print.print(sortByBits(a2)); // 1,2,4,8,16,32,64,128,256,512,1024
	}
}
