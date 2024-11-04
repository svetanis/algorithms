package com.svetanis.algorithms.prefixsum;

import static com.svetanis.java.base.utils.Print.print;

public final class PrefixSumArray {

	public static int[] prefixSum(int[] a) {
		int n = a.length;
		int[] prefix = new int[n];
		prefix[0] = a[0];
		for (int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] + a[i];
		}
		return prefix;
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 20, 10, 5, 15 };
		print(prefixSum(a1));

		int[] a2 = { 10, 4, 16, 20 };
		print(prefixSum(a2));
	}
}
