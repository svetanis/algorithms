package com.svetanis.algorithms.prefixsum.range;

import static com.svetanis.algorithms.prefixsum.PrefixSumArray.prefixSum;

public final class RangeSumQueries {

	public static int rangeSum(int[] a, int left, int right) {
		int[] prefix = prefixSum(a);
		if (left == 0) {
			return prefix[right];
		}
		return prefix[right] - prefix[left - 1];
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		System.out.println(rangeSum(a, 1, 3));
		System.out.println(rangeSum(a, 2, 4));
	}
}
