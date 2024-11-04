package com.svetanis.algorithms.prefixsum;

import static com.svetanis.java.base.utils.Print.print;

public final class SuffixSumArray {

	public static int[] suffixSum(int[] a) {
		int n = a.length;
		int[] suffix = new int[n];
		suffix[n - 1] = a[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			suffix[i] = suffix[i + 1] + a[i];
		}
		return suffix;
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 20, 10, 5, 15 };
		print(suffixSum(a1));

		int[] a2 = { 10, 4, 16, 20 };
		print(suffixSum(a2));
	}
}
